/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jss;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arand
 */
@WebServlet(name = "enviarServlet", urlPatterns = {"/enviarServlet"})
public class enviarServlet extends HttpServlet {
    
    private final static String QUEUE_NAME = "RabbitMQ2";
    private Channel canal;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String modelo = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String intervalo = request.getParameter("intervalo");
        String alerta = request.getParameter("alerta");
        run(null);
        enviar(modelo+","+tipo+","+intervalo+","+alerta);
        
        RequestDispatcher rd = request.getRequestDispatcher("pagfin.html");
        rd.forward(request, response);
        
    }
    
     public void run(Object mensaje) {
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            canal = connection.createChannel();
            canal.queueDeclare(QUEUE_NAME, false, false, false, null);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    /**
     * Metodo que recibe un String que sera el mensaje a enviar a la QUEUE
     * @param mensaje
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public void enviar(String mensaje) throws UnsupportedEncodingException, IOException {
        String message = (String) mensaje;
        canal.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Enviado '" + message + "'");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
