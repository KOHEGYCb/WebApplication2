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
        
        <form action="UserServlet" method="post">
            <span>idDriver</span><input type="text" name="idDriver"/><br/><br/>
            <input type="submit" name="delete" value="Delete"/>
            <input type="submit" name="backToAdmin" value="Back">
        </form>
    </body>
</html>



