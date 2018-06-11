<jsp:include page="commons/header.jsp"></jsp:include>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
	.ui-dialog, .ui-widget, .ui-widget-content,
	.ui-corner-all, .foo, .ui-draggable, .ui-resizable {
	background: white !important
}
​
</style>
<script>
	$(document).ready(
			function() {
				$(".service").click(
						function() {
							if ($(this).attr("id") == "setQuestData.do"
									|| $(this).attr("id") == "startPerson.do") {
								openDialog($(this).attr("id"), false);
							} else if ($(this).attr("id") == "printQuest.pdf") {
								openDialog($(this).attr("id"), true);
							} else {
								window.location.href = $(this).attr("id");
							}
						})

				function openDialog(attr, isPrint) {
					$('#dialogDiv').dialog(
							{
								modal : true,
								buttons : {
									'OK' : function() {
										var count = $('#questCount').val();
										if (isPrint) {
											window.open(attr + '?count='
													+ count, "_blank");
										} else {
											window.open(attr + '?count='
													+ count, "New Window",
													"width="
															+ $(window).width()
															+ ",height ="
															+ $(window)
																	.height());
										}
										$('#questCount').val('');
										$(this).dialog('close');
									},
									'Cancel' : function() {
										$('#questCount').val('');
										$(this).dialog('close');
									}
								}
							});
				}
			});
</script>
<style>
h1, h2, h3, h4, h5, h6 {
	color: white;
}

p {
	color: #868F9B;
}
</style>
<div id="service" class="section" style="margin-top: 20px;">

	<!-- Container -->
	<div class="container">
		<div id="dialogDiv" style="display: none;">
			<form class="">
				<div class="form-group">
					<label>Please specify the count : </label>
				</div>
				<div class="form-group">
					<input type="text" id="questCount">
				</div>
			</form>
		</div>
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
				<div class="service" id="setQuestData.do">
					<i class="fa fa-pencil"></i>
					<h3>Start Questionnaire</h3>
					<p>Click here to start a quick Questionnaire</p>
				</div>
			</div>
			<!-- /service -->

			<!-- service -->
			<div class="col-md-4 col-sm-6">
				<div class="service" id="bulkUpload.view">
					<i class="fa fa-rocket"></i>
					<h3>Upload in Bulk as CSV</h3>
					<p>Click here to Upload questionnaire and personality view</p>
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

			<!-- service  -->
			<div class="col-md-4 col-sm-6">
				<div class="service" id="printQuest.pdf">
					<i class="fa fa-print"></i>
					<h3>Print Questions</h3>
					<p>Click here to print the Questions</p>
				</div>
			</div>
			<!-- /service -->

		</div>
		<!-- /Row -->

	</div>
	<!-- /Container -->

</div>