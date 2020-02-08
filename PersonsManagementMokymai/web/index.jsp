<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="lt.bit.java.datamodel.Person" %>
<%@page import="lt.bit.java.ListPersons" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Management</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <h1>Welcome to Persons Management</h1>
        <form action="view" method="GET">
            <input type="hidden" name="page_url" value="form-person.jsp">
            <input type="submit" name="btn_action" value="New">
        </form>
        <br>
        <br>
        <table border="1" style="width:100%; border: 1px solid #00cccc;">
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Birth date</th>
                <th>Salary</th>
                <th colspan="2">Actions</th>
                <th colspan="2">&nbsp;</th>
            </tr>
            <% for (Person per : ListPersons.getListItems()) { %>
            <tr>
                <td><%=per.getId()%></td>
                <td><%=per.getFirstName()%></td>
                <td><%=per.getLastName()%></td>
                <td><%=per.getAge()%></td>
                <td><% 
                    Date birthDate = per.getBirthDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                    out.write(formatter.format(birthDate));  
                %></td>
                <td><%=per.getSalary()%></td>
                <td style="width: 80px;">
                    <form action="view" method="POST">
                        <input type="hidden" name="id" value="<%=per.getId()%>">
                        <input type="submit" name="btn_action" value="Edit">
                    </form>
                </td>
                <td style="width: 80px;">
                    <form action="remove" method="POST">
                        <input type="hidden" name="id" value="<%=per.getId()%>">
                        <input type="submit" name="btn_remove" value="Remove">
                    </form>
                </td>
                <td style="width: 80px;">
                    <form action="view" method="POST">
                        <input type="hidden" name="page_url" value="addresses.jsp?person_id=<%=per.getId()%>">
                        <input type="submit" name="btn_action" value="Addresses">
                    </form>
                </td>
                <td style="width: 80px;">
                    <form action="view" method="GET">
                        <input type="hidden" name="page_url" value="contacts.jsp?person_id=<%=per.getId()%>">
                        <input type="submit" name="btn_action" value="Contacts">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
