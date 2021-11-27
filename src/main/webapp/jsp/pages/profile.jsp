
<jsp:useBean id="user" scope="request" type="com.epam.ratingmovies.dao.entity.User"/>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 25.11.2021
  Time: 02:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>${user.email}</h4>
<h1>Ура !!! вы авторизовались и теперь вы в профиле</h1>
</body>
</html>
