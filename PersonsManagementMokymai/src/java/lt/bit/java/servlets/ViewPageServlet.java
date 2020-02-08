/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ViewPageServlet", urlPatterns = {"/view"})
public class ViewPageServlet extends HttpServlet {

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
        
        try {
            String pageUrl = request.getParameter("page_url");
            if (pageUrl != null && !pageUrl.equals("")) {
                response.sendRedirect(pageUrl);
            }
            
        } catch (Exception e) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Klaida!</h1>");
                out.println("<p>" + e.getMessage() + "</p>");
                out.println("<a href='index.jsp'>Back to home page</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        
        /*
        try {
            String actionParam = request.getParameter("btn_action");
            
            if (actionParam.equalsIgnoreCase("new") || actionParam.equalsIgnoreCase("edit")) {
                
                if (request.getParameter("id") != null) {
                    response.sendRedirect("form-person.jsp?id=" + request.getParameter("id"));
                } else {
                    response.sendRedirect("form-person.jsp");
                }
            } else if (actionParam.equalsIgnoreCase("cancel")) {
                response.sendRedirect("index.jsp");
            } else if (actionParam.equalsIgnoreCase("addresses")) {
                response.sendRedirect("addresses.jsp?person_id=" + request.getParameter("id"));
            }
            
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
        */
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
