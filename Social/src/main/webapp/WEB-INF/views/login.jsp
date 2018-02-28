<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="azx">
				<p>
				<h3>For SingIn you need to enter your login and password</h3>
				<h3>Login</h3>
				<c:if test="${param.fail}">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<p style="color: red;">Fail</p>
					</div>
				</c:if>
				<form:form action="/login" class="form-inline" method="post">
					<div class="form-group">
						<input name="login" placeholder="Login" class="form-control" />
						<h3>Password:</h3>
						<input name="password" type="password" placeholder="********" class="form-control" /><br> <br>
						<button type="submit" class="btn btn-primary">LogIn</button>
					</div>
				</form:form>
				<h3>Still not registered? Registrate now!</h3>
				<h3>
					<a href="/registration">Sing Up</a>
				</h3>
			</div>
		</div>
	</div>
</body>
</html>