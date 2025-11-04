<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Listar Empleados</title>
    <style>
        body {
            font-family: Arial;
            margin: auto;
            text-align: center;
            background-color: #343434;
        }
        div {
            border-radius: 10px;
            box-shadow: black 10px 10px 10px;
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
</head>
<body>
<h1>Listar Empleados</h1>
<div>
<table border="1">
    <tr>
        <td>DNI</td>
        <td>Nombre</td>
        <td>Sexo</td>
        <td>Categoria</td>
        <td>Años</td>
    </tr>
    <c:forEach var="empleado" items="${lista}">
        <tr>
            <td><c:out value="${empleado.dni}"></c:out></td>
            <td><c:out value="${empleado.nombre}"></c:out></td>
            <td><c:out value="${empleado.sexo}"></c:out></td>
            <td><c:out value="${empleado.categoria}"></c:out></td>
            <td><c:out value="${empleado.anyos}"></c:out></td>
        </tr>
    </c:forEach>
</table>
    <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
        Volver al Inicio
    </button>
</div>
</body>
</html>