<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container">
	<div class="text-center md-padding">
		<div class="row">
			<h2 class="white-text">${bulkUploadMessage}</h2>
		</div>
		<div class="row">
			<input type="button" value ="Back" class="btn btn-default" onclick="location.href='service.do'"/>
			<input type="button" value ="Upload Again" class="btn btn-primary" onclick="location.href='bulkUpload.view'"/>
		</div>
	</div>
</div>