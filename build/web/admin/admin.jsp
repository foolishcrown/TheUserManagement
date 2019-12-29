<%-- 
    Document   : admin
    Created on : Nov 12, 2019, 5:19:25 PM
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
        <h1>Welcome ${sessionScope.USER.username}</h1>
        <c:url var="LogoutLink" value="LogoutController"/>
        <a href="${LogoutLink}">Logout</a>
        <br/>
        <br/>
        <c:url var="LinkInsert" value="LoadRoleInsertController"/>
        <a href="${LinkInsert}">Insert new account</a>
        <br/>
        <br/>
        <c:url var="LinkHistory" value="ViewHistoryPromotion" />
        <a href="${LinkHistory}">View Promotion History</a>
        <br/>
        <br/>
        <br/>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtSearch" value="${requestScope.txtSearch}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <c:if test="${requestScope.ROLES != null}">
            <c:url var="LinkAll" value="AdminLoadController"/>
            <a href="${LinkAll}" id="all">All (${requestScope.SUM})</a> || 
            <c:if test="${not empty requestScope.ROLES}">
                <c:forEach items="${requestScope.ROLES}" var="roleDTO" varStatus="counter">
                    <c:url var="LinkSelect" value="AdminLoadController">
                        <c:param name="selectRole" value="${roleDTO.roleID}"/>
                    </c:url>
                    <a href="${LinkSelect}">${counter.count}.${roleDTO.description}(${roleDTO.count})</a>
                    <input type="hidden" class="roleCount" value="${roleDTO.count}"/>
                </c:forEach>
            </c:if>
        </c:if>

        <c:if test="${requestScope.ACCOUNTS != null}" >
            <c:if test="${not empty requestScope.ACCOUNTS}" var="checkEmpty">
                <table border="1" style="">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Picture</th>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Edit</th>
                            <th>Delete</th>
                            <th>Add To
                                <br/>Promotion</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.ACCOUNTS}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><img src="images/${dto.photo}" style="width: 60px;height: 60px" /></td>
                                <td>${dto.userID}</td>
                                <td>${dto.username}</td>
                                <td>
                                    <c:url var="LinkEdit" value="MainController">
                                        <c:param value="Edit" name="action"/>
                                        <c:param value="${dto.userID}" name="userID"/>
                                        <c:param value="${dto.role}" name="selectRole"/>
                                        <c:param value="${param.txtSearch}" name="txtSearch"/>
                                    </c:url>
                                    <a href="${LinkEdit}"> edit</a>
                                </td>
                                <td>
                                    <c:url var="LinkDelete" value="MainController">
                                        <c:param value="Delete" name="action" />
                                        <c:param value="${dto.userID}" name="userID" />
                                        <c:param value="${dto.role}" name="selectRole" />
                                        <c:param value="${param.txtSearch}" name="txtSearch"/>
                                    </c:url>
                                    <c:if test="${sessionScope.USER.userID != dto.userID}">
                                        <a href="${LinkDelete}"> delete</a>
                                    </c:if>
                                </td>
                                <td><c:url var="LinkAdd" value="MainController">
                                        <c:param value="AddPromotion" name="action" />
                                        <c:param name="userID" value="${dto.userID}"/>
                                        <c:param value="${dto.role}" name="selectRole" />
                                        <c:param value="${param.txtSearch}" name="txtSearch"/>
                                    </c:url>
                                    <a href="${LinkAdd}"><button type="button">Add</button></a>
                                </td>
                            </tr>
                        </c:forEach> 

                    </tbody>
                </table>

            </c:if>
            <c:if test="${!checkEmpty}">
                Empty List
            </c:if>
        </c:if>
                Status:<font color="red">${requestScope.INVALID}</font>
                <font color="green">${requestScope.MSG}</font><br/>
                <a href="admin/promotion.jsp">>>>View Promotion List</a>    
                <br/>
                
        <script>



        </script>       

    </body>
</html>
