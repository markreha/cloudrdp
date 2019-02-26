<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="jumbotron">

	<c:if test="${empty token }">
	<div class="container">
		<h1 class="display-4">Example</h1>
		<p>To start, login, create an account, or view the available containers.</p>
		<p>
			<a class="btn btn-primary btn-lg" href="user/login" role="button">Log in »</a>
			<a class="btn btn-primary btn-lg" href="user/register" role="button">Register »</a>
			<a class="btn btn-primary btn-lg" href="container/product" role="button">View containers »</a>
		</p>
	</div>
	</c:if>
	
	<c:if test="${not empty token}">
	<div class="container">
		<h1 class="display-4">Welcome, ${token.username}!</h1>
		<p>Make your own cloud application</p>
		<p>
			<a class="btn btn-primary btn-lg" href="product/flights" role="button">View flights »</a>
		</p>
	</div>
	</c:if>
</div>