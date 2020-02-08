/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.java.services.ListPersons;
import lt.bit.java.datamodel.Person;

/**
 *
 */
@WebServlet(name="SavePerson", urlPatterns = {"/save"})
public class SavePersonServlet extends HttpServlet {

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
            String btnAction = request.getParameter("btn_action");
            
            if (btnAction.equalsIgnoreCase("cancel")) {
                response.sendRedirect("index.jsp");
            } else if (btnAction.equalsIgnoreCase("save")) {
                
                Integer id = new Integer(request.getParameter("id"));
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                Integer age = new Integer(request.getParameter("age"));
                BigDecimal salary = new BigDecimal(request.getParameter("salary"));
                
                Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birth_date"));  
                
                Person p = new Person();
                p.setId(id);
                p.setFirstName(firstName);
                p.setLastName(lastName);
                p.setAge(age);
                p.setSalary(salary);
                p.setBirthDate(birthDate);
                
                ListPersons.savePerson(p);
                
                response.sendRedirect("index.jsp");
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
