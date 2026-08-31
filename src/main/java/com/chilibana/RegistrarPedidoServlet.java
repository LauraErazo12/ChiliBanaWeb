package com.chilibana;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrarPedidoServlet", urlPatterns = {"/registrarPedido"})
public class RegistrarPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println(
            "<html><body>" +
            "<h1>ChiliBana</h1>" +
            "<h2>Método GET funcionando correctamente</h2>" +
            "<p>El Servlet recibió una solicitud mediante GET.</p>" +
            "<a href='index.html'>Volver al formulario</a>" +
            "</body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cliente = request.getParameter("cliente");
        String tipoPedido = request.getParameter("tipoPedido");
        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");

        request.setAttribute("cliente", cliente);
        request.setAttribute("tipoPedido", tipoPedido);
        request.setAttribute("producto", producto);
        request.setAttribute("cantidad", cantidad);

        request.getRequestDispatcher("resultado.jsp")
                .forward(request, response);
    }
}