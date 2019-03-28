<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<h2 style="margin: 0.5em 0">Select an Image</h2>
</div>

<c:choose>
	<c:when test="${not empty images}">

		<div class="input row">
			<i style="color: #522398" class="fas fa-search icon"></i><input
				placeholder="Search" type="text" id="filter" autocomplete="off"
				onkeyup="filterFunction()" />
		</div>

		<div class="images-container">

			<c:forEach items="${images}" var="image">

				<div onclick="javascript:openModal(this)" class="column images">
					<c:if test = "${image.instance == 'Nginx'}">
						<img
							src="https://quiksite.com/wp-content/uploads/2016/09/Nginx-Logo-02.png" />
					</c:if>
					<c:if test = "${image.instance == 'Tomcat'}">
						<img
							src="https://fixstream.com/wp-content/uploads/2015/08/apache-tomcat-logo-square.jpg" />
					</c:if>
					<span style="margin-top: 10px">Instance: <span
						id="image-instance">${image.instance}</span></span> <span
						style="margin-top: 10px">Version: <span id="image-version">${image.version}</span></span>
					<span style="margin-top: 20px">Tier: <span id="image-tier">${image.tier}</span></span>
					<span style="margin-top: 10px">CPU: <span id="image-cpu">${image.cpu}</span></span>
					<span style="margin-top: 10px">RAM: <span id="image-ram">${image.ram}</span></span>
					<span style="margin-top: 10px">Storage: <span
						id="image-storage">${image.storage}</span></span> <input
						id="image-imageId" type="hidden" value="${image.imageId}" />
				</div>

			</c:forEach>

		</div>


		<!-- The Modal -->
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<!--   <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Modal Header</h2>
    </div>
    <div class="modal-body">
      <p>Some text in the Modal Body</p>
      <p>Some other text...</p>
    </div>
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div> -->

			<div class="modal-content">
				<div class="modal-header">
					<span onclick="javascript:closeModal()" class="close">&times;</span>
					<h2 class="mgn-10">Container Details</h2>
				</div>
				<form:form class="form" modelAttribute="container"
					action="${pageContext.request.contextPath}/container/createContainer"
					method="POST">

					<div class="column">
						<form:errors class="form-error" path="name" />
						<div class="row">
							<div class="pad-20" style="width: 150px">Name:</div>
							<form:input path="name" minlength="5"
								placeholder="Container Name" class="input" required="true"
								type="text" value="" maxlength="20" />
						</div>
					</div>

					<div class="column">
						<form:errors class="form-error" path="description" />
						<div class="row">
							<div class="pad-20" style="width: 150px">Description:</div>
							<form:input path="description" placeholder="Description"
								type="text" class="input" value="" maxlength="200" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">Instance:</div>
							<form:input path="instance" id="form-instance" value=""
								readonly="true" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">Version:</div>
							<form:input path="version" id="form-version" value=""
								readonly="true" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">Tier:</div>
							<form:input path="tier" id="form-tier" value="" readonly="true" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">CPU:</div>
							<form:input path="cpu" id="form-cpu" value="" readonly="true" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">RAM:</div>
							<form:input path="ram" id="form-ram" value="" readonly="true" />
						</div>
					</div>

					<div class="column">
						<div class="row">
							<div class="pad-20" style="width: 150px">Storage:</div>
							<form:input path="storage" id="form-storage" value=""
								readonly="true" />
						</div>
					</div>

					<div class="column">
						<form:errors class="form-error" path="imageId" />
						<form:input id="form-imageId" path="imageId" type="hidden"
							readonly="true" />
					</div>

					<input class="btn-purple font-bold mgn-col-10" type="submit"
						value="Create Container" />

				</form:form>

			</div>
		</div>

	</c:when>
	<c:otherwise>

		<div style="align-items: center;" class="column">${message}</div>
	</c:otherwise>
</c:choose>
