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

public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

    // Obtener lista de empleados
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

    // editar los empleados
    public boolean editar(Empleado empleado) throws SQLException {
        String query = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            query = "UPDATE empleados SET nombre=?, sexo=?, anyos=?, categoria=? WHERE id=?";
            statement = connection.prepareStatement(query);

            statement.setString(1, empleado.getNombre());
            statement.setString(2, String.valueOf(empleado.getSexo()));
            statement.setInt(3, empleado.getAnyos());
            statement.setInt(4, empleado.getCategoria());

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // Obtener empleado mediante un DNI
    public Empleado buscarEmpleado(String dni) throws SQLException {
        ResultSet resultado = null;
        connection = obtenerConexion();
        String sql = "SELECT * FROM empleados WHERE dni=?";
        Empleado emp = new Empleado();

        try {
            statement = connection.prepareStatement(sql);
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
