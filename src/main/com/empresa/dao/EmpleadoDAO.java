package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.exception.DatosNoCorrectosException;
import com.empresa.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para el controlador {@link com.empresa.controller.EmpleadoController}.
 */
public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    /**
     * Obtener conexion pool. Gracias a {@link Conexion}
     * @return
     * @throws SQLException
     */
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

    /**
     * Listar empleados.
     * @return una lista con todos los empleados.
     * @throws SQLException
     */
    public List<Empleado> listarEmpleados() throws SQLException {
        ResultSet resultado = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql);
            resultado = statement.executeQuery();
            while (resultado.next()) {
                Empleado emp = new Empleado();
                emp.setDni(resultado.getString("dni"));
                emp.setNombre(resultado.getString("nombre"));
                emp.setSexo(resultado.getString("sexo").charAt(0));
                emp.setCategoria(resultado.getInt("categoria"));
                emp.setAnyos(resultado.getInt("anyos"));
                listaEmpleados.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (DatosNoCorrectosException e) {
            throw new RuntimeException(e);
        }

        return listaEmpleados;
    }

    /**
     * Modificar empleado, ingresando un empleado en concreto
     * para poder modificar sus datos.
     * @param empleado
     * @return
     * @throws SQLException
     */
    public boolean editar(Empleado empleado) throws SQLException {
        String query = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            query = "UPDATE empleados SET nombre=?, sexo=?, anyos=?, categoria=? WHERE dni=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, empleado.getNombre());
            statement.setString(2, String.valueOf(empleado.getSexo()));
            statement.setInt(3, empleado.getAnyos());
            statement.setInt(4, empleado.getCategoria());
            statement.setString(5, empleado.getDni());

            estadoOperacion = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    /**
     * Buscar empleado mediante DNI.
     * Devuelve un objeto Empleado si se encuentra, null en caso contrario.
     * @param dni
     * @return
     * @throws SQLException
     */
    public Empleado buscarEmpleado(String dni) throws SQLException {
        ResultSet resultado = null;
        connection = obtenerConexion();
        String sql = "SELECT * FROM empleados WHERE dni=?";
        Empleado emp = new Empleado();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                emp.setDni(resultado.getString("dni"));
                emp.setNombre(resultado.getString("nombre"));
                emp.setSexo(resultado.getString("sexo").charAt(0));
                emp.setAnyos(resultado.getInt("anyos"));
                emp.setCategoria(resultado.getInt("categoria"));
            }
        } catch (SQLException | DatosNoCorrectosException e) {
            throw new RuntimeException(e);
        }

        return emp;
    }

}
