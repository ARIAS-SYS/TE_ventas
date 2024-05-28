

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
            <h1>Formulario de Clientes</h1> 

            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="productos"/>
            </jsp:include>

            <form action="ClienteControlador" method="post">
                <input type="hidden"name="id" value="${cliente.id}">

                <div class="mb-3">
                    <label for="" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="" name="nombre" value="${cliente.nombre}" placeholder="Escriba su nombre...">
                </div>
                <div class="mb-3">
                    <label for="" class="form-label">Email</label>
                    <input type="email" class="form-control" id="" name="correo" value="${cliente.correo}" placeholder="Correo electronico...">
                </div>
                <div class="mb-3">
                    <label for="" class="form-label">Celular</label>
                    <input type="text" class="form-control" id="" name="celular" value="${cliente.celular}" placeholder="Celular...">
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>



        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
