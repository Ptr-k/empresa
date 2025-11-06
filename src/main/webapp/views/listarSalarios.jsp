<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Mostrar Salarios</title>
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

        #search {
            margin-bottom: 10px;
            height: 35px
        }
    </style>
</head>
<body>
<div>
    <form action="nominas" method="get" value="listarSalarios">
        <input type="hidden" name="accion" value="listarSalarios">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required placeholder="Ingrese el DNI">
        <button type="submit" id="search">Buscar Salario</button>
    </form>

    <c:if test="${not empty nominas}">
        <c:forEach items="${nominas}" var="item">
            <p>El saldo del DNI ${item[0].dni} es de ${item[1]} €</p>
        </c:forEach>
    </c:if>

    <c:if test="${empty nominas && not empty dni}">
        <p style="color: red">No se encontró ningún empleado con el DNI: ${dni}</p>
    </c:if>

    <button type="button" onclick="window.location.href='<%= request.getContextPath() %>/'">
        Volver al Inicio
    </button>
</div>
</body>
</html>
