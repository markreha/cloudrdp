<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form class="form" modelAttribute="user" action="${pageContext.request.contextPath}/user/loginUser" method="POST">
  <h2 class="mgn-10">Login</h2>
  
  <div class="column">
  	<form:errors class="form-error" path="username"/>
   <div class="row">
   	<div class="pad-20" style="width:150px">Username:</div>
    <form:input path="username" minlength="5" placeholder="username" class="input" required="true" type="text" value="" maxlength="20" />
   </div>
  </div>
  
  <div class="column">
   <form:errors class="form-error" path="password"/>
   <div class="row">
   	<div class="pad-20" style="width:150px">Password:</div>
    <form:input path="password" minlength="5" placeholder="password" type="password" class="input" required="true" value="" maxlength="20" />
   </div>
  </div>
  
  <input class="btn-purple font-bold mgn-col-10" type="submit" value="Login" />
  
</form:form>

<div style="color: red"></div>
<div style="color: green"></div>
