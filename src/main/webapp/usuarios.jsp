<%
    if (session.getAttribute("login") != "OK") {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Punto de Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>

    </head>
    <body>
        <div class="container">
            <h1>Punto de Venta</h1> 

            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="usuarios"/>
            </jsp:include>

            <a href="UsuarioControlador?action=add" class="btn btn-primary btn-sm mt-4"><i class="fa-solid fa-circle-plus"></i> Nuevo</a>

            <table class="table table-striped mt-2">
                <tr>
                    <th>Id</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Correo</th>
                    <th>Password</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${usuarios}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.nombres}</td>
                        <td>${item.apellidos}</td>
                        <td>${item.correo}</td>
                        <td>${item.password}</td>
                        <td><a href="UsuarioControlador?action=edit&id=${item.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                        <td><a href="UsuarioControlador?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar..'))"><i class="fa-solid fa-trash"></i></a></td>
                    </tr>
                </c:forEach>

            </table>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
