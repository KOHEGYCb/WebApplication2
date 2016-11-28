<%@page import="dao.StationDAO"%>
<%@page import="java.util.List"%>
<%@page import="beans.route.Station"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Stations</h2>
        <table>
            <tr>
                <td>Id</td>
                <td>IdStation</td>
                <td>Name</td>
            </tr>

            <%
                List<Station> stations = StationDAO.getINSTANCE().getAllStations();

                for (int i = 0; i < stations.size(); i++) {
                    int id = i + 1;
                    int idStation = stations.get(i).getId();
                    String name = stations.get(i).getName();
            %>

            <tr>
                <td><%= id%></td>
                <td><%= idStation%></td>
                <td><%= name%></td>
            </tr>

            <%
                    }
            %>
        </table>
    </body>
</html>
