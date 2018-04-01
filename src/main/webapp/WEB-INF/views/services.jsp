<jsp:include page="commons/header.jsp"></jsp:include>
<script>
$(document).ready(function(){
	$(".service").click(function(){
		location.href=$(this).attr("id");
	})
	
});

</script>
<div id="service" class="section" style="margin-top:20px;">

		<!-- Container -->
		<div class="container">

			<!-- Row -->
			<div class="row">

				<!-- Section header -->
				<div class="section-header text-center">
					<h2 class="title">Services Provided</h2>
				</div>
				<!-- /Section header -->

				<!-- service -->
				<div class="col-md-4 col-sm-6">
					<div class="service" id="startPerson.do">
						<i class="fa fa-user-circle"></i>
						<h3>Noble personalities</h3>
						<p>Click here to know about Noble personalities</p>
					</div>
				</div>
				<!-- /service -->

				<!-- service -->
				<div class="col-md-4 col-sm-6">
					<div class="service" id="startQuest.do">
						<i class="fa fa-pencil"></i>
						<h3>Start Questionnaire</h3>
						<p>Click here to start a quick Questionnaire</p>
					</div>
				</div>
				<!-- /service -->

				<!-- service -->
				<div class="col-md-4 col-sm-6">
					<div class="service" id="startBoth.do">
						<i class="fa fa-rocket"></i>
						<h3>Questionnaire &amp; Personality</h3>
						<p>Click here to start questionnaire and personality view</p>
					</div>
				</div>
				<!-- /service -->

				<!-- service -->
				<div class="col-md-4 col-sm-6">
					<div class="service" id="uploadPerson.do">
						<i class="fa fa-upload"></i>
						<h3>Upload Personalities</h3>
						<p>Click here to upload Personalities.</p>
					</div>
				</div>
				<!-- /service -->

				<!-- service -->
				<div class="col-md-4 col-sm-6">
					<div class="service" id="uploadQuest.do">
						<i class="fa fa-upload"></i>
						<h3>Upload Questionnaire</h3>
						<p>Click here to Upload Questionnaire.</p>
					</div>
				</div>
				<!-- /service -->

				<!-- service
				<div class="col-md-4 col-sm-6">
					<div class="service">
						<i class="fa fa-flask"></i>
						<h3>Brand Design</h3>
						<p>Maecenas tempus tellus eget condimentum rhoncus sem quam semper libero.</p>
					</div>
				</div>
				 /service -->

			</div>
			<!-- /Row -->

		</div>
		<!-- /Container -->

	</div>