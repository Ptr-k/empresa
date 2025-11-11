package com.empresa.controller;

import com.empresa.dao.EmpleadoDAO;
import com.empresa.dao.NominaDAO;
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
import java.util.List;

/**
 * Se utiliza el patrón de diseño Front Controller para organizar
 * las peticiones a los controladores, como {@link EmpleadoController} y {@link NominaController}.
 * @see EmpleadoController
 * @see NominaController
 */

@WebServlet("/app/*")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EmpleadoDAO empleadoDAO;
    private NominaDAO nominaDAO;

    public FrontController() {
        super();
        this.empleadoDAO = new EmpleadoDAO();
        this.nominaDAO = new NominaDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "inicio";
        }

        // Según las acciones escogidas, te redirige a la vista correspondiente
        try {
            switch(accion) {
                case "inicio":
                    mostrarInicio(request, response);
                    break;

                case "listarEmpleados":
                    listarEmpleados(request, response);
                    break;

                case "modificarEmpleado":
                    mostrarFormularioModificacion(request, response);
                    break;

                case "buscarEmpleado":
                    buscarEmpleado(request, response);
                    break;

                case "editarEmpleado":
                    editarEmpleado(request, response);
                    break;

                case "listarSalarios":
                    listarSalarios(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Acción no encontrada: " + accion);
            }
        } catch (Exception e) {
            throw new ServletException("Error procesando la acción: " + accion, e);
        }
    }

    private void mostrarInicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Empleado> lista = empleadoDAO.listarEmpleados();
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("/views/listarEmpleados.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error listando empleados", e);
        }
    }

    private void mostrarFormularioModificacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
    }

    private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        try {
            Empleado empleado = empleadoDAO.buscarEmpleado(dni);

            if (empleado != null && empleado.getDni() != null && !empleado.getDni().isEmpty()) {
                request.setAttribute("empleado", empleado);
            }

            request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error buscando empleado", e);
        }
    }

    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Empleado empleado = new Empleado();
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

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DatosNoCorrectosException | SQLException e) {
            request.setAttribute("error", "Error al actualizar: " + e.getMessage());
            request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
        }
    }

    private void listarSalarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        try {
            List<Object[]> nominas = nominaDAO.obtenerNomina(dni);
            request.setAttribute("nominas", nominas);
            request.setAttribute("dni", dni);
            request.getRequestDispatcher("/views/listarSalarios.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error obteniendo nómina", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}