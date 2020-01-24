<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="service.Service" %>
<%@ page import="service.ServiceIml" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.SQLException" %>
<% Service service = ServiceIml.getInstance();
%>
<%
    try {
        List<User> listUser = service.getAllUsers();
    } catch (SQLException e) {
        e.printStackTrace();
    }%>
<% HttpSession session1 = request.getSession();%>


<html>
<head>
    <title>Admin page</title>
</head>
<body>
<p>Сессия: name = <%= session1.getAttribute("name")%>
    password = <%=session1.getAttribute("password")%>
    role = <%=session1.getAttribute("role")%>
</p>
<p><a href="${pageContext.request.contextPath}/admin.jsp">Перейти на admin.jsp</a></p>
<p><a href="${pageContext.request.contextPath}/user.jsp">Перейти на user.jsp</a></p>
<p>Привет, <%=session1.getAttribute("name")%></p>
<a href="${pageContext.request.contextPath}/logout">Разлогиниться</a>

<div align="center">
    <form action="/registration" method="post">

        <input type="text" name="name" value="name">

        <input type="text" name="password" value="password">

        <select size="1" name="role">
            <option value="Admin">Admin</option>
            <option value="User">User</option>
        </select>
        <input type="submit" value="Добавить юзера" />
    </form>


    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Role</th>

        </tr>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
            <td>

                    <a href="/edit?id=${user.id}&name=${user.name}&password=${user.password}&role=${user.role}">Edit</a>

                    <a href="/delete?id=${user.id}">Delete</a>
                </td>
       </tr>
        </c:forEach>
    </table>
</div>
</form>
<hr/>
</body>
</html>
