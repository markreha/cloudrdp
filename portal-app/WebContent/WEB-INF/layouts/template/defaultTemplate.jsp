<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Cloud RDP</title>
	
	<!-- Meta Data -->
<!-- 	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1"> -->
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0"/>
	
	<!-- Resolves favicon.ico -->
	<link rel="icon" href="data:;base64,iVBORw0KGgo="/>
	
	<!-- Stylesheets -->
	
	<link rel="stylesheet" media="all" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/header.css">
	<link rel="stylesheet" media="all" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/layouts.css">
	<link rel="stylesheet" media="all" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/commons.css">
	<link rel="stylesheet" media="all" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/containers.css">
	<link rel="stylesheet" media="all" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/modal.css">
		
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" 
		integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	
	<!-- 	
	<link rel="stylesheet" media="all" type="text/css"
		href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" media="all" type="text/css"
		href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" media="all" type="text/css"
		href="//cdn.datatables.net/responsive/2.2.0/css/responsive.bootstrap.min.css"></link>
	<link rel="stylesheet" media="all" type="text/css"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
		integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
		crossorigin="anonymous">
		-->
		
	<!-- Scripts -->
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utility.js"></script>
			
	<!-- https://cdnjs.com/libraries/jquery-ui-timepicker-addon -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.11.0/jquery-ui.min.js"></script>
	<!--  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-sliderAccess.js"></script>
	<script	type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script	type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.0/js/dataTables.responsive.min.js"></script>	
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>
	 -->
</head>

<body>

<div>
<img class="logo"
		src="https://www.gcu.edu/sites/default/files/media/Documents/brand/logos/jpeg/GCU-267.jpg"/>

<img class="logo" style="float:right; padding: 10px;" 
	src="https://mk0teamcolorcodtgc6i.kinstacdn.com/wp-content/uploads/2018/04/grand_canyon_university.jpg"/>

</div> 
	<div class="container">
		<!-- Header -->
		<div class="header">
		<tiles:insertAttribute name="header" />
		</div>
		
		<!-- Body Page -->
		<div class="body">
			<tiles:insertAttribute name="body" />
		</div>
	
		<!-- DISABLED Footer Page -->
		<%-- <div class="footer">
		<tiles:insertAttribute name="footer" />
		</div>  --%>
	</div>
</body>

</html>