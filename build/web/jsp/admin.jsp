<%-- 
    Document   : admin
    Created on : 17.11.2016, 20:58:58
    Author     : Dmitry
--%>

<%@page import="beans.route.Route"%>
<%@page import="dao.RouteDAO"%>
<%@page import="dao.TransportDAO"%>
<%@page import="beans.transport.Transport"%>
<%@page import="dao.DriversDAO"%>
<%@page import="beans.driver.Driver"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String str = "" + session.getAttribute("sessionName");
        if("".equals(session.getAttribute("sessionName"))){
            str = "ljbvharleicmga";
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>
    </head>
    <body>
        <%= str %>
        <h1>Hello Admin</h1>
        <form action="AdminServlet" method="post">
            <input type="submit" name="logout" value="Log out"/>
        </form>
        <br/>
        <!-- Drivers Table -->
        <jsp:include page="admin/tables/tableDriver.jsp" flush="true"/>
        <br/>
        <jsp:include page="admin/tables/tableStation.jsp" flush="true"/>
        <br/>
        <jsp:include page="admin/tables/tableRoute.jsp" flush="true"/>
        <br/>
        <form action="AdminServlet" method="post">
            <input type="submit" name="Route" value="Route"/>
            <input type="submit" name="Transport" value="Transport"/>
            <input type="submit" name="Station" value="Station"/>
            <input type="submit" name="Driver" value="Delete driver"/>
        </form>
        <br/>
        <br/>
        <%--<jsp:include page="user.jsp" flush="true"/>--%>
    </body>
</html>
