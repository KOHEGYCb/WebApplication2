<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="tables/tableStation.jsp" flush="true"/>
        <p class="text">Create new Station</p>
        <form action="StationServlet" method="post">
            <span>Station name</span>
            <input type="text" name="stationName" value=""/><br/><br/>
            <input type="submit" name="createStation" value="Create">
        </form>
        <p class="text">Delete Station</p>
        <form action="StationServlet" method="post">
            <span>Station name</span>
            <input type="text" name="stationName" value=""/><br/><br/>
            <input type="submit" name="deleteStation" value="Delete">
        </form>
        <p class="text">Update Station</p>
        <form action="StationServlet" method="post">
            <span>Old station name</span><input type="text" name="oldStationName" value=""/><br/><br/>
            <span>New station name</span><input type="text" name="newStationName" value=""/><br/><br/>
            <input type="submit" name="updateStation" value="Update">
            <input type="submit" name="backToAdmin" value="Back"/>
        </form>
        <br/><br/>
    </body>
</html>
