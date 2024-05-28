
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UsuarioDAO dao = new UsuarioDAOimpl();
            int id;
            Usuario usuario = new Usuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            System.out.println(action);

            switch (action) {
                case "add":
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("frmusuarios.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    usuario=dao.getById(id);
                    
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("frmusuarios.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    
                    response.sendRedirect(request.getContextPath()+"/UsuarioControlador");
                    break;
                case "view":
                    
            System.out.println("adentro de sout");
                    List<Usuario> lista = dao.getAll();
                                                   System.out.println("despues de sout");

                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        System.out.println("id "+id);
       
        

        Usuario usuario = new Usuario();
                
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setId(id);
        usuario.setCorreo(correo);
        usuario.setPassword(password);

        

        if (id == 0) {
            
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.insert(usuario);
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.update(usuario);
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador");
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }

    }

}
