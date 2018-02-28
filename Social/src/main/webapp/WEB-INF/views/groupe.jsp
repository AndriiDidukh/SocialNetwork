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
					<div class="rightside">
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
							<h3>Create new Groupe</h3>
							<form:form action="/groupe" method="post" class="form-inline"
								modelAttribute="groupeForm" enctype="multipart/form-data">
								<form:hidden path="id" />
								<custom:hiddenInputs excludeParams="name, id" />
								<div class="form-group">
									<div>
										<label for="name"><form:errors path="name" /></label>
										<form:input path="name" class="form-control"
											placeholder="name" />
									</div>
									<div>
										<label for="name"><form:errors path="name" /></label>
										<form:input path="description" class="form-control"
											placeholder="Description" size="50%" />
									</div>
									<h4>Add Photo</h4>
									<label class="btn btn-default btn-file"> Browse <input
										type="file" name="file" style="display: none;">
									</label><br>
									<button type="submit" class="btn btn-primary">Create
										Groupe</button>
									<br> <br>
								</div>
							</form:form>
							<br>
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<h3>My Groups</h3>
									<div class="detai-tabs">
										<ul class="nav nav-pills tab-nike" role="tablist">
											<li role="presentation" class="active"><a href="#home"
												aria-controls="home" role="tab" data-toggle="tab">Groups</a></li>
											<li role="presentation"><a href="#profile"
												aria-controls="profile" role="tab" data-toggle="tab">Created
													groups</a></li>

										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="home">
												<div class="row greenBox">
													<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
														<h4>photo</h4>
													</div>
													<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
														<h4>name/description</h4>
													</div>
												</div>
												<c:forEach items="${groups}" var="gr">
													<div class="row groupeBox">
														<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
															<a href="/groupePage/${gr.groupe.id}"><img
																src="/images/groupe/${gr.groupe.id}${gr.groupe.path}?version=${gr.groupe.version}"
																width="100%"></a>
														</div>
														<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
															<div class="span">
																<h4>
																	<a href="/groupePage/${gr.groupe.id}">${gr.groupe.name}</a>
																</h4>
															</div>
															<div class="span">${gr.groupe.description}</div>
															<a
																href="/groupe/unsubscribe/${gr.groupe.id}<custom:allParams/>"><button
																	class="btn btn-primary">unsubscribe</button></a>
														</div>
													</div>
												</c:forEach>
											</div>
											<div role="tabpanel" class="tab-pane" id="profile">
												<div class="row greenBox">
													<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
														<h4>photo</h4>
													</div>
													<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
														<h4>name/description</h4>
													</div>
												</div>
												<c:forEach items="${myGroupes}" var="groupe">
													<div class="row groupeBox">
														<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
															<a href="/groupePage/${groupe.id}"><img
																src="/images/groupe/${groupe.id}${groupe.path}?version=${groupe.version}"
																width="100%"></a>
														</div>
														<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
															<div class="span">
																<h4>
																	<a href="/groupePage/${groupe.id}">${groupe.name}</a>
																</h4>
															</div>
															<div class="span">${groupe.description}</div>
														</div>
													</div>
													<div class="distance">.</div>
												</c:forEach>
											</div>
										</div>
									</div>
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