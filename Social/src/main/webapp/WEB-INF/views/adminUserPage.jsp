<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="">
					<ul class="nav navbar-nav">
						<li><a href="/admin/userPage">UserPage</a></li>
						<li><a href="/admin/post">Post</a></li>
						<li><a href="/admin/groupe">Groupe</a></li>
						<li><a href="/admin/message">Message</a></li>
						<li><a href="/">Back to UserPage</a></li>
					</ul>
				</div>
			</div>
			</nav>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown">Sort</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name" />
						<custom:sort innerHtml="Name desc" paramValue="name,desc" />
						<custom:sort innerHtml="Surname asc" paramValue="surname" />
						<custom:sort innerHtml="Surname desc" paramValue="surname,desc" />
						<custom:sort innerHtml="Email asc" paramValue="email" />
						<custom:sort innerHtml="Email desc" paramValue="email,desc" />
						<custom:sort innerHtml="Phone asc" paramValue="phone" />
						<custom:sort innerHtml="Phone desc" paramValue="phone,desc" />
						<custom:sort innerHtml="Country name asc"
							paramValue="country.name" />
						<custom:sort innerHtml="Country name desc"
							paramValue="country.name,desc" />
						<custom:sort innerHtml="City asc" paramValue="city" />
						<custom:sort innerHtml="City desc" paramValue="city,desc" />
						<custom:sort innerHtml="Sex name asc" paramValue="sex.name" />
						<custom:sort innerHtml="Sex name desc" paramValue="sex.name,desc" />
						<custom:sort innerHtml="DayOfBirth name asc"
							paramValue="dayOfBirth.name" />
						<custom:sort innerHtml="DayOfBirth name desc"
							paramValue="dayOfBirth.name,desc" />
						<custom:sort innerHtml="MounthOfBirth name asc"
							paramValue="mounthOfBirth.name" />
						<custom:sort innerHtml="MounthOfBirth name desc"
							paramValue="mounthOfBirth.name,desc" />
						<custom:sort innerHtml="YearOfBirth name asc"
							paramValue="yearOfBirth.name" />
						<custom:sort innerHtml="YearOfBirth name desc"
							paramValue="yearOfBirth.name,desc" />
					</ul>
				</div>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
				<custom:size posibleSizes="1,2,5,10,20" size="${userPages.size}"
					title="Size" />
			</div>
			<br> <br> <br> <br>
			<form:form action="/admin/userPage" class="form-inline" method="get"
				modelAttribute="filter">
				<custom:hiddenInputs
					excludeParams=" name,surname, email, password, phone, country,city,  countryIds, sexIds, _sexIds,
					 _countryIds, dayOfBirthIds, _dayOfBirth, mounthOfBirth, _mounthOfBirth,yearOfBirth, _yearOfBirth" />
				<div class="form-group">
					<form:input path="name" placeholder="name" class="form-control" />
					<form:input path="surname" placeholder="surname"
						class="form-control" />
					<form:input path="email" placeholder="email" class="form-control" />
					<form:input path="phone" placeholder="phone" class="form-control" />
					<form:input path="city" placeholder="city" class="form-control" />
				</div>
				<div class="form-group">
					<h4>Country</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${countries}" path="countryIds"
						itemLabel="name" itemValue="id" />
				</div>
				<div class="form-group">
					<h4>Sex</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${sexs}" path="sexIds" itemLabel="name"
						itemValue="id" />
				</div>
				<div>
					<h4>DaysOfBirth</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${daysOfBirth}" path="dayOfBirthIds"
						itemLabel="name" itemValue="id" />
				</div>
				<div>
					<h4>MounthOfBirth</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${mounsesOfBirth}" path="mounthOfBirthIds"
						itemLabel="name" itemValue="id" />
				</div>
				<div>
					<h4>YearOfBirth</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${yearsOfBirth}" path="yearOfBirthIds"
						itemLabel="name" itemValue="id" />
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Find</button>
				</div>
			</form:form>
		</div>
		<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
			<form:form class="form-inline" action="/admin/userPage" method="post"
				modelAttribute="form">
				<form:hidden path="id" />
				<custom:hiddenInputs
					excludeParams="name, surname, email, password, phone, country,city, dayOfBirth,mounthOfBirth,yearOfBirth, id" />
				<div class="form-group">
					<label for="login"><form:errors path="login" /></label>
					<form:input path="login" class="form-control" placeholder="login" />
					<label for="name"><form:errors path="name" /></label>
					<form:input path="name" class="form-control" placeholder="name" />
					<label for="surname"><form:errors path="surname" /></label>
					<form:input path="surname" class="form-control"
						placeholder="surname" />
					<label for="email"><form:errors path="email" /></label>
					<form:input path="email" class="form-control" placeholder="email" />
					<label for="password"><form:errors path="password" /></label>
					<form:input path="password" type="password" class="form-control"
						placeholder="password" />
					<label for="phone"><form:errors path="phone" /></label>
					<form:input path="phone" class="form-control" placeholder="phone" />
					<label for="city"><form:errors path="city" /></label>
					<form:input path="city" class="form-control" placeholder="city" />
					<form:select path="country" items="${countries}" itemLabel="name"
						itemValue="id">
					</form:select>
					<form:select path="sex" items="${sexs}" itemLabel="name"
						itemValue="id">
					</form:select>
					<form:select path="dayOfBirth" items="${daysOfBirth}"
						itemLabel="name" itemValue="id">
					</form:select>
					<form:select path="mounthOfBirth" items="${mounsesOfBirth}"
						itemLabel="name" itemValue="id">
					</form:select>
					<form:select path="yearOfBirth" items="${yearsOfBirth}"
						itemLabel="name" itemValue="id">
					</form:select>
					<button type="submit" class="btn btn-primary">Create</button>
				</div>
			</form:form>
			<div class="row">
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Name</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Surname</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Email</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Phone</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Country</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">City</div>
				<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">Sex</div>
				<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">DateOfBirth</div>
			</div>
			<c:forEach items="${userPages.content}" var="userPage">
				<div class="row">
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.surname}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.email}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.phone}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.country.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.city}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.sex.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.dayOfBirth.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.mounthOfBirth.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">${userPage.yearOfBirth.name}</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
						<a href="/admin/userPage/delete/${userPage.id}<custom:allParams/>">delete</a>
					</div>
					<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
						<a href="/admin/userPage/update/${userPage.id}<custom:allParams/>">update</a>
					</div>
				</div>
			</c:forEach>
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
				<custom:pageable page="${userPages}" cell="<li></li>"
					container="<ul class='pagination'></ul>" />
			</div>
		</div>
	</div>
</body>
</html>