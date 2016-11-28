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
        <jsp:include page="tables/tableRoute.jsp" flush="true"/>
        <br/>
        <div class="forms">
            <form action="TransportServlet" method="post">
                <span>Number:</span><input type="text" name="number"/><br/>
                <span>idDriver:</span><input type="text" name="idDriver"/><br/>
                <span>idRoute:</span><input type="text" name="idRoute"/><br/>
                <span>Type:</span>
                <span>Bus</span><input type="radio" name="type" value="0"/>
                <span>Trolleybus</span><input type="radio" name="type" value="1"/>
                <span>Tram</span><input type="radio" name="type" value="2"/><br/><br/>
                <input type="submit" name="createTransport" value="Create"/>
                <input type="submit" name="backToAdmin" value="Back">
            </form>
        </div>
    </body>
</html>
