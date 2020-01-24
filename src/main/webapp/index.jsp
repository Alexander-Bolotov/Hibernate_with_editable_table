<%@ page import="service.ServiceIml" %>
<%@ page import="service.Service" %>
<%
    Service service = ServiceIml.getInstance();

    HttpSession httpSession = request.getSession();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>Проект 3 предпроектной подготовки Filter User/Admin</h2>
<p>
    Сессия
    Name = <%= session.getAttribute("name")
    %>
    password = <%= session.getAttribute("password")
    %>
    role = <%= session.getAttribute("role")
    %>
</p>
<p><a href="${pageContext.request.contextPath}/admin.jsp">Перейти на admin.jsp</a></p>
<p><a href="${pageContext.request.contextPath}/user.jsp">Перейти на user.jsp</a></p>
<hr/>
    <form method="POST" action="${pageContext.request.contextPath}/login">
    <table>
        <tr>
            <td><label for="loginField">Логин</label></td>
            <td><input id="loginField" type="text" name="name"></td>
        </tr>
        <tr>
            <td><label for="passwordField">Пароль</label></td>
            <td><input id="passwordField" type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="Войти"></td>
        </tr>
    </table>
</form>
</body>
</html>