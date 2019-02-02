<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Nav -->
<nav class="navbar navbar-toggleable-md navbar-expand-md py-0">
	<a class="navbar-brand" href="${pageContext.request.contextPath}">Flight<span class="alt">Manager</span></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navbarNav">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">Home</a></li>
			
			<!-- Not logged in -->
			<c:if test="${empty token }">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login/login">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register/register">Register</a></li>
			</c:if>
			
			<!-- User logged in -->
			<c:if test="${not empty token}">
			
			</c:if>
			
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/product/flights">Flights</a></li>
		
			<!-- Logged in -->
			<c:if test="${not empty token }">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login/logout">Logout</a></li>
			</c:if>
		</ul>
	</div>
</nav>