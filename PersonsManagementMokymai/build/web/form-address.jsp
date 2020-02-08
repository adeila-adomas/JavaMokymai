<%@page import="lt.bit.java.Address"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="lt.bit.java.Person" %>
<%@page import="lt.bit.java.ListPersons" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <%
            Address p = null;
            String dateString = null;
            if (request.getParameter("id") != null) {
                Integer addressId = new Integer(request.getParameter("id"));
                
                p = ListPersons.getPerson(personId);
                Date birthDate = p.getBirthDate();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                dateString = formatter.format(birthDate);

                %><h1><%="Edit "+p.getFirstName()%></h1><%
            } else {
                %><h1>Add new Person</h1><%
                p = new Person();
            }
            %>
        <form action="save" method="post">
            <table style="width:100%; border:1px solid #ccccff;">
            <tr>
                <td>ID:</td>
            <td><input type="hidden" name="id" value="<%=p.getId()%>"><%=p.getId()%></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="first_name" value="<%=(p.getFirstName()!=null?p.getFirstName():"")%>"></td>
            </tr>
            <tr>
                <td>City:</td>
                <td><input type="text" name="last_name" value="<%=p.getLastName()!=null?p.getLastName():""%>"></td>
            </tr>
            <tr>
                <td>Postal Code:</td>
                <td><input type="text" name="age" value="<%=p.getAge()!=null?p.getAge():""%>"</td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="btn_action" value="Save">
                    <input type="submit" name="btn_action" value="Cancel">
                </td>
            </tr>
        </table>
            </form>
    </body>
</html>
