<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="card">
	<div class="card-body">
		<h4 class="card-title">Monitor</h4>
		<a href="CloudRDP/config/config/${cont.name}">Configure</a>
		Image: ${cont.image[0]}
		URL: http://example.com/ ${cont.name}
		
		Storage Space: ${code.setting.settings[0].limits.current} / ${code.storage}
		CPU: ${code.settings[1].limits.current}
		RAM: ${code.settings[2].limits.current}
		Running: ${cont.code}
	</div>
</div>