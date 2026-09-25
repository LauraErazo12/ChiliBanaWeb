package com.chilibana.servlet;

import com.chilibana.dao.PedidoDAO;
import com.chilibana.modelo.Pedido;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PedidoServlet", urlPatterns = {"/pedidos"})
public class PedidoServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Pedido> pedidos = pedidoDAO.consultarTodos();
            request.setAttribute("pedidos", pedidos);

            request.getRequestDispatcher("pedidos.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if ("insertar".equals(accion)) {

                String cliente = request.getParameter("cliente");
                String tipoPedido = request.getParameter("tipoPedido");
                String producto = request.getParameter("producto");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                Pedido pedido = new Pedido(
                        cliente,
                        tipoPedido,
                        producto,
                        cantidad
                );

                pedidoDAO.insertar(pedido);

            } else if ("actualizar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));
                String cliente = request.getParameter("cliente");
                String tipoPedido = request.getParameter("tipoPedido");
                String producto = request.getParameter("producto");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                Pedido pedido = new Pedido(
                        id,
                        cliente,
                        tipoPedido,
                        producto,
                        cantidad
                );

                pedidoDAO.actualizar(pedido);

            } else if ("eliminar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));
                pedidoDAO.eliminar(id);
            }

            response.sendRedirect("pedidos");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
