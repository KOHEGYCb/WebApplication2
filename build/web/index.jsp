<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css"  rel="stylesheet" type="text/css" >
        <title>JSP Page</title>

        <script type="text/javascript">
            function validateForm( )
            {
                valid = true;
                var login = document.getElementById("login");
                if (login.value === "")
                {
                    alert("Write login");
                    valid = false;
                } else {
                    var password = document.getElementById("password");
                    if (password.value === "")
                    {
                        alert("Write password");
                        valid = false;
                    }
                }
                return valid;
            }
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("sessionLogin") != null) {
                session.invalidate();
            }
        %>
        <div class="login">
            <form onsubmit="return validateForm( );" action="StartServlet" method="post">
                <ul>
                    <li><p>Login:</p> <input id="login" type="text" name="login" value="${login}"/><span><br>${loginNotFound}</span></li>

                    <li><p>Password:</p> <input id="password" type="password" name="password"/><span><br>${wrongPassword}</span></li>
                </ul>


                <input type="submit" name="enter" value="Log In"/>

            </form>
            <br/>
            <form action="StartServlet" method="post">
                <input id="register" type="submit" name="register" value="Registration"/>
            </form>
        </div>
    </body>
</html>
