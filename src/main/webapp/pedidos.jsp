<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chilibana.modelo.Pedido" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pedidos - ChiliBana</title>
    <meta charset="UTF-8">
</head>
<body>

    <h1>ChiliBana</h1>
    <h2>Registro de pedidos</h2>

    <h3>Registrar pedido</h3>

    <form action="pedidos" method="post">

        <input type="hidden" name="accion" value="insertar">

        <label>Cliente:</label>
        <input type="text" name="cliente" required>
        <br><br>

        <label>Tipo de pedido:</label>
        <select name="tipoPedido">
            <option value="Domicilio">Domicilio</option>
            <option value="En sitio">En sitio</option>
        </select>
        <br><br>

        <label>Producto:</label>
        <select name="producto">
            <option value="Gomitas enchiladas">Gomitas enchiladas</option>
            <option value="Granizado enchilado">Granizado enchilado</option>
            <option value="Chamoy">Chamoy</option>
        </select>
        <br><br>

        <label>Cantidad:</label>
        <input type="number" name="cantidad" min="1" required>
        <br><br>

        <button type="submit">Registrar pedido</button>

    </form>

    <hr>

    <h3>Pedidos registrados</h3>

    <table border="1">

        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Tipo</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Actualizar</th>
            <th>Eliminar</th>
        </tr>

        <%
            List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");

            if (pedidos != null) {
                for (Pedido pedido : pedidos) {
        %>

        <tr>
            <td><%= pedido.getId() %></td>
            <td><%= pedido.getCliente() %></td>
            <td><%= pedido.getTipoPedido() %></td>
            <td><%= pedido.getProducto() %></td>

            <td>
                <%= pedido.getCantidad() %>
            </td>

            <td>
                <form action="pedidos" method="post">

                    <input type="hidden" name="accion" value="actualizar">

                    <input type="hidden"
                           name="id"
                           value="<%= pedido.getId() %>">

                    <input type="hidden"
                           name="cliente"
                           value="<%= pedido.getCliente() %>">

                    <input type="hidden"
                           name="tipoPedido"
                           value="<%= pedido.getTipoPedido() %>">

                    <input type="hidden"
                           name="producto"
                           value="<%= pedido.getProducto() %>">

                    <input type="number"
                           name="cantidad"
                           value="<%= pedido.getCantidad() %>"
                           min="1">

                    <button type="submit">Actualizar</button>

                </form>
            </td>

            <td>
                <form action="pedidos" method="post">

                    <input type="hidden"
                           name="accion"
                           value="eliminar">

                    <input type="hidden"
                           name="id"
                           value="<%= pedido.getId() %>">

                    <button type="submit">Eliminar</button>

                </form>
            </td>

        </tr>

        <%
                }
            }
        %>

    </table>

</body>
</html>