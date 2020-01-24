<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% HttpSession session1 = request.getSession();%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    Сессия: name = <%= session1.getAttribute("name")%>
    password = <%=session1.getAttribute("password")%>
    role = <%=session1.getAttribute("role")%>
</p>
<p>
    Привет,    <%=session1.getAttribute("name")%>!
</p>
<p><a href="${pageContext.request.contextPath}/admin.jsp">Перейти на admin.jsp</a></p>
<p><a href="${pageContext.request.contextPath}/logout">Разлогиниться</a></p>
</body>
</html>
