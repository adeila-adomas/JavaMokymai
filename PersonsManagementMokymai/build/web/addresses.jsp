<%@page import="lt.bit.java.datamodel.Address"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="lt.bit.java.datamodel.Person" %>
<%@page import="lt.bit.java.services.ListPersons" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Addresses</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <%
            Person p = null;
            if (request.getParameter("person_id") != null) {
                Integer personId = new Integer(request.getParameter("person_id"));
                p = ListPersons.getPerson(personId);
            } 
            %>
        <h1>Addresses</h1>
        <br>
        <br>
        <table border="1" style="width:100%; border: 1px solid #00cccc;">
            <tr>
                <td><strong>Person: </strong></td>
                <td><%=p.getFirstName()%></td>
                <td><%=p.getLastName()%></td>
            </tr>
        </table>
        <form action="addresses" method="POST">
            <table border="1" style="width:100%; border: 1px solid #00cccc;">
                <% for (Address addr : p.getAddresses()) { %>
                <tr>
                    <td>Address: </td>
                    <td><%=addr.getId()%></td>
                    <td><%=addr.getAddress()%></td>
                    <td><%=addr.getCity()%></td>
                    <td><%=addr.getPostalCode()%></td>
                    <td colspan="2">Actions</td>
                    <td><input type="submit" name="btnAddAddress" value="Edit"></td>
                    <td><input type="submit" name="btnBack" value="Delete"></td>
                </tr>
                <% } %>
            </table>
        </form>
            
        <form action="view" method="GET">
            <input type="hidden" name="page_url" value="form-address.jsp?person_id=<%=p.getId()%>">
            <input type="submit" name="btn_back" value="Add">
        </form>            
            
        <form action="view" method="GET">
            <input type="hidden" name="page_url" value="index.jsp">
            <input type="submit" name="btn_back" value="Back">
        </form>
    </body>
</html>
