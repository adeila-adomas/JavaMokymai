<%@page import="lt.bit.java.datamodel.Contact"%>
<%@page import="lt.bit.java.services.ListAddresses"%>
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
        <title>Contact</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <%
            Contact c = null;
            Person p = null;
            if (request.getParameter("person_id") != null) {
                
                Integer personId = new Integer(request.getParameter("person_id"));
                p = ListPersons.getPerson(personId);
                
                /* condition to check if id is present for loading already existing address object */
                /* in case if there is no id, then new object is created */
                
                if (request.getParameter("id") != null) {
                    
                    Integer contactId = new Integer(request.getParameter("id"));
                    c = p.getContacts().getContact(contactId);
                        %><h1><%="Edit " + c.getContact()%></h1><%
                    } else {
                    %><h1>New Address</h1><%
                        c = new Contact();
                    }
        %>
        <form action="saveContact" method="post">
            <table style="width:100%; border:1px solid #ccccff;">
                <tr>
                    <td>ID:</td>
                    <td>
                        <input type="hidden" name="person_id" value="<%=p.getId()%>">
                        <input type="hidden" name="id" value="<%=c.getId()%>"><%=c.getId()%>
                    </td>
                </tr>
                <tr>
                    <td>Contact: </td>
                    <td><input type="text" name="contact" value="<%=(c.getContact() != null ? c.getContact() : "")%>"></td>
                </tr>
                <tr>
                    <td>Type: </td>
                    <td><input type="text" name="type" value="<%=c.getType()!= null ? c.getType(): ""%>"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="btn_save" value="Save">
                        <input type="hidden" name="referrer_url" value="contacts.jsp?person_id=<%=request.getParameter("person_id")%>">
                        <input type="submit" name="btn_cancel" value="Cancel">
                    </td>
                </tr>
            </table>
        </form>
            <% } /* Closing first condition */ %>
    </body>
</html>
