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
						<h3>${groupe.name}</h3>
						<div class="row">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
								<img
									src="/images/groupe/${groupe.id}${groupe.path}?version=${groupe.version}"
									width="100%">
							</div>
							<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
								<div class="span">${groupe.description}</div>
								<div class="span">
									Groupe Creator: <a href="/peoplePage/${groupe.creator.id}">${groupe.creator.name}
										${groupe.creator.surname}</a>
								</div>
								<div>
									<a href="#"><button class="btn btn-primary">GroupeSubscribers</button></a>
									<b class="span">${count} subscribers</b>
								</div>
								<form:form action="/groupesPage/${groupe.id}" method="post"
									class="form-inline" modelAttribute="memb">
									<form:hidden path="id" />
									<custom:hiddenInputs excludeParams="name, id" />
									<div class="form-group">
										<button type="submit" class="btn btn-primary">Folow
											Group</button>
									</div>
								</form:form>
							</div>
						</div>
						<h3>Add New Post</h3>
						<form:form action="/groupePage/${groupe.id}" method="post"
							class="form-inline" modelAttribute="form">
							<form:hidden path="id" />
							<custom:hiddenInputs excludeParams="name, id" />
							<div class="form-group">
								<div>
									<form:input path="name" placeholder="Header"
										class="form-control" />
								</div>
								<div>
									<form:input path="text" placeholder="Text" class="form-control"
										size="50" />
								</div>
								<button type="submit" class="btn btn-primary">Create
									Post</button>
							</div>
						</form:form>
						<h3>GROUPE POSTS</h3>
						<div class="row greenGroupePageBox">
							<div class="col-md-4 col-sm-4 col-xs-4 col-lg-4">
								<h3>From</h3>
							</div>
							<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
								<h3>Posts</h3>
							</div>
						</div>
						<c:forEach items="${posts}" var="post">
							<div class="row groupePageBox">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
									<div class="row">
										<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5 text-center">
											<a href="/peoplePage/${post.writer.id}"><img
												src="/images/userpage/${post.writer.id}${post.writer.path}?version=${post.writer.version}"
												height="50px"></a>
										</div>
										<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
											<a href="/peoplePage/${post.writer.id}"> <b>${post.writer.name}<br>
													${post.writer.surname}
											</b></a>
										</div>
									</div>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
									<div>
										<b>${post.name}</b>
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