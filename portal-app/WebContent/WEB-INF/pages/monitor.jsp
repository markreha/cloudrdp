<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">Monitor</h4>
		<button onclick="myFunction()">Configure</button>

		<p id="demo"></p>

		<script>
			function myFunction() {
				var model = '${ cont["id"] }';
				var str = "Configure";

				var result = str.link("${pageContext.request.contextPath}/config/config/" + model);
				document.getElementById("demo").innerHTML = result;
			}
		</script>
		<p>
			Image:
			<!-- 
		<c:forEach items="${cont[name]}" begin="0"
			end="${fn:length(cont.getImage)}" var="name">
			<c:out value="${name}" />
		</c:forEach>
		-->
		</p>
		URL: http://example.com/ ${cont["name"]}
		<!--  
		<c:forEach items="${cont.getSetting}" begin="0"
			end="${fn:length(cont.getSetting)}" var="line">
			<c:out value="${line.getName}: ${line.getLimits.getCurrent}" />
		</c:forEach>
		-->
		Storage Space: ${cont["current0"]} / ${code["storage"]} CPU:
		${cont["current1"]} RAM: ${cont["current2"]} Running: ${cont["code"]}
	</div>
</div>