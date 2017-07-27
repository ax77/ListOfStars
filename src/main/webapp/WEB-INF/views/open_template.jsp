<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">

<title>Home</title>

<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrapValidator.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/datepicker.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/general.css" />">


<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrapValidator.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/general.js" />"></script>
</head>
<body>
	<t:insertAttribute name="header" />
	<!--<co id="co_tile_header" />-->

	<div class="container-fluid">
		<div class="row">
			<t:insertAttribute name="side" />
			<!--<co id="co_tile_side" />-->

			<t:insertAttribute name="loginForm" />
			<!--<co id="co_tile_loginForm" />-->

			<div class="col-sm-9 col-md-10 main top60 bottom30">
				<t:insertAttribute name="content" />
				<!--<co id="co_tile_content" />-->
			</div>
		</div>
	</div>
</body>
</html>
