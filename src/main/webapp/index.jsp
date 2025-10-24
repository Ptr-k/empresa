<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>EMPRESA</title>
    <link rel="stylesheet" type="text/css" href="/webapp/resources/css/style.css">
    <style>
        body {
            font-family: Arial;
            margin: auto;
            text-align: center;
        }
        div {
            display: inline-block;
            vertical-align: middle;
            text-align: justify;
        }
        table td {
            border: 1px solid black;
            padding: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>
<div>
    <h1>MENÚ DE EMPLEADOS</h1>
    <table>
        <tr>
            <td><a href="empleados?accion=listarEmpleados">Consultar empleados</a></td>
        </tr>
        <tr>
            <td><a href="empleados?accion=listarSalarios">Mostrar salario de empleados</a></td>
        </tr>
        <tr>
            <td><a href="empleados?accion=modificar">Modificar datos de un empleado existente</a></td>
        </tr>
    </table>
</div>
</body>
</html>