package com.empresa.dao;

import com.empresa.conexion.Conexion;
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

    // Obtener todas las nóminas de los empleados
    public List<Nomina> obtenerNominas() throws SQLException {
        ResultSet resultado = null;
        List<Nomina> nominas = new ArrayList<>();
        String sql = "SELECT * FROM nominas";
        String sqlEmpld = "SELECT * from ";

        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql);
            resultado = statement.executeQuery();
            while (resultado.next()) {
                Nomina nom = new Nomina();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nominas;
    }
}
