<%-- 
    Document   : promotion
    Created on : Nov 19, 2019, 4:48:35 PM
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
        <h1>Promotion List</h1>
        <c:if test="${sessionScope.USER.role != 0}">
            <c:redirect url="/index.jsp" />
        </c:if>
        <c:if test="${sessionScope.PROLIST != null}" var="checkNull">
            <c:if test="${not empty sessionScope.PROLIST.getProList()}" var="checkEmpty">
                <form action="${pageContext.request.getContextPath()}/MainController" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>UserID</th>
                                <th>Rank</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.PROLIST.getProList()}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.userID}
                                        <input type="hidden" name="userID" value="${dto.userID}"/>
                                    </td>
                                    <td><input type="number" max="10" min="1" required="" value="${dto.rank}" name="rank"/></td>
                                    <td>
                                        <c:url var="LinkDelete" value="/MainController">
                                            <c:param name="action" value="DeletePromotion"/>
                                            <c:param name="userID" value="${dto.userID}"/>
                                        </c:url>
                                        <a href="${LinkDelete}"><button type="button">Delete</button></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><input type="submit" value="UpdatePromotion" name="action"/> </td>
                                <td>
                                    <c:url var="LinkConfirmDB" value="/MainController">
                                        <c:param name="action" value="ConfirmDB" />
                                    </c:url>
                                    <a href="${LinkConfirmDB}"><button type="button" >Confirm to DB</button></a>
                                    
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </form>

            </c:if>
            <c:if test="${!checkEmpty}">
                Empty List
            </c:if>
        </c:if>
        <c:if test="${!checkNull}">
            Please Add Promotion!
        </c:if>
        <br/>

        Status:<font color="red">${requestScope.INVALID}</font>
        <font color="green">${requestScope.MSG}</font>
        <br/>
        <br/>
        <br/>
        <c:url var="LinkBack" value="/AdminLoadController" />
        <a href="${LinkBack}"><< Back to main</a>
    </body>
</html>
