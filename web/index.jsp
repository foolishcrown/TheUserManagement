<%-- 
    Document   : index
    Created on : Nov 12, 2019, 1:54:42 PM
    Author     : Shang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            <font color="red">${requestScope.INVALID}</font>
            <table border="1">
                <tbody>
                    <tr>
                        <th>User ID:</th>
                        <td><input type="text" name="userID"  /></td>
                    </tr>
                    <tr>
                        <th>Password:</th>
                        <td><input type="password" name="password"  /></td>
                    </tr>
                    <tr>
                        <th colspan="2"><input type="submit" value="Login" name="action" style="width: 100%" /></th>
                    </tr>
                </tbody>
            </table>

        </form>

        

    </body>
</html>
