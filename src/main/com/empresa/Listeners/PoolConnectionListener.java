package com.empresa.Listeners;

import com.empresa.conexion.Conexion;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener para inicializar y finalizar las conexiones(pool)
 * a la base de datos. Cierra cada conexión al finalizar el contexto.
 * @see Conexion
 */
@WebListener
public class PoolConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        /**
         * Inicializar pool de conexiones.
         * @see Conexion#getConnection()
         */
        try {
            Conexion.getConnection().close(); // obtener y cerrar para inicializar pool.
        } catch (Exception e) {
            throw new RuntimeException(e); // devuelve directamente un error si no se puede inicializar el pool.
        }
    }

    /**
     * Finalizar el pool de conexiones correctamente al
     * detener la aplicación.
     * @see Conexion#cerrarPool()
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Conexion.cerrarPool();
    }
}