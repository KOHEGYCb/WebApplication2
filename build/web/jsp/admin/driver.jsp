<%@page import="dao.TransportDAO"%>
<%@page import="beans.transport.Transport"%>
<%@page import="java.util.List"%>
<%@page import="beans.driver.Driver"%>
<%@page import="dao.DriversDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="tables/tableDriver.jsp" flush="true"/>
        <br/>
        <form  action="AdminServlet" method="post">
            <input type="submit" name="backToAdmin" value="Back"/>
        </form>
        <form action="UserServlet" method="post">
            <p>Driver: <select name="idDriver">
                    <%
                        String strDisabled = "disabled";
                        List<Driver> drivers = DriversDAO.getINSTANCE().getAllDriver();
                        for (int i = 0; i < drivers.size(); i++) {
                            boolean disabled = true;
                            List<Transport> transports = TransportDAO.getINSTANCE().getTransportByIdDriver(drivers.get(i).getId());
                            if (transports.size() == 0) {
                                disabled = false;
                            }
                            int idDriver = drivers.get(i).getId();
                            String name = drivers.get(i).getName();
                            String surname = drivers.get(i).getSurname();
                    %>
                    <option <% if (disabled) {%> <%= strDisabled%> <%}%> value="<%= idDriver%>"><%= idDriver %>: <%= name %> <%= surname %></option>
                    <%
                        }
                    %>
                </select></p>
            <p><input type="submit" name="deleteDriver" value="Delete"/><span>${errorDelete}</span></p>
        </form>
    </body>
</html>



