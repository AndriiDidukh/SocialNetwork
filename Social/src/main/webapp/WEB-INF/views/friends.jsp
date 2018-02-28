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
						<div class="specifications">
							<div class="detai-tabs">
								<ul class="nav nav-pills tab-nike" role="tablist">
									<li role="presentation" class="active"><a href="#home"
										aria-controls="home" role="tab" data-toggle="tab">Friends</a></li>
									<li role="presentation"><a href="#profile"
										aria-controls="profile" role="tab" data-toggle="tab">Followers</a></li>
									<li role="presentation"><a href="#messages"
										aria-controls="messages" role="tab" data-toggle="tab">Follow
											For</a></li>
								</ul>
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="home">
										<c:forEach items="${friends2}" var="userPage">
											<div class="row">
												<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
													<div class="aligncenter">
														<a href="/peoplePage/${userPage.friend.id}"><img
															src="/images/userpage/${userPage.friend.id}${userPage.friend.path}?version=${userPage.friend.version}"
															height="110px"></a>
													</div>
													<div class="span"></div>
												</div>
												<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
													<h4>
														<a href="/peoplePage/${userPage.friend.id}">${userPage.friend.name}
															${userPage.friend.surname}</a>
													</h4>
													<a href="/texting/${userPage.friend.id}"><button
															class="btn btn-primary">Send Message</button></a><br> <a
														href="/friends/delete/${userPage.friend.id}<custom:allParams/>"><button
															class="btn btn-primary">Delete Friend</button></a>
												</div>
											</div>
										</c:forEach>
										<c:forEach items="${friends1}" var="userPage">
											<div class="row">
												<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
													<div class="aligncenter">
														<a href="/peoplePage/${userPage.friend2.id}"><img
															src="/images/userpage/${userPage.friend2.id}${userPage.friend2.path}?version=${userPage.friend2.version}"
															height="110px"></a>
													</div>
													<div class="span"></div>
												</div>
												<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">

													<h4>
														<a href="/peoplePage/${userPage.friend2.id}">${userPage.friend2.name}
															${userPage.friend2.surname}</a>
													</h4>
													<a href="/texting/${userPage.friend2.id}"><button
															class="btn btn-primary">Send Message</button></a><br> <a
														href="/friends/delete/${userPage.friend2.id}<custom:allParams/>"><button
															class="btn btn-primary">Delete Friend</button></a>
												</div>
											</div>
										</c:forEach>
									</div>
									<div role="tabpanel" class="tab-pane" id="profile">
										<c:forEach items="${followers}" var="userPage">
											<div class="row">
												<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
													<div class="aligncenter">
														<a href="/peoplePage/${userPage.follower.id}"><img
															src="/images/userpage/${userPage.follower.id}${userPage.follower.path}?version=${userPage.follower.version}"
															height="110px"></a>
													</div>
													<div class="span"></div>
												</div>
												<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
													<h4>
														<a href="/peoplePage/${userPage.follower.id}">${userPage.follower.name}
															${userPage.follower.surname}</a>
													</h4>
													<a href="/texting/${userPage.follower.id}"><button class="btn btn-primary">Send
															Message</button></a><br>
													<form:form
														action="/friends/addFriend/${userPage.follower.id}"
														modelAttribute="friend" method="post" class="form-inline">
														<button type="submit" class="btn btn-primary">Add
															Friend</button>
													</form:form>

												</div>
											</div>
										</c:forEach>
									</div>
									<div role="tabpanel" class="tab-pane" id="messages">
										<c:forEach items="${followTo}" var="userPage">
											<div class="row">
												<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
													<div class="aligncenter">
														<a href="/peoplePage/${userPage.followTo.id}"><img
															src="/images/userpage/${userPage.followTo.id}${userPage.followTo.path}?version=${userPage.followTo.version}"
															height="110px"></a>
													</div>
													<div class="span"></div>
												</div>
												<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">

													<h4>
														<a href="/peoplePage/${userPage.followTo.id}">${userPage.followTo.name}
															${userPage.followTo.surname}</a>
													</h4>
													<a href="/texting/${userPage.followTo.id}"><button
															class="btn btn-primary">Send Message</button></a><br> <a
														href="/folowFor/delete/${userPage.followTo.id}<custom:allParams/>"><button
															class="btn btn-primary">Unsubscribe</button></a>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</security:authorize>
</body>
</html>