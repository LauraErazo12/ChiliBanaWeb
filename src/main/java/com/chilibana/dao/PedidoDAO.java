package com.chilibana.dao;

import com.chilibana.conexion.ConexionBD;
import com.chilibana.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // INSERT
    public void insertar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente, tipo_pedido, producto, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, pedido.getCliente());
            sentencia.setString(2, pedido.getTipoPedido());
            sentencia.setString(3, pedido.getProducto());
            sentencia.setInt(4, pedido.getCantidad());

            sentencia.executeUpdate();
        }
    }

    // SELECT
    public List<Pedido> consultarTodos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Pedido pedido = new Pedido(
                        resultado.getInt("id"),
                        resultado.getString("cliente"),
                        resultado.getString("tipo_pedido"),
                        resultado.getString("producto"),
                        resultado.getInt("cantidad")
                );

                pedidos.add(pedido);
            }
        }

        return pedidos;
    }

    // UPDATE
    public void actualizar(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET cliente = ?, tipo_pedido = ?, producto = ?, cantidad = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, pedido.getCliente());
            sentencia.setString(2, pedido.getTipoPedido());
            sentencia.setString(3, pedido.getProducto());
            sentencia.setInt(4, pedido.getCantidad());
            sentencia.setInt(5, pedido.getId());

            sentencia.executeUpdate();
        }
    }

    // DELETE
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        }
    }
}