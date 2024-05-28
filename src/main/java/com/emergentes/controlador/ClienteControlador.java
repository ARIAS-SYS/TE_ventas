
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ClienteDAO dao = new ClienteDAOimpl();
            int id;
            Cliente cliente = new Cliente();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";


            switch (action) {
                case "add":
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("frmclientes.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    cliente=dao.getById(id);
                    
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("frmclientes.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    
                    response.sendRedirect(request.getContextPath()+"/ClienteControlador");
                    break;
                case "view":
                    List<Cliente> lista = dao.getAll();
                                       
                    request.setAttribute("clientes", lista);
                    request.getRequestDispatcher("clientes.jsp").forward(request, response);
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
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String celular = request.getParameter("celular");
        
        System.out.println("id "+id);
       
        

        Cliente cliente = new Cliente();
                
        cliente.setNombre(nombre);
        cliente.setId(id);
        cliente.setCorreo(correo);
        cliente.setCelular(celular);

        

        if (id == 0) {
            
            try {
                ClienteDAO dao = new ClienteDAOimpl();
                dao.insert(cliente);
                response.sendRedirect(request.getContextPath() + "/ClienteControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            
            try {
                ClienteDAO dao = new ClienteDAOimpl();
                dao.update(cliente);
                response.sendRedirect(request.getContextPath() + "/ClienteControlador");
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }

    }

}
