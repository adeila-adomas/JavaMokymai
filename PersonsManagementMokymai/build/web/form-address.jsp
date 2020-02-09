<%@page import="lt.bit.java.services.ListAddresses"%>
<%@page import="lt.bit.java.datamodel.Address"%>
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
        <title>Address</title>
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <%
            Address addr = null;
            Person p = null;
            if (request.getParameter("person_id") != null) {
                
                Integer personId = new Integer(request.getParameter("person_id"));
                p = ListPersons.getPerson(personId);
                
                /* condition to check if id is present for loading already existing address object */
                /* in case if there is no id, then new object is created */
                
                if (request.getParameter("id") != null) {
                    
                    Integer addressId = new Integer(request.getParameter("id"));
                    addr = p.getAddresses().getAddress(addressId);
                        %><h1><%="Edit " + addr.getAddress()%></h1><%
                    } else {
                    %><h1>New Address</h1><%
                        addr = new Address();
                    }
        %>
        <form action="saveAddress" method="post">
            <table style="width:100%; border:1px solid #ccccff;">
                <tr>
                    <td>ID:</td>
                    <td>
                        <input type="hidden" name="person_id" value="<%=p.getId()%>">
                        <input type="hidden" name="id" value="<%=addr.getId()%>"><%=addr.getId()%>
                    </td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="addr_address" value="<%=(addr.getAddress() != null ? addr.getAddress() : "")%>"></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="addr_city" value="<%=addr.getCity() != null ? addr.getCity() : ""%>"></td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td><input type="text" name="addr_postalcode" value="<%=addr.getPostalCode() != null ? addr.getPostalCode() : ""%>"</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="btn_save" value="Save">
                        <input type="hidden" name="referrer_url" value="addresses.jsp?person_id=<%=request.getParameter("person_id")%>">
                        <input type="submit" name="btn_cancel" value="Cancel">
                    </td>
                </tr>
            </table>
        </form>
            <% } /* Closing first condition */ %>
    </body>
</html>
