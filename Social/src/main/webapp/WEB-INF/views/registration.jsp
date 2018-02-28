<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test="${param.fail}">
				<div class="col-md-12 col-xs-12">
					<p style="color: red;">Fail</p>
				</div>
			</c:if>
			<form:form action="/registration" class="form-group" method="post"
				modelAttribute="form" enctype="multipart/form-data">
				<form:hidden path="id" />
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<h3>REGESTRATION</h3>
					<label for="login"><form:errors path="login" /></label>
					<form:input path="login" class="form-control" placeholder="login" />
					<label for="name"><form:errors path="name" /></label>
					<form:input path="name" class="form-control" placeholder="name" />
					<label for="surname"><form:errors path="surname" /></label>
					<form:input path="surname" class="form-control"
						placeholder="surname" />
					<label for="email"><form:errors path="email" /></label>
					<form:input path="email" class="form-control" placeholder="email" />
					<label for="password"><form:errors path="password" /></label> <input
						name="password" type="password" placeholder="password"
						class="form-control" /> <label for="phone"><form:errors
							path="phone" /></label>
					<form:input path="phone" class="form-control" placeholder="phone" />
					<div>
						<br>
						<h4>Sex:</h4>
						<form:select path="gender" items="${sexs}"></form:select>
					</div>
					<div>
						<h4>Addres</h4>
					</div>
					<p>
						Country:
						<form:select path="country" items="${countries}">
						</form:select>
					</p>
					<label for="city"><form:errors path="city" /></label>
					<form:input path="city" class="form-control" placeholder="city" />
					<div>
						<h4>Date Of Birth</h4>
					</div>
					<form:select path="dayOfBirth" items="${daysOfBirth}"
						itemLabel="id"></form:select>
					<form:select path="mounthOfBirth" items="${mounsesOfBirth}"></form:select>
					<form:select path="yearOfBirth" items="${yearsOfBirth}"
						itemLabel="id"></form:select>
					<h4>Add Photo</h4>
					<input type="file" name="file"> <br> <br>
					<button type="submit" class="btn btn-primary">Create</button>
					<h3>You are already registrated?</h3>
					<h3 class="azx">
						<a href="/login">SingIn</a>
					</h3>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>