<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
				<div class="col-xs-3 col-3sm-3 col-md-3 col-lg-3 greenRightBox">
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
				<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
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
						<h3>Written posts</h3>
						<div class="row greenBox">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								<h3>Post Written To</h3>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<h3>Posts</h3>
							</div>
						</div>
						<c:forEach items="${myPosts}" var="post">
							<div class="row postBox2">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
									<div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5 text-center">
											<a href="/peoplePage/${post.owner.id}"><img
												src="/images/userpage/${post.owner.id}${post.owner.path}?version=${post.owner.version}"
												height="50px"></a>
										</div>
										<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
											<a href="/peoplePage/${post.owner.id}"> <b>${post.owner.name}<br>
													${post.owner.surname}
											</b></a>
										</div>
									</div>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div class="row">
										<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
											<b>${post.name}</b>
										</div>
										<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 date">
											<a href="/post/delete/${post.id}<custom:allParams/>"> <sup>X</sup></a>
										</div>
									</div>
									<div>${post.text}</div>
								</div>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 date">
									<sup>${post.date}</sup>
								</div>
								<p></p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>