<%-- 
    Document   : contacts
    Created on : Feb 5, 2020, 11:31:52 PM
    Author     : adoma
--%>

<%@page import="lt.bit.java.ListPersons"%>
<%@page import="lt.bit.java.datamodel.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
    </head>
    <body>
        
        <%
            Person p = null;
            if (request.getParameter("person_id") != null) {
                Integer personId = new Integer(request.getParameter("person_id"));
                p = ListPersons.getPerson(personId);
            } 
            %>
        <h1>Contacts</h1>
        <br>
        <br>
        <table border="1" style="width:100%; border: 1px solid #00cccc;">
            <tr>
                <td><strong>Person: </strong></td>
                <td><%=p.getFirstName()%></td>
                <td><%=p.getLastName()%></td>
            </tr>
        </table>
        
        <form action="view" method="GET">
            <input type="hidden" name="page_url" value="index.jsp">
            <input type="submit" name="btn_back" value="Back">
        </form>
    </body>
</html>
