<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
	$(function() {
		var low = '${cont["lower0"]}'
		var high = '${cont["upper0"]}'
		var curr = '${cont["current0"]}'
		$("#slider-1").slider({
			orientation : "horizontal",
			value:curr,
            min: 0,
            max: 10,
            slide : function(event, ui) {
				$("#storage").val(ui.value);
			}
		});
		$("#storage").val($("#slider-1").slider("value"));

		var low = '${cont["lower1"]}'
		var high = '${cont["upper1"]}'
		var curr = '${cont["current1"]}'
		$("#slider-2").slider({
			orientation : "horizontal",
			value:curr,
            min: 0,
            max: 10,
			slide : function(event, ui) {
				up = ((ui.value - low)) / (high - low)
				$("#cpu").val(ui.value);
			}
		});
		$("#cpu").val($("#slider-2").slider("value"));

		var low = '${cont["lower2"]}'
		var high = '${cont["upper2"]}'
		var curr = '${cont["current2"]}'
		$("#slider-3").slider({
			orientation : "horizontal",
			value:curr,
            min: 0,
            max: 10,
			slide : function(event, ui) {
				$("#ram").val(ui.value);
			}
		});
		$("#ram").val($("#slider-3").slider("value"));
	});
	function savedata() {
		
	}
</script>
<div class="card">
	<div class="card-body">
	<form:form method = "POST" action="../updateSetting" modelAttribute="update">
		<p>
			<label for="storage">Storage Space:</label> <input type="text"
				id="storage" name="storage" style="border: 0; color: #b9cd6d; font-weight: bold;">
		</p>
		<div id="slider-1"></div>
		<p>
			<label for="cpu">CPU:</label> <input type="text" id="cpu" name="cpu"
				style="border: 0; color: #b9cd6d; font-weight: bold;">
		</p>
		<div id="slider-2"></div>
		<p>
			<label for="ram">RAM:</label> <input type="text" id="ram" name="ram"
				style="border: 0; color: #b9cd6d; font-weight: bold;"/>
		</p>
		<div id="slider-3"></div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
	</div>
</div>