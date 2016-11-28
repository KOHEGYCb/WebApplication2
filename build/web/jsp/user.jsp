<%@page import="dao.RouteDAO"%>
<%@page import="beans.driver.Driver"%>
<%@page import="dao.DriversDAO"%>
<%@page import="dao.TransportDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.transport.Transport"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>
    </head>
    <body>
        <p>Hello Driver <span>${sessionSurname} ${sessionName}</span></p>
        <form action="UserServlet" method="post">
            <input type="submit" name="logout" value="Log out">
        </form>
        <br/>
        <table>
            <tr>
                <td>Id</td>
                <td>Number</td>
                <td>Type Transport</td>
                <td>Start Station</td>
                <td>Final Station</td>
            </tr>

            <%
                Driver driver = DriversDAO.getINSTANCE().getDriverByLogin("" + session.getAttribute("sessionLogin"));
                List<Transport> transports = TransportDAO.getINSTANCE().getTransportByIdDriver(driver.getId());
                for (int i = 0; i < transports.size(); i++) {
                    String type = "";
                    switch (transports.get(i).getType()) {
                        case 0:
                            type = "Bus";
                            break;
                        case 1:
                            type = "Trolleybus";
                            break;
                        case 2:
                            type = "Trum";
                            break;
                        default:
                            break;
                    }
                    String startStation = RouteDAO.getINSTANCE().getRouteById(transports.get(i).getIdRoute()).getStartStation().getName();
                    String finalStation = RouteDAO.getINSTANCE().getRouteById(transports.get(i).getIdRoute()).getFinalStation().getName();
            %>
            <tr>
                <td><%= i+1%></td>
                <td><%= transports.get(i).getNumber()%></td>
                <td><%= type%></td>
                <td><%= startStation%></td>
                <td><%= finalStation%></td>
            </tr>
            <%
                }
            %>
        </table>

    </body>
</html>
