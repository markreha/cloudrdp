<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <div><h2 style="margin: 0.5em 0">Your Containers</h2></div>
   
<c:choose>
	<c:when test="${not empty containers}">

  
    <div class="input row">
      <i style="color: #522398"class="fas fa-search icon"></i><input placeholder="Search" type="text" id="filter" autocomplete="off" onkeyup="filterFunction()"/> 
    </div>

    <div class="images-container">
      
	<c:forEach items="${containers}" var="container">
	
      <div id="myBtn" class="column images">
      <span style="margin-top: 10px">${container.name}</span>
      <img src="https://cdn-images-1.medium.com/max/960/1*ZGEUEy_SifxtHG-CSAWsZA.png"/>
        <span style="margin-top: 10px">Instance: ${container.instance}</span>
        <span style="margin-top: 10px">Version: ${container.version}</span>
        <span style="margin-top: 20px">Tier: ${container.tier}</span>
    	 <span style="margin-top: 10px">CPU: ${container.cpu}</span>
    	  <span style="margin-top: 10px">RAM: ${container.ram}</span>
    	   <span style="margin-top: 10px">Storage: ${container.storage}</span>
        <span style="margin-top: 20px">${container.description}</span>
      </div>

	</c:forEach>
	
    </div>
    
    
	</c:when>
	<c:otherwise>
	
	<div style="align-items: center;" class="column">
	
		You have no containers!
	
	    <a class="btn btn-purple font-bold mgn-col-10" href="${pageContext.request.contextPath}/image/catalog"><span>Create a Container</span></a>

	</div>
	</c:otherwise>
</c:choose>
      