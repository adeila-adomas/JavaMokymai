<%-- 
    Document   : contacts
    Created on : Feb 5, 2020, 11:31:52 PM
    Author     : adoma
--%>

<%@page import="lt.bit.java.datamodel.Contact"%>
<%@page import="lt.bit.java.services.ListPersons"%>
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
        <br>
        <br>
        <h1>Contacts (<%=p.getContacts().getListItems().size()%>)</h1>
        <h3>Person: <%=p.getFirstName()+" "+p.getLastName()%></h3>
        
        <table border="0" style="width:100%; border: 1px solid #00cccc;">
            
            <tr>
                <td colspan="2">
                    <table border="1" style="width:100%; border: 1px solid #00cccc;">
                        <tr>
                            <th>ID</th>
                            <th>Contact</th>
                            <th>Type</th>
                            <th colspan="2">Actions</th>
                        </tr>
                        <% for (Contact c : p.getContacts().getListItems()) {%>
                        <tr>
                            <td><%=c.getId()%></td>
                            <td><%=c.getContact()%></td>
                            <td><%=c.getType()%></td>
                            
                            <td style="width: 80px;">
                                <form action="view" method="GET">
                                    <input type="hidden" name="id" value="<%=c.getId()%>">
                                    <input type="hidden" name="page_url" value="form-contact.jsp?person_id=<%=p.getId()%>&id=<%=c.getId()%>">
                                    <input type="submit" name="btn_action" value="Edit">
                                </form>
                            </td>
                            <td style="width: 80px;">
                                <form action="removeContact" method="POST">
                                    <input type="hidden" name="person_id" value="<%=p.getId()%>">
                                    <input type="hidden" name="id" value="<%=c.getId()%>">
                                    <input type="hidden" name="redirect_url" value="contacts.jsp?person_id=<%=p.getId()%>">
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
                        <input type="hidden" name="page_url" value="form-contact.jsp?person_id=<%=p.getId()%>">
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
