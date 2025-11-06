<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modificar Empleado</title>
    <style>
        body {
            font-family: Arial;
            margin: auto;
            text-align: center;
            background-color: #343434;
        }
        div {
            border-radius: 10px;
            box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
            padding: 15px;
            margin: 10%;
            display: inline-block;
            vertical-align: middle;
            text-align: justify;
            background-color: #232323;
            color: white;
            text-decoration: none;
        }

        table td {
            border: 1px solid black;
            padding: 10px;
            margin: 10px;
        }

        button {
            border-radius: 10px;
            background-color: white;
            padding: 10px 15px;
            text-align: center;
            font-size: 16px;
            margin: 10px 0px;
            display: block;
            align-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<div>
    <!-- Se utiliza un formulario para conseguir los datos del empleado que se desea
     en este caso mediante el DNI. -->
    <form method="get">
        <input type="hidden" name="accion" value="buscarEmpleado">
        <label for="dni">DNI del Empleado:</label>
        <c:choose>
            <c:when test="${not empty empleado and not empty empleado.dni}">
                <input type="text" id="dni" name="dni" value="${empleado.dni}" readonly>
            </c:when>
            <c:otherwise>
                <input type="text" id="dni" name="dni" required>
                <input type="submit" value="Buscar Empleado">
            </c:otherwise>
        </c:choose>
    </form>

    <!-- Si me devuelve un empleado, se muestra el formulario para modificarlo. -->
    <c:if test="${not empty empleado and not empty empleado.dni}">
        <hr style="border-color: #555; margin: 20px 0;">
        <h3>Datos del Empleado</h3>
        <form method="post" action="${pageContext.request.contextPath}/app">
            <input type="hidden" name="accion" value="editarEmpleado">
            <input type="hidden" name="dni" value="${empleado.dni}">

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${empleado.nombre}" required>

            <label for="sexo">Sexo:</label>
            <select id="sexo" name="sexo" required>
                <option value="M" <c:if test="${empleado.sexo.toString() eq 'M'}">selected</c:if>>Masculino</option>
                <option value="F" <c:if test="${empleado.sexo.toString() eq 'F'}">selected</c:if>>Femenino</option>
            </select>

            <label for="categoria">Categoría (1-10):</label>

            <label for="categoria">Categoría (1-10):</label>
            <input type="number" id="categoria" name="categoria"
                   min="1" max="10" value="${empleado.categoria}" required>

            <label for="anyos">Años trabajados:</label>
            <input type="number" id="anyos" name="anyos"
                   min="0" value="${empleado.anyos}" required>

            <input type="submit" value="Guardar Cambios">
        </form>
    </c:if>

    <button type="button" onclick="window.location.href='<%= request.getContextPath() %>/'">
        Volver al Inicio
    </button>
</div>
</body>
</html>
