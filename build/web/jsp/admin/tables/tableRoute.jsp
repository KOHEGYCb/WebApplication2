<%-- 
    Document   : unusedRoute
    Created on : 24.11.2016, 14:24:15
    Author     : Dmitry
--%>

<%@page import="dao.RouteDAO"%>
<%@page import="beans.route.Route"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Routes</h2>

        <table>
            <tr>
                <td>IdRoute</td>
                <td>Start station</td>
                <td>Final Station</td>
            </tr>

            <%
                List<Route> routes = RouteDAO.getINSTANCE().getAllRoute();
                for (int i = 0; i < routes.size(); i++) {
                    int id = routes.get(i).getId();
                    String startStation = routes.get(i).getStartStation().getName();
                    String finalStation = routes.get(i).getFinalStation().getName();
            %>
            <tr>
                <td><%= id %></td>
                <td><%= startStation %></td>
                <td><%= finalStation %></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
