<%--
  Created by IntelliJ IDEA.
  User: THE HUY
  Date: 9/4/2018
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
	<center>
		<form action="login" method="post">
		    <label>User</label>
		    <input type="email" name="username" required placeholder="abc@gmail.com"><br><br>
		    <label>Pass</label>
		    <input type="password" name="password" required placeholder="******"><br><br>
		    <button type="submit" style="margin-left: 30px;">Đăng nhập</button>
		</form>
	</center>
</body>
</html>
