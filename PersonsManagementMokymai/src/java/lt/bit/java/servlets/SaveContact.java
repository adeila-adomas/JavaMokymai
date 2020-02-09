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
import lt.bit.java.datamodel.Contact;
import lt.bit.java.services.ListPersons;

/**
 *
 */
@WebServlet(name="SaveContact", urlPatterns = {"/saveContact"})
public class SaveContact extends HttpServlet {

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
            
            // Cancel
            if (request.getParameter("btn_cancel") != null && request.getParameter("referrer_url") != null) {
                response.sendRedirect(request.getParameter("referrer_url"));
            }
            
            // Save
            if (request.getParameter("btn_save") != null) {
                Integer personId = new Integer(request.getParameter("person_id"));
                Integer id = new Integer(request.getParameter("id"));
                
                Contact c = null;
                
                if (ListPersons.getPerson(personId).getContacts().getContact(id) != null) {
                    c = ListPersons.getPerson(personId).getContacts().getContact(id);
                } else {
                    c = new Contact();
                }
                
                c.setId(id);
                c.setContact(request.getParameter("contact"));
                c.setType(request.getParameter("type"));
                ListPersons.getPerson(personId).getContacts().saveContact(c);
                
                response.sendRedirect(request.getParameter("referrer_url"));
            }
            
        } catch (IOException | NumberFormatException e) {
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
