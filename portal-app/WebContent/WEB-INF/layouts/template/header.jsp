<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<div class="topnav" id="myTopnav">

  <a href="${pageContext.request.contextPath}/">Cloud RDP</a>
  
  <c:choose>
	  <c:when test="${not empty token}">
	   <a href="${pageContext.request.contextPath}/container/product">Product Catalog</a>
	   
	   <a class="topnav-right" href="${pageContext.request.contextPath}/user/logout">Logout ${token.username}&nbsp;<i class="fas fa-sign-out-alt"></i></a>
	  </c:when>
	  <c:otherwise>
	   <a href="${pageContext.request.contextPath}/container/product">Product Catalog</a>
	   
	   <a class="topnav-right" href="${pageContext.request.contextPath}/user/register">Sign Up</a>
	   <a class="topnav-right" href="${pageContext.request.contextPath}/user/login">Login</a>
	  </c:otherwise>
  </c:choose>
   
    <!-- For Dropdown functionality -->
  <%--  <div class="dropdown">
    <button class="dropbtn">Dropdown Menu 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="${pageContext.request.contextPath}/uri/example">Dropdown Option 1</a>
      <a href="${pageContext.request.contextPath}/uri/example">Dropdown Option 2</a>
      <a href="${pageContext.request.contextPath}/uri/example">Dropdown Option 3</a>
    </div>
  </div> --%>
  
  <a href="javascript:void(0);" style="font-size:15px;" class="topnav-icon" onclick="headerMenu()">&#9776;</a>
</div>

