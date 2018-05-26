<jsp:include page="commons/header.jsp"></jsp:include>
<style>
h1, h2, h3, h4, h5, h6 {
	color: white;
}
</style>
<div class="container section md-padding">
	<form action="home.do" class="form-horizontal">
		<div class="text-center">
			<h1><label class="title">Quiz Complete</label></h1>
			<h3>Thanks for attending the quiz</h3>
		</div>
		<div class="row">
			<div class="text-center">
				<div class="col-sm-offset-5 col-sm-2">
					<label class="form-control">Point Scored : ${score} / ${noOfTotalQuizQues}</label>
					<hr>
				</div>
			</div>
		</div>
		<div class="row text-center">
			<button class="btn btn-success">Click for Home Page</button>
		</div>
	</form>
</div>
