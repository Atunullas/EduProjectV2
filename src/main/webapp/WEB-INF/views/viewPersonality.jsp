<jsp:include page="commons/header.jsp"></jsp:include>
<header role="banner">
	<div class="container_16">
		<hgroup>
			<h1>${person.personName}</h1>
			<h2>${person.personAge}</h2>
		</hgroup>

		<figure>
			<img
				src="Sheldon%20COOPER%20-%20Physicien%20surdou%C3%A9%20et%20Geek%20qualifi%C3%A9_files/avatar.jpg"
				alt="${person.personPic}">
		</figure>
	</div>
</header>

<!-- Contact -->
<section class="contactform clearfix">
	<div class="container_16">
		<h3>${person.personAge}</h3>
		<p>${person.personAbout}
	</div>${person.personPic}
</section>