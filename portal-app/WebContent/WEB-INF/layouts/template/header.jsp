<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<img class="logo"
		src="https://www.gcu.edu/sites/default/files/media/Documents/brand/logos/jpeg/GCU-267.jpg" />
	 
<div class="topnav" id="myTopnav">
	<a href="${pageContext.request.contextPath}/"><span>Cloud
			RDP</span></a>

	<c:if test="${not empty token}">
		<!-- For Dropdown functionality -->
		<div class="dropdown">
			<button class="dropbtn">
				<span>Docker</span> <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="${pageContext.request.contextPath}/container/catalog"><span>Your
						Containers</span></a> <a href="#manageContainers"><span>Manage
						Containers</span></a> <a
					href="${pageContext.request.contextPath}/image/catalog"><span>Create
						a Container</span></a>
			</div>
		</div>

		<div class="dropdown">
			<button class="dropbtn">
				<span>Swarm</span> <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="#swarmServices"><span>Services</span></a> <a
					href="#swarmNodes"><span>Nodes</span></a> <a
					href="#swarmVisualizer"><span>Visualizer</span></a>
			</div>
		</div>

	</c:if>

	<c:choose>
		<c:when test="${not empty token}">
			<%-- <a href="${pageContext.request.contextPath}/container/product">Product Catalog</a> --%>

			<a class="topnav-right"
				href="${pageContext.request.contextPath}/user/logout"><span>Logout
					${token.username}&nbsp;<i class="fas fa-sign-out-alt"></i>
			</span></a>
		</c:when>
		<c:otherwise>
			<%-- <a href="${pageContext.request.contextPath}/container/product">Product Catalog</a> --%>

			<a class="topnav-right"
				href="${pageContext.request.contextPath}/user/register"><span>Sign
					Up</span></a>
			<a class="topnav-right"
				href="${pageContext.request.contextPath}/user/login"><span>Login</span></a>
		</c:otherwise>
	</c:choose>

	<a class=topnav-right href="#aboutus"><span>About Us</span></a> <a
		href="javascript:void(0);" style="font-size: 19.6px;"
		class="topnav-icon" onclick="headerMenu()">&#9776;</a>
</div>

