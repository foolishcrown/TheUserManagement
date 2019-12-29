<%-- 
    Document   : edit
    Created on : Nov 14, 2019, 11:15:40 AM
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
        <c:if test="${sessionScope.USER.role != 0}">
            <c:redirect url="/index.jsp" />
        </c:if>
        <h1>Account ${requestScope.USER.userID}</h1>
        <form action="UpdateController" method="POST" enctype="multipart/form-data">
            <table border="1" style="border: none">
                <tbody>
                    <tr>
                        <td colspan="2">
                            <img src="images/${requestScope.USER.photo}" width="100" height="100" />
                            <input type="hidden" name="txtPhoto" value="${requestScope.USER.photo}"/>
                            <input type="hidden" name="txtSearch" value="${requestScope.txtSearch}"/>
                            <input type="hidden" name="selectRole" value="${requestScope.selectRole}"/>

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
                            <c:url var="LinkChangePass" value="/LoadPassChangeController">
                                <c:param name="action" value="ChangePass"/>
                                <c:param name="userID" value="${requestScope.USER.userID}"/>
                            </c:url>
                            <a href="${LinkChangePass}"><button type="button">Change Pass</button></a>
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
                        <td>
                            <select name="cboRole" required="" <c:if test="${sessionScope.USER.userID == requestScope.USER.userID}" var="checkOwner">disabled=""</c:if> >

                                <c:forEach items="${requestScope.ROLES}" var="dto">
                                    <c:if test="${requestScope.USER.role == dto.roleID}" var="selected" >
                                        <option selected="" value="${dto.roleID}" >${dto.description}</option>
                                    </c:if>
                                    <c:if test="${!selected}">
                                        <option value="${dto.roleID}" >${dto.description}</option>
                                    </c:if>
                                </c:forEach>

                            </select>
                            <c:if test="${checkOwner}">
                                <input type="hidden" name="cboRole" value="${requestScope.USER.role}"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:url var="LinkBack" value="AdminLoadController">
                                <c:param name="txtSearch" value="${requestScope.txtSearch}"/>
                                <c:param name="selectRole" value="${requestScope.selectRole}"/>

                            </c:url>
                            <a href="${LinkBack}"><button type="button">Back</button></a>
                        </td>
                        <td><button type="submit" name="action" value="Update" >Save</button></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
