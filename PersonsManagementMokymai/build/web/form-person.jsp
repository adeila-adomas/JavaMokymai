<%-- 
    Document   : form-person
    Created on : Feb 1, 2020, 12:29:48 PM
    Author     : adoma
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="lt.bit.java.datamodel.Person" %>
<%@page import="lt.bit.java.services.ListPersons" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Person</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <%
            Person p = null;
            String dateString = null;
            if (request.getParameter("id") != null) {
                Integer personId = new Integer(request.getParameter("id"));
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
        <form action="savePerson" method="post">
            <table style="width:100%; border:1px solid #ccccff;">
            <tr>
                <td>ID:</td>
            <td><input type="hidden" name="id" value="<%=p.getId()%>"><%=p.getId()%></td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="first_name" value="<%=(p.getFirstName()!=null?p.getFirstName():"")%>"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="last_name" value="<%=p.getLastName()!=null?p.getLastName():""%>"></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="text" name="age" value="<%=p.getAge()!=null?p.getAge():""%>"</td>
            </tr>
            <tr>
                <td>Birth Date (yyyy-MM-dd):</td>
                <td><input type="text" name="birth_date" value="<%=p.getBirthDate()!=null?dateString:""%>"></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><input type="text" name="salary" value="<%=p.getSalary()==BigDecimal.ZERO?"":p.getSalary()%>"></td>
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
