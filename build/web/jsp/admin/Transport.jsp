<%@page import="dao.TransportDAO"%>
<%@page import="beans.transport.Transport"%>
<%@page import="dao.RouteDAO"%>
<%@page import="beans.route.Route"%>
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
        <%--<jsp:include page="tables/tableDriver.jsp" flush="true"/>--%>
        <br/>
        <%--<jsp:include page="tables/tableRoute.jsp" flush="true"/>--%>
        <br/>
        <div class="forms">
            <form  action="AdminServlet" method="post">
                <input type="submit" name="backToAdmin" value="Back"/>
            </form>
            <form action="TransportServlet" method="post">
                <p>Create</p>
                <span>Number:</span><input type="text" name="number"/><br/>



                <p>Driver: <select name="idDriver">
                        <%
                            List<Driver> drivers = DriversDAO.getINSTANCE().getAllDriver();
                            for (int i = 0; i < drivers.size(); i++) {

                                int idDriver = drivers.get(i).getId();
                                String name = drivers.get(i).getName();
                                String surname = drivers.get(i).getSurname();
                        %>
                        <option value="<%= idDriver%>"><%= idDriver%>: <%= name%> <%= surname%></option>
                        <%
                            }
                        %>
                    </select></p>




                <p>Route: <select name="idRoute">
                        <%
                            List<Route> routes = RouteDAO.getINSTANCE().getAllRoute();
                            for (int i = 0; i < routes.size(); i++) {

                                int idRoute = routes.get(i).getId();
                                String startStation = routes.get(i).getStartStation().getName();
                                String finalStation = routes.get(i).getFinalStation().getName();
                        %>
                        <option value="<%= idRoute%>"><%= idRoute%>: <%= startStation%> <%= finalStation%></option>
                        <%
                            }
                        %>
                    </select></p>
                <p>Type:</p>
                <input type="radio" name="type" value="0"/><span>Bus</span><br>
                <input type="radio" name="type" value="1"/><span>Trolleybus</span><br/>
                <input type="radio" name="type" value="2"/><span>Tram</span><br/><br/>
                <input type="submit" name="createTransport" value="Create"/>${createError}
            </form>
            <br/><br/>
            <form action="TransportServlet" method="post">
                <p>Delete</p>



                <%
                    List<Transport> transports = TransportDAO.getINSTANCE().getAllTransport();
                    if (transports.isEmpty()) {
                %>
                <p>No Transport</p>
                <%
                } else {
                %>
                <p>Number: <select name="idTransport">        
                        <%
                            for (int i = 0; i < transports.size(); i++) {
                                int idTransport = transports.get(i).getId();
                                int number = transports.get(i).getNumber();
                                String type = transports.get(i).getStringType();
                        %>
                        <option value="<%= idTransport%>"><%= idTransport%>:<%= number%>: <%= type%></option>
                        <%
                            }
                        %>
                    </select>
                    <input type="submit" name="deleteTransport" value="Delete"/>${deleteError}

                    <%
                        }
                    %>
            </form>
        </div>
    </body>
</html>
