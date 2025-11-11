package com.empresa.controller;

import com.empresa.dao.NominaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// @WebServlet(description = "unicamente se administran los dni", urlPatterns = {"/nominas"})

/**
 * Controlador para la vista de nominas.jsp
 * Se utiliza en {@link FrontController} para organizar las peticiones.
 * No es necesario tener el servlet aquí.
 */
public class NominaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NominaController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        /**
         * <h2>Listar salarios:</h2>
         * <p>A partir de un DNI pedido, te devuelve
         * el salario correspondiente del empleado. </p>
         */
        if (accion.equals("listarSalarios")) {
            String dni = request.getParameter("dni");

            try {
                NominaDAO nominaDAO = new NominaDAO();
                List<Object[]> nominas = nominaDAO.obtenerNomina(dni);

                request.setAttribute("nominas", nominas);
                request.setAttribute("dni", dni);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarSalarios.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
