package com.emergentes.dao;

import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends Conexion implements UsuarioDAO {

    @Override
    public void insert(Usuario usuario) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO usuarios(nombres,apellidos,correo,password) VALUES(?,?,?,sha1(?))");
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Usuario usuario) throws Exception {

        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios SET nombres = ?,apellidos = ?, correo = ?, password = ? WHERE id = ?");
            ps.setString(1, usuario.getNombres());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getPassword());
            ps.setInt(5, usuario.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public Usuario getById(int id) throws Exception {

        Usuario usuario = new Usuario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return usuario;

    }

    @Override
    public List<Usuario> getAll() throws Exception {
        
        System.out.println("adentro de get ");

        List<Usuario> lista = null;
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setPassword(rs.getString("password"));

                lista.add(usuario);
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
