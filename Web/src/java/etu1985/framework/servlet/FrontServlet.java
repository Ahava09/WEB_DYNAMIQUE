/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1985.framework.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import etu1985.framework.*;


/**
 *
 * @author itu
 */
@WebServlet(name = "frontservlet_1")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls = new HashMap<>();
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // init();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet frontservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Name " + getNameServlet(request,out) + "</h1>");
            out.println("</body>");
            out.println("</html>");
              
        }
    }
//    public void init(ServletConfig config) throws ServletException {
//        try {
//        super.init(config);
//        servletname = config.getServletName();
//    } catch (ServletException ex) {
//        // Gérer l'exception ici ou la relancer
//        ex.printStackTrace();
//    }
        
//    }
    
    private String getNameServlet(HttpServletRequest request,PrintWriter out){
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String[] uriParts = uri.split(context);
        out.print("uri:  "+uri);
        if (uriParts.length > 1) {
            return uriParts[1];
        } else {
            return "";
        }

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
