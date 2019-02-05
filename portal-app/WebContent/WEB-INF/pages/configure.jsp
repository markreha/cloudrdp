<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">Configure</h4>
		CPU: ${code.setting.settings[0].limits.lower} - ${code.setting.settings[0].limits.current} - ${code.setting.settings[0].limits.upper}
		RAM: ${code.setting.settings[0].limits.lower} - ${code.setting.settings[0].limits.current} - ${code.setting.settings[0].limits.upper}
		Storage Space: ${code.setting.settings[0].limits.lower} - ${code.setting.settings[0].limits.current} - ${code.setting.settings[0].limits.upper}
	</div>
</div>