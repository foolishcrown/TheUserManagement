<%-- 
    Document   : insert
    Created on : Nov 14, 2019, 11:48:22 AM
    Author     : Shang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            [type=text],[type=password],[type=email],[type=tel]{
                width: 99%;
            }
            
        </style>
        
    </head>
    <body>
        <c:if test="${sessionScope.USER.role != 0}">
            <c:redirect url="/index.jsp" />
        </c:if>
        <h1>Insert Account</h1>
        

        <form action="InsertController" method="POST" enctype="multipart/form-data">
            <table border="1" style="border: none">
                <tbody>
                    <tr>
                        <th>UserID :</th>
                        <td><input type="text" maxlength="50" required="" name="txtUserID" value="${requestScope.DTO.userID}"/></td>
                        <td style="border: none" ><font color="red">${requestScope.INVALID}</font></td>
                    </tr>
                    <tr>
                        <th>Password :</th>
                        <td><input type="password" maxlength="50" required="" name="txtPassword" id="password"/></td>
                    </tr>
                    <tr>
                        <th>Confirm Password :</th>
                        <td><input type="password" maxlength="50" required="" name="txtConfirm" id="confirm-pass" /></td>
                    </tr>
                    <tr>
                        <th>Username :</th>
                        <td><input type="text" maxlength="50" required="" name="txtUsername" value="${requestScope.DTO.username}"/></td>
                    </tr>
                    <tr>
                        <th>Email :</th>
                        <td><input type="email" maxlength="50" required="" name="txtEmail" value="${requestScope.DTO.email}" /></td>
                    </tr>
                    <tr>
                        <th>Phone :</th>
                        <td><input type="tel" pattern="[0-9]{10}" required="" name="txtPhone" value="${requestScope.DTO.phone}" /></td>
                    </tr>
                    <tr>
                        <th>Photo :</th>
                        <td><input type="file" name="txtPhoto" required="" accept="image/*" /></td>
                    </tr>
                    <tr>
                        <th>Role :</th>
                        <td><select name="cboRole" required="">
                                <c:forEach items="${requestScope.ROLES}" var="dto">
                                    <option value="${dto.roleID}">${dto.description}</option>
                                </c:forEach>
                            </select></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Reset"/></td>
                        <td><input type="submit" name="action" value="Insert"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <c:url var="LinkBack" value="/AdminLoadController"/>
        <a href="${LinkBack}"><< Back to List</a>
       
        <script>
            var password = document.getElementById("password"), confirm = document.getElementById("confirm-pass");
            function validatePassword(){
                if(password.value !== confirm.value){
                    confirm.setCustomValidity("Confirm must be match with Password!");
                }else{
                    confirm.setCustomValidity("");
                }
            }
            password.onchange = validatePassword;
            confirm.onkeyup = validatePassword;
        </script>
        
    </body>
</html>
