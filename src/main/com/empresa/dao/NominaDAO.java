package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.exception.DatosNoCorrectosException;
import com.empresa.model.Empleado;
import com.empresa.model.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

    // Obtener nomina mediante un DNI.
    public List<Object[]> obtenerNomina(String dni) throws SQLException {
        ResultSet resultado = null;
        List<Object[]> nominas = new ArrayList<>();
        String sql = "SELECT * from empleados WHERE dni=? ";

        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                Empleado emp = new Empleado();
                emp.setDni(resultado.getString("dni"));
                emp.setNombre(resultado.getString("nombre"));
                emp.setSexo(resultado.getString("sexo").charAt(0));
                emp.setAnyos(resultado.getInt("anyos"));
                emp.setCategoria(resultado.getInt("categoria"));

                Nomina nom = new Nomina();
                int sueldo = nom.sueldo(emp);

                // Guardamos tanto el empleado como el salario
                nominas.add(new Object[]{emp, sueldo});
            }
        } catch (SQLException | DatosNoCorrectosException e) {
            throw new RuntimeException(e);
        }

        return nominas;
    }
}
