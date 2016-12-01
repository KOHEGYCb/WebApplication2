<%@page import="dao.TransportDAO"%>
<%@page import="beans.transport.Transport"%>
<%@page import="dao.RouteDAO"%>
<%@page import="beans.route.Route"%>
<%@page import="java.util.List"%>
<%@page import="beans.route.Station"%>
<%@page import="dao.StationDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="tables/tableRoute.jsp" flush="true"/>
        <br/>
        <form  action="AdminServlet" method="post">
            <input type="submit" name="backToAdmin" value="Back"/>
        </form>
        <h3 class="text">Create Route</h3>
        <form action="RouteServlet" method="post">
            <p>Start station: <select name="startStation">
                    <%
                        List<Station> stations = StationDAO.getINSTANCE().getAllStations();
                        for (int i = 0; i < stations.size(); i++) {
                            int idStation = stations.get(i).getId();
                            String nameStation = stations.get(i).getName();
                    %>
                    <option value="<%= idStation%>"><%= nameStation%></option>
                    <%
                        }
                    %>


                </select></p>
            <p>Final station: <select name="finalStation">
                    <%
                        for (int i = 0; i < stations.size(); i++) {
                            int idStation = stations.get(i).getId();
                            String nameStation = stations.get(i).getName();
                    %>
                    <option value="<%= idStation%>"><%= nameStation%></option>
                    <%
                        }
                    %>


                </select></p>
            <p><input type="submit" name="createRoute" value="Create"/><span>${errorCreate}</span></p>
        </form>

        <h3 class="text">Delete Route</h3>
        <form action="RouteServlet" method="post">
            <p>Route: <select name="idRoute">
                    <%
                        String strDisabled = "disabled";
                        List<Route> routes = RouteDAO.getINSTANCE().getAllRoute();
                        for (int i = 0; i < routes.size(); i++) {
                            boolean disabled = true;
                            List<Transport> transports = TransportDAO.getINSTANCE().getTransportByIdRoute(routes.get(i).getId());
                            if (transports.size() == 0) {
                                disabled = false;
                            }
                            int idRoute = routes.get(i).getId();
                            String startStation = routes.get(i).getStartStation().getName();
                            String finalStation = routes.get(i).getFinalStation().getName();
                    %>
                    <option <% if (disabled) {%> <%= strDisabled%> <%}%> value="<%= idRoute%>"><%= idRoute%>: <%= startStation%> - <%= finalStation%></option>
                    <%
                        }
                    %>
                </select></p>
            <p><input type="submit" name="deleteRoute" value="Delete"/><span>${errorDelete}</span></p>
        </form>

        <h3 class="text">Update Route</h3>
        <form action="RouteServlet" method="post">
            <p>Route: <select name="idRoute">
                    <%
                        for (int i = 0; i < routes.size(); i++) {
                            boolean disabled = true;
                            List<Transport> transports = TransportDAO.getINSTANCE().getTransportByIdRoute(routes.get(i).getId());
                            if (transports.size() == 0) {
                                disabled = false;
                            }
                            int idRoute = routes.get(i).getId();
                            String startStation = routes.get(i).getStartStation().getName();
                            String finalStation = routes.get(i).getFinalStation().getName();
                    %>
                    <option <% if (disabled) {%> <%= strDisabled%> <%}%> value="<%= idRoute%>"><%= idRoute%>: <%= startStation%> - <%= finalStation%></option>
                    <%
                        }
                    %>
                </select></p>
            <p><input type="submit" name="updateRoute" value="Update"/><span>${errorDelete}</span></p>
        </form>
    </body>
</html>
