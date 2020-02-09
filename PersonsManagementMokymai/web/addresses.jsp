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
        <h1>Addresses (<%=p.getAddresses().getListItems().size()%>)</h1>
        <h3>Person: <%=p.getFirstName()+" "+p.getLastName()%></h3>
        
        <table border="0" style="width:100%; border: 1px solid #00cccc;">
            <tr>
                <td colspan="2">
                    <table border="1" style="width:100%; border: 1px solid #00cccc;">
                        <tr>
                            <th>ID</th>
                            <th>Address</th>
                            <th>City</th>
                            <th>Postal Code</th>
                            <th colspan="2">Actions</th>
                        </tr>
                        <% for (Address addr : p.getAddresses().getListItems()) {%>
                        <tr>
                            <td><%=addr.getId()%></td>
                            <td><%=addr.getAddress()%></td>
                            <td><%=addr.getCity()%></td>
                            <td><%=addr.getPostalCode()%></td>
                            <td style="width: 80px;">
                                <form action="view" method="GET">
                                    <input type="hidden" name="id" value="<%=addr.getId()%>">
                                    <input type="hidden" name="page_url" value="form-address.jsp?person_id=<%=p.getId()%>&id=<%=addr.getId()%>">
                                    <input type="submit" name="btn_action" value="Edit">
                                </form>
                            </td>
                            <td style="width: 80px;">
                                <form action="removeAddress" method="POST">
                                    <input type="hidden" name="person_id" value="<%=p.getId()%>">
                                    <input type="hidden" name="id" value="<%=addr.getId()%>">
                                    <input type="hidden" name="redirect_url" value="addresses.jsp?person_id=<%=p.getId()%>">
                                    <input type="submit" name="btn_remove" value="Remove">
                                </form>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                </td>
            </tr>

            <tr>
                <td style="width:100px;">
                    <form action="view" method="GET">
                        <input type="hidden" name="page_url" value="form-address.jsp?person_id=<%=p.getId()%>">
                        <input type="submit" name="btn_back" value="Add">
                    </form>
                </td>
                <td>
                    <form action="view" method="GET">
                        <input type="hidden" name="page_url" value="index.jsp">
                        <input type="submit" name="btn_back" value="Back">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
