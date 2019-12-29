<%-- 
    Document   : changepass
    Created on : Nov 20, 2019, 8:20:28 AM
    Author     : Shang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.USER.role != 1}">
            <c:redirect url="/index.jsp" />
        </c:if>
        <h1>Account : ${sessionScope.USER.userID}</h1>
        <font color="red">${requestScope.INVALID}</font>
        <form action="${pageContext.request.getContextPath()}/MainController" method="POST">
            <table border="1">

                <tbody>
                    <tr>
                        <th>Old Password: </th>
                        <td><input type="password" name="oldPass"  required="" />
                            <input type="hidden" name="userID" value="${sessionScope.USER.userID}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>New Password: </th>
                        <td><input type="password" name="newPass" required="" id="password" /></td>
                    </tr>
                    <tr>
                        <th>Confirm Password: </th>
                        <td><input type="password" name="confirm" required="" id="confirm-pass"</td>
                    </tr>
                    <tr>
                        <td><button type="submit" name="action" value="ChangePassSub">Save Change</button></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <c:url var="LinkBack" value="/SubLoadController"/>
        <a href="${LinkBack}"><< Go Back</a>

        <script>
            var password = document.getElementById("password"), confirm = document.getElementById("confirm-pass");
            function validatePassword() {
                if (password.value !== confirm.value) {
                    confirm.setCustomValidity("Confirm must be match with Password!");
                } else {
                    confirm.setCustomValidity("");
                }
            }
            password.onchange = validatePassword;
            confirm.onkeyup = validatePassword;
        </script>

    </body>
</html>
