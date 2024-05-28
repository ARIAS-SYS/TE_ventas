
package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VentaDAOimpl extends Conexion implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO ventas(producto_id,cliente_id,fecha) VALUES(?,?,?)");
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Venta venta) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE ventas SET producto_id = ?, cliente_id = ?, fecha = ? WHERE id = ?");
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void delete(int id) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM ventas WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public Venta getById(int id) throws Exception {

        Venta venta = new Venta();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM ventas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venta.setId(rs.getInt("id"));
                venta.setProducto_id(rs.getInt("producto_id"));
                venta.setCliente_id(rs.getInt("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return venta;

    }

    @Override
    public List<Venta> getAll() throws Exception {

        List<Venta> lista = null;
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT v.*, p.nombre as producto, c.nombre as cliente FROM ventas v LEFT JOIN productos p ON v.producto_id = p.id LEFT JOIN clientes c ON v.cliente_id = c.id");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Venta>();
            while (rs.next()) {
                Venta venta = new Venta();

                venta.setId(rs.getInt("id"));
                venta.setProducto_id(rs.getInt("producto_id"));
                venta.setCliente_id(rs.getInt("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setProducto(rs.getString("producto"));
                venta.setCliente(rs.getString("cliente"));

                lista.add(venta);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return lista;

    }
    
}
