package com.empresa.controller;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.exception.DatosNoCorrectosException;
import com.empresa.model.Empleado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// @WebServlet(description = "se administran las peticiones para los empleados", urlPatterns = {"/empleados"})
// Ahora que se utiliza @FrontController no es necesario el Servlet aquí.
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadoController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("listarEmpleados")) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> lista = new ArrayList();
            try {
                lista = empleadoDAO.listarEmpleados();
                for (Empleado empleado : lista) {
                    System.out.println(empleado);
                }

                request.setAttribute("lista", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarEmpleados.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (accion.equals("modificar")) {
            // Mostrar formulario de búsqueda/modificación
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/modificar.jsp");
            requestDispatcher.forward(request, response);

        } else if (accion.equals("buscarEmpleado")) {
            // Buscar empleado por DNI para mostrar en el formulario
            String dni = request.getParameter("dni");
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            try {
                Empleado empleado = empleadoDAO.buscarEmpleado(dni);

                if (empleado != null && empleado.getDni() != null && !empleado.getDni().isEmpty()) {
                    request.setAttribute("empleado", empleado);
                }

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/modificar.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("editarEmpleados")) {
            String dni = request.getParameter("dni");
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleado = new Empleado();

            try {
                empleado.setDni(request.getParameter("dni"));
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
                empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));
                empleado.setSexo(request.getParameter("sexo").charAt(0));

                boolean actualizado = empleadoDAO.editar(empleado);

                if (actualizado) {
                    request.setAttribute("mensaje", "Empleado actualizado correctamente");
                } else {
                    request.setAttribute("mensaje", "No se pudo actualizar el empleado");
                }

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } catch (DatosNoCorrectosException | SQLException e) {
                request.setAttribute("error", "Error al actualizar: " + e.getMessage());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/modificar.jsp");
                requestDispatcher.forward(request, response);
            }

        }
    }
}
