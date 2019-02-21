<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	function myFunction() {
		let model = '${ cont["id"] }';
		let str = "Configure";

		let result = "<tiles:insertAttribute name='products' />";
		document.getElementById("prodCreate").innerHTML = result;
	}
</script>
<div class="card">
	<div class="card-body">
		<h4 class="card-title">Products</h4>
		<div class="prodCreate"><button onclick="myFunction()">Image</button></div>
	</div>
</div>
<div class="card">
	<div class="card-body">
		<h4 class="card-title">Containers</h4>
		<!-- Populate with containers the user has -->
		<a href="../../CloudRDP/container/display/1">Example Container</a>
		<!-- /container/display/{id} -->
	</div>
</div>