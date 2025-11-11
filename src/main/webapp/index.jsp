<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>EMPRESA</title>
    <style>
        body {
            font-family: Arial;
            margin: auto;
            text-align: center;
            background-color: #343434;
            color: white;
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
        table td a:visited {
            color: white;
            text-decoration: none;
        }
        table td {
            padding: 10px;
            margin: 10px;
            text-decoration: none;
        }
        a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            display: block;
            padding: 10px;
        }
        a:hover {
            background-color: #444444;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div>
    <table>
        <tr>
            <td><a href="${pageContext.request.contextPath}/app?accion=listarEmpleados">Consultar empleados</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/app?accion=listarSalarios">Mostrar salario de empleados</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/app?accion=modificarEmpleado">Modificar datos de un empleado existente</a></td>
        </tr>
    </table>
</div>
</body>
</html>