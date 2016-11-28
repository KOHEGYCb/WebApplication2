<%@page import="beans.route.Route"%>
<%@page import="dao.RouteDAO"%>
<%@page import="dao.TransportDAO"%>
<%@page import="beans.transport.Transport"%>
<%@page import="beans.driver.Driver"%>
<%@page import="dao.DriversDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Drivers</h2>
        <table>
            <tr>
                <td>Id</td>
                <td>IdDriver</td>
                <td>Surname</td>
                <td>Name</td>
                <td colspan="2">Transport</td>
                <td colspan="2">Route</td>
            </tr>

            <%
                List<Driver> drivers = DriversDAO.getINSTANCE().getAllDriver();

                for (int i = 0; i < drivers.size(); i++) {
                    int id = i + 1;
                    int idDriver = drivers.get(i).getId();
                    String surname = drivers.get(i).getSurname();
                    String name = drivers.get(i).getName();
                    List<Transport> transports = TransportDAO.getINSTANCE().getTransportByIdDriver(drivers.get(i).getId());
                    int rowspan = transports.size();
                    if (rowspan == 0) {
                        rowspan = 1;
                    }
            %>

            <tr class="tableBlock">
                <td rowspan="<%= rowspan%>"><%= id%></td>
                <td rowspan="<%= rowspan%>"><%= idDriver%></td>
                <td rowspan="<%= rowspan%>"><%= surname%></td>
                <td rowspan="<%= rowspan%>"><%= name%></td>

                <%
                    for (int j = 0; j < transports.size(); j++) {
                        int number = transports.get(j).getNumber();
                        String typeTransport = transports.get(j).getStringType();
                        Route route = RouteDAO.getINSTANCE().getRouteById(transports.get(j).getIdRoute());
                        String startStation = RouteDAO.getINSTANCE().getRouteById(transports.get(j).getIdRoute()).getStartStation().getName();
                        String finalStation = RouteDAO.getINSTANCE().getRouteById(transports.get(j).getIdRoute()).getFinalStation().getName();
                %>

                <td class="dopBlock"><%= number%></td>
                <td  class="dopBlock"><%= typeTransport%></td>
                <td  class="dopBlock"><%= startStation%></td>
                <td  class="dopBlock"><%= finalStation%></td>
            </tr>

            <%
                    }
                }
            %>
        </table>
    </body>
</html>
