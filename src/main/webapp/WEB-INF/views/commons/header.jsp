<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="static/images/favicon.ico" type="image/x-icon"/>
<title>Smart App</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CVarela+Round"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="static/css/bootstrap.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="static/css/font-awesome.min.css">

<!-- Custom style sheet -->
<link type="text/css" rel="stylesheet" href="static/css/style.css" />

<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
</head>
<!-- Background Image -->
<div class="bg-img"
	style="background-image: url('./static/img/background2.jpg')">
	<div class="overlay"></div>
</div>
<!-- /Background Image -->

<!-- Nav -->

	<nav id="nav" class="navbar nav-transparent">
		<div class="container">
		<c:if test="${isWindowMode ne true}">
			<div class="navbar-header">
				<!-- Logo -->
				<div class="navbar-brand">
					<a href="home.do"> <img class="logo" src="static/img/logo.png"
						alt="logo"> <img class="logo-alt"
						src="static/img/logo-alt.png" alt="logo">
					</a>
				</div>
				<!-- /Logo -->
			</div>

			<!--  Main navigation  -->
			<ul class="main-nav nav navbar-nav navbar-right">
				<li><a href="home.do">Home</a></li>
				<li><a href="service.do">Services</a></li>
			</ul>
			</c:if>
			<!-- /Main navigation -->
		</div>
	</nav>

<!-- /Nav -->
