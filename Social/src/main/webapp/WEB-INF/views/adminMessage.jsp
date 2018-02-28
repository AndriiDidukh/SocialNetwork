<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="row-fluid">
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
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form:form action="/admin/message" class="form-inline" method="get"
						modelAttribute="filter">
						<div class="form-group">
							<form:input path="text" placeholder="text" class="form-control" />
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Find</button>
						</div>
					</form:form>
				</div>
			</div>
			<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form:form action="/admin/message" method="post"
						class="form-inline" modelAttribute="adminMessage">
						<form:hidden path="id" />
						<custom:hiddenInputs excludeParams="name, id" />
						<div class="form-group">
							<form:input path="text" placeholder="Text" class="form-control" />
							<h5>Choice Owner</h5>
							<form:select path="senderId" items="${senderIds}"
								itemLabel="name" itemValue="id">
							</form:select>
							<h5>Choice Writer</h5>
							<form:select path="reciverId" items="${reciverIds}"
								itemLabel="name" itemValue="id">
							</form:select>
							<br> <br>
							<button type="submit" class="btn btn-primary">Create
								Message</button>
						</div>
					</form:form>
				</div>
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<h4>text</h4>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<h4>Sender</h4>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<h4>Reciver</h4>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<h4>Delete</h4>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<h4>Update</h4>
				</div>
				<c:forEach items="${messages.content}" var="message">
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">${message.text}</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">${message.senderId.name}${message.senderId.surname}</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">${message.reciverId.name}${message.reciverId.surname}</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<a href="/admin/message/delete/${message.id}<custom:allParams/>">delete</a>
					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<a href="/admin/message/update/${message.id}<custom:allParams/>">update</a>
					</div>
				</c:forEach>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
					<custom:pageable page="${messages}" cell="<li></li>"
						container="<ul class='pagination'></ul>" />
				</div>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-center">
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							Sort <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<custom:sort innerHtml="Text asc" paramValue="text" />
							<custom:sort innerHtml="Text desc" paramValue="text,desc" />
						</ul>
					</div>
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-center">
					<custom:size posibleSizes="1,2,5,10,20" size="${messages.size}"
						title="Size" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>