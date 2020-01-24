
<%@ page import="service.ServiceIml" %>
<%@ page import="service.Service" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Service service = ServiceIml.getInstance();
%>

<html>
<head>
    <title>Редактировать User</title>
</head>
<body>
<div align="center">

    <h2>User Edit</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Role</th>
        </tr>

        <form method="post" action="/edit">
                <td>
                   <input type="text" name="id" required value="${id}">
                   </td>
                <td>
                    <input type="text" name="name" required value="${name} ">
                    </td>
                <td>
                    <input type="text" name="password" required value="${password} ">
                </td>
                <td>
                    <input type="text" name="role" required value="${role} ">
                </td>
                <td>
                    <button type="submit" formmethod="post">Отправить</button>
                </td>
        </form>
        </tr>
    </table>


    <div>
</body>
</html>
