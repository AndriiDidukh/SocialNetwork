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
					<div class="rightside" style="box-shadow: none;">
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
						<h3>Groupe Search</h3>
						<div class="row">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div class="row">
									<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
										<form:form action="/groupeSearch" class="form-inline"
											method="get" modelAttribute="filter">
											<div class="form-group">
												<form:input path="name" placeholder="name"
													class="form-control" />
												<div class="form-group"></div>
											</div>
											<div class="form-group">
												<button type="submit" class="btn btn-primary">Find</button>
											</div>
										</form:form>
									</div>
									<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
										<div class="dropdown">
											<button class="btn btn-primary dropdown-toggle" type="button"
												data-toggle="dropdown">
												Sort <span class="caret"></span>
											</button>
											<ul class="dropdown-menu">
												<custom:sort innerHtml="name asc" paramValue="name" />
												<custom:sort innerHtml="name desc" paramValue="name,desc" />
											</ul>
										</div>
									</div>
									<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
										<custom:size posibleSizes="1,2,5,10,20" size="${groupes.size}"
											title="Size" />
									</div>
									<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></div>
								</div>
							</div>
						</div>
						<div class="row groupeBoxGreen">
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
								<h4>photo</h4>
							</div>
							<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
								<h4>name/description</h4>
							</div>
							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"></div>
							<h4>creator</h4>
						</div>
						<c:forEach items="${adminGroupes.content}" var="groupe">
							<div class="row groupeBox2">
								<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
									<a href="/groupePage/${groupe.id}"><img
										src="/images/groupe/${groupe.id}${groupe.path}?version=${groupe.version}"
										width="100%"></a>
								</div>
								<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
									<div class="span">
										<h4>
											<a href="/groupePage/${groupe.id}">${groupe.name}</a>
										</h4>
									</div>
									<div class="span">${groupe.description}</div>
									<form:form action="/groupesPage/${groupe.id}" method="post"
										class="form-inline" modelAttribute="form">
										<form:hidden path="id" />
										<custom:hiddenInputs excludeParams="name, id" />
										<div class="form-group">
											<button type="submit" class="btn btn-primary">Folow
												Group</button>
										</div>
									</form:form>
								</div>
								<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
									<div class="span">${groupe.creator.name}<br>${groupe.creator.surname}</div>
								</div>
							</div>
						</c:forEach>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
							<custom:pageable page="${adminGroupes}" cell="<li></li>"
								container="<ul class='pagination'></ul>" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</security:authorize>
</body>
</html>