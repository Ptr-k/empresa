package com.empresa.conexion;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 Utilización del patrón de diseño, Singleton, para mejorar la conexión a la base de datos.
 Se utiliza un método privado, con solo una instancia del DataSource.
 <p>Cuenta con una configuración básica.</p>
 */
public class Conexion {
    private static BasicDataSource dataSource = null;

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (Conexion.class) {
                if (dataSource == null) { // Se realiza un double check mediante esta linea. No es del todo necesario.
                    dataSource = new BasicDataSource();

                    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    dataSource.setUsername("root");
                    dataSource.setPassword("123456");
                    dataSource.setUrl("jdbc:mysql://localhost:3306/nominas");

                    // Configuración del POOL que se utilizará.
                    dataSource.setInitialSize(5);           // conexiones iniciales
                    dataSource.setMaxTotal(20);             // máximo de conexiones
                    dataSource.setMaxIdle(10);              // máximo de conexiones inactivas
                    dataSource.setMinIdle(5);               // mínimo conexiones inactivas
                    dataSource.setMaxWaitMillis(5000);      // tiempo máximo de espera
                }
            }
        }
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException {
        /**
         * Consigue la conexión anteriormente configurada.
         * @return Connection
         * @throws SQLException
         * @see BasicDataSource#getConnection()
         */
        return getDataSource().getConnection();
    }

    /**
     * Cerrar el pool de conexiones.
     * Se cierra de forma correcta para evitar problemas futuros.
     */
    public static void cerrarPool() {
        if (dataSource != null) {
            try {
                dataSource.close();
                System.out.println("✅ Pool de conexiones cerrado");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}