<%-- 
    Document   : sub
    Created on : Nov 12, 2019, 5:22:15 PM
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
        <h1>Welcome ${sessionScope.USER.username}</h1>
        <c:url var="LogoutLink" value="LogoutController"/>
        <a href="${LogoutLink}">Logout</a>
        <br/>
        <br/>


        <form action="UpdateSubController" method="POST" enctype="multipart/form-data">
            <table border="1" style="border: none">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <img src="images/${requestScope.USER.photo}" width="100" height="100" />
                            <input type="hidden" name="txtPhoto" value="${requestScope.USER.photo}"/>
                        </td>
                        <td style="border: none"><input type="file" name="upload" accept="image/*" /></td>
                    </tr>
                    <tr>
                        <th>UserID :</th>
                        <td><input type="text" name="txtUserID" value="${requestScope.USER.userID}" readonly="" required=""/></td>
                    </tr>
                    <tr>
                        <th>Username :</th>
                        <td><input type="text" name="txtUsername" maxlength="50" value="${requestScope.USER.username}" required="" /></td>
                    </tr>
                    <tr>
                        <th>Password :</th>
                        <td><input type="password" maxlength="50" name="txtPassword" value="${requestScope.USER.password}" required=""/></td>
                        <td style="border: none">

                            <a href="sub/changepass.jsp"><button type="button">Change Pass</button></a>
                        </td>
                    </tr>
                    <tr>
                        <th>Email :</th>
                        <td><input type="email" maxlength="50" name="txtEmail" value="${requestScope.USER.email}" required="" /></td>
                    </tr>
                    <tr>
                        <th>Phone :</th>
                        <td><input type="tel" accept="[0-9]{10}" name="txtPhone" value="${requestScope.USER.phone}" required=""/></td>
                    </tr>
                    <tr>
                        <th>Role :</th>
                        <td><select name="cboRole" required="">
                                <c:forEach items="${requestScope.ROLES}" var="dto">
                                    <c:if test="${dto.roleID != 0}">

                                        <c:if test="${requestScope.USER.role == dto.roleID}" var="selected" >
                                            <option selected="" value="${dto.roleID}" >${dto.description}</option>
                                        </c:if>
                                        <c:if test="${!selected}">
                                            <option value="${dto.roleID}" >${dto.description}</option>
                                        </c:if>

                                    </c:if>

                                </c:forEach>
                            </select></td>
                    </tr>
                    <tr>

                        <td><button type="submit" name="action" value="UpdateSub" >Save</button></td>
                    </tr>
                </tbody>
            </table>
        </form>


        Status:<font color="red">${requestScope.INVALID}</font>
        <font color="green">${requestScope.MSG}</font><br/>

        <br/>

    </body>
</html>
