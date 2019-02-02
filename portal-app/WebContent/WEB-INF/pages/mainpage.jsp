<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="jumbotron">

	<c:if test="${empty token }">
	<div class="container">
		<h1 class="display-4">Manage your flights TODAY</h1>
		<p>Are you ready for a flight managing adventure? To start, login, create an account, or view our fights.</p>
		<p>
			<a class="btn btn-primary btn-lg" href="login/login" role="button">Log in »</a>
			<a class="btn btn-primary btn-lg" href="register/register" role="button">Register »</a>
			<a class="btn btn-primary btn-lg" href="container/viewall" role="button">View containers »</a>
		</p>
	</div>
	</c:if>
	
	<c:if test="${not empty token}">
	<div class="container">
		<h1 class="display-4">Welcome, ${token.user.username}!</h1>
		<p>Make your own cloud application</p>
		<p>
			<a class="btn btn-primary btn-lg" href="product/flights" role="button">View flights »</a>
		</p>
	</div>
	</c:if>
</div>