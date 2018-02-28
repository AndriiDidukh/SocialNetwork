<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<security:authorize access="!isAuthenticated()">
		<div class="azx">
			<h3>To view this page you need to SignIn</h3>
			<h3>
				<a href="/login">SingIn</a>
			</h3>
			<h3>Still not registered? Register now!</h3>
			<h3>
				<a href="/registration">Register</a>
			</h3>
		</div>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<div class="container">
			<div class="row">
				<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					<div class="leftsize">
						<div class="logo">
							<img class="img-thumbnail" width="100%"
								src="/resources/image/network.png?version=1" />
						</div>
						<div class="MainMenu">
							<ul class=leftul>
								<li><a href="/">My Page</a></li>
								<li><a href="/friends">My Friends</a></li>
								<li><a href="/message">My Messages</a></li>
								<li><a href="/groupe">My Groups</a></li>
								<li><a href="/news">My News</a></li>
								<li><a href="/post">My Posts</a></li>
								<security:authorize
									access="isAuthenticated()and hasRole('ROLE_ADMIN')">
									<li><a href="/admin">Admin panel</a></li>
								</security:authorize>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9"
					style="box-shadow: 0 0 0 4px green;">
					<div class="Header">
						<div class="name">
							<a><security:authentication property="principal.name" /> <security:authentication
									property="principal.surname" /></a>
						</div>
						<div class="topMenu">
							<ul class="topul">
								<li><a href="/peopleSearch">People Search</a></li>
								<li><a href="/groupeSearch">Groupe Search</a></li>
							</ul>
						</div>
						<div class="logout">
							<form:form action="/logout" method="post">
								<button type="submit" class="btn btn-default">
									<img src="/resources/image/logout.png" height="25px">
								</button>
							</form:form>
						</div>
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"
						style="box-shadow: 0 0 0 2px green;">
						<h3>People Search</h3>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										data-toggle="dropdown">Sort</button>
									<ul class="dropdown-menu">
										<custom:sort innerHtml="Name asc" paramValue="name" />
										<custom:sort innerHtml="Name desc" paramValue="name,desc" />
										<custom:sort innerHtml="Surname asc" paramValue="surname" />
										<custom:sort innerHtml="Surname desc"
											paramValue="surname,desc" />
										<custom:sort innerHtml="Email asc" paramValue="email" />
										<custom:sort innerHtml="Email desc" paramValue="email,desc" />
								
									</ul>
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								<custom:size posibleSizes="1,2,5,10,20" size="${userPages.size}"
									title="Size" />
							</div>
						</div>
						<form:form action="/peopleSearch" class="form-inline" method="get"
							modelAttribute="filter">
							<custom:hiddenInputs
								excludeParams=" name,surname, email, password, phone, country,city,  countryIds,
									 sexIds, _sexIds, _countryIds, dayOfBirthIds, _dayOfBirth, mounthOfBirth, _mounthOfBirth, yearOfBirth, _yearOfBirth" />

							<div class="form-group">
								<form:input path="name" placeholder="name" class="form-control" />
								<form:input path="surname" placeholder="surname"
									class="form-control" />
								<form:input path="email" placeholder="email"
									class="form-control" />
								<form:input path="phone" placeholder="phone"
									class="form-control" />
							</div>
							<div class="form-group">
								<h4>Country</h4>
							</div>
							<div class="form-group">
								<form:checkboxes items="${countries}" path="countryIds" />
							</div>
							<div>
								<h4>Gender</h4>
							</div>
							<div class="form-group">
								<form:checkboxes items="${sexs}" path="sexIds" />
							</div>
							<br>
							<br>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">
									<img width="30px" src="/resources/image/search.png?version=1" /><B>
										Find</B>
								</button>
							</div>
						</form:form>
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<div class="row">
							<br>
							<c:forEach items="${userPages.content}" var="userPage">
								<div class="row">
									<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
										<div class="aligncenter">
											<a href="/peoplePage/${userPage.id}"><img
												src="/images/userpage/${userPage.id}${userPage.path}?version=${userPage.version}"
												height="110px"></a>
										</div>
										<div class="span"></div>
									</div>
									<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">

										<h4>
											<a href="/peoplePage/${userPage.id}">${userPage.name}
												${userPage.surname}</a>
										</h4>
										<a href="/texting/${userPage.id}"><button
												class="btn btn-primary">Send Message</button></a><br>

										<form:form action="/follower/${userPage.id}"
											modelAttribute="form" method="post" class="form-inline">
											<button type="submit" class="btn btn-primary">Add
												Friend</button>
										</form:form>
									</div>
								</div>
							</c:forEach>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
								<custom:pageable page="${userPages}" cell="<li></li>"
									container="<ul class='pagination'></ul>" />
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>