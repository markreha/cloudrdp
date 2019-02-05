<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">User login</h4>
		
		<form:form method = "POST" action="loginUser" modelAttribute="user">
			<div class="form-group">
				<form:label path="username">Username</form:label>
				<form:input path="username" class="form-control" />
				<p class="text-danger small">
					<form:errors path="username" />
				</p>
			</div>
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:input path="password" class="form-control" type="password" />
				<p class="text-danger small">
					<form:errors path="password" />
				</p>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div><%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>