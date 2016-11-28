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
                    var name = document.getElementById("name");
                    if (name.value === "")
                    {
                        alert("Write name");
                        valid = false;
                    } else {
                        var surname = document.getElementById("surname");
                        if (surname.value === "")
                        {
                            alert("Write surname");
                            valid = false;
                        } else {
                            var password = document.getElementById("password");
                            if (password.value === "") {
                                alert("Write password");
                                valid = false;
                            }
                        }
                    }
                }
                return valid;
            }
        </script>
    </head>
    <body>
        <div class="login">
            <h1>Registration</h1>
            <form onsubmit="return validateForm( );" action="RegistrationServlet" method="post">
                <span>Login:</span> <input id="login" type="text" name="login" value="${setLogin}"/><br/><span>${badLogin}</span><br/><br/>
                <span>Name:</span> <input id="name" type="text" name="name" value="${setName}"/><br/><span>${badName}</span><br/><br/>
                <span>Surname:</span> <input id="surname" type="text" name="surname" value="${setSurname}"/><br/><span>${badSurname}</span><br/><br/>
                <span>Password:</span> <input id="password" type="password" name="password"/><br/><span>${badPassword}</span><br/><br/>

                <input type="submit" name="register" value="Registration"/>
            </form>
                <br/>
            <form action="RegistrationServlet" method="post">
                <input type="submit" name="login" value="Log in"/>
            </form>
        </div>

        <!--        <div class="registration">
                    <div class="names">
                        <p>Login: </p>
                        <p>Name: </p>
                        <p>Surname: </p>
                        <p>Password: </p>
                    </div>
                    <div class="fields">
                            <form action="RegistrationServlet" method="post">
                    <input type="text" name="login"/><br/>
                    <input type="text" name="name"/><br/>
                    <input type="text" name="surname"/><br/>
                    <input type="password" name="password"/><br/>
                    
                    <input type="submit" name="register" value="Registration"/>
                    
                        </form>
                    </div>
                </div>-->
    </body>
</html>
