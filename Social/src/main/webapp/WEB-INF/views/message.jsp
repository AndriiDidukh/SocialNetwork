<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
				<a href="/login">Sing In</a>
			</h3>
			<h3>Still not registered? Registrate now!</h3>
			<h3>
				<a href="/registration">Sign Up</a>
			</h3>
		</div>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<div class="container">
			<div class="row">
				<div class="col-xs-3 col-3sm-3 col-md-3 col-lg-3">
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
				<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 greenRightBox">
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
					<div class="message">
						<h3>Chouse Person for texting</h3>
						<c:forEach items="${messages}" var="userPage">
							<div class="row">
								<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 aligncenter">
									<a href="/texting/${userPage.id}"><img
										src="/images/userpage/${userPage.id}${userPage.path}?version=${userPage.version}"
										height="110px"></a>
									<div class="span"></div>
								</div>
								<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
									<h4>
										<a href="/texting/${userPage.id}">${userPage.name}
											${userPage.surname}</a>
									</h4>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>