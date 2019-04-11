<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="jumbotron">

	<c:if test="${empty token }">
	<div class="container">
		<h1 class="display-4">Welcome!</h1>
		<br/>
		<p>To start, login or create an account.</p>
		<br/>
		Manage your existing Docker containers or deploy a new application to the Swarm!
		<br/>
		<p>
			<a class="btn btn-primary btn-lg" href="user/login" role="button">Log in »</a>
			<a class="btn btn-primary btn-lg" href="user/register" role="button">Register »</a>
		</p>
	</div>
	</c:if>
	
	<c:if test="${not empty token}">
	<div class="container">
		<h1 class="display-4">Welcome, ${token.username}!</h1>
		<br/>
		<p>Manage your existing Docker containers or deploy a new application to the Swarm!</p>
		<p>
			<!-- <a class="btn btn-primary btn-lg" href="product/flights" role="button">View flights »</a> -->
		</p>
	</div>
	</c:if>
</div>