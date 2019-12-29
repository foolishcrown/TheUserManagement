<%-- 
    Document   : history
    Created on : Nov 21, 2019, 12:24:20 AM
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
        <h1>History Promotion List</h1>
        <c:if test="${sessionScope.USER.role != 0}">
            <c:redirect url="/index.jsp" />
        </c:if>
        <c:if test="${requestScope.HISTORY != null}">
            <c:if test="${not empty requestScope.HISTORY}" var="checkEmpty">


                <table border="1">
                    <thead>
                        <tr>
                            <th>Newest</th>
                            <th>UserID</th>
                            <th>Rank</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.HISTORY}" var="dto">
                            <tr>
                                <td>${dto.dateOfCreate}</td>
                                <td>${dto.userID}</td>
                                <td>${dto.rank}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Oldest</th>
                            <th>UserID</th>
                            <th>Rank</th>
                        </tr>
                    </tfoot>
                </table>
            </c:if>
            <c:if test="${!checkEmpty}">
                <font color="red">Empty History</font>
            </c:if>
            <c:url var="LinkBack" value="/AdminLoadController"/>
            <a href="${LinkBack}"><< Back to List</a>
        </c:if>
    </body>
</html>
