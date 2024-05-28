
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            VentaDAO dao = new VentaDAOimpl();
            ProductoDAO daoProducto = new ProductoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
            int id;
            List<Producto> listaProducto = null;
            List<Cliente> listaCliente = null;

            Venta venta = new Venta();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";


            switch (action) {
                case "add":
                    listaProducto = daoProducto.getAll();
                    listaCliente = daoCliente.getAll();
                    request.setAttribute("listaProducto", listaProducto);
                    request.setAttribute("listaCliente", listaCliente);
                    
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventas.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    venta=dao.getById(id);
                    
                    listaProducto = daoProducto.getAll();
                    listaCliente = daoCliente.getAll();
                    request.setAttribute("listaProducto", listaProducto);
                    request.setAttribute("listaCliente", listaCliente);
                    
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventas.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    
                    response.sendRedirect(request.getContextPath()+"/VentaControlador");
                    break;
                case "view":
                    List<Venta> lista = dao.getAll();
                                       
                    request.setAttribute("ventas", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
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
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));
        String fecha = request.getParameter("fecha");
        
        Venta venta = new Venta();
               
        venta.setId(id);
        venta.setProducto_id(producto_id);
        venta.setCliente_id(cliente_id);
        venta.setFecha(convertirFecha(fecha));
        

        if (id == 0) {
            
            try {
                VentaDAO dao = new VentaDAOimpl();
                dao.insert(venta);
                response.sendRedirect(request.getContextPath() + "/VentaControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            
            try {
                VentaDAO dao = new VentaDAOimpl();
                dao.update(venta);
                response.sendRedirect(request.getContextPath() + "/VentaControlador");
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }

    }
    
    public Date convertirFecha(String fecha){
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return fechaBD;
        
    }
    
}
