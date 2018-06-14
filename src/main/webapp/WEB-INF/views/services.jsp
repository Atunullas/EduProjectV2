<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(
			function() {
				$(".service").click(
						function() {
							var serviceId = $(this).attr("id");
							if (serviceId == "setQuestData.do"
									|| serviceId == "startPerson.do") {
								openStartDialog(serviceId, false);
							} else if (serviceId == "printQuest.pdf") {
								openStartDialog(serviceId, true);
							} else if (serviceId == "editQuestView.do"
									|| serviceId == "editPersonView.do") {
								openEditDelDialog(serviceId);
							} else {
								window.location.href = serviceId;
							}
						})

				function openStartDialog(location, isPrint) {
					$('#dialogStartForm').trigger('reset');
					var url = '';
					$("#startDialog").dialog(
							{
								// autoOpen: false,
								show : {
									effect : "blind",
									duration : 1000
								},
								hide : {
									effect : "explode",
									duration : 1000
								},
								width : 400,
								height : "auto",
								resizable : false,
								modal : true,
								buttons : {
									"Ok" : function() {
										url = location + "?count="
												+ $('#itemCount').val()
												+ "&subject="
												+ $('#subject').val();
										if (isPrint) {
											window.open(url, "_blank");
										} else {
											window.open(url, "New Window",
													"width="
															+ $(window).width()
															+ ",height ="
															+ $(window)
																	.height());
										}
										$(this).dialog("close");
									},
									Cancel : function() {
										$(this).dialog("close");
									}
								}
							});
				}

				function openEditDelDialog(location, isPrint) {
					$('#dialogEditForm').trigger('reset');
					var url = '';
					$("#editDelDialog").dialog({
						// autoOpen: false,
						show : {
							effect : "blind",
							duration : 1000
						},
						hide : {
							effect : "explode",
							duration : 1000
						},
						width : 400,
						height : "auto",
						resizable : false,
						modal : true,
						buttons : {
							"Ok" : function() {
								url = location + "?subject="
								+ $('#subject').val();
								window.location.href = url;
								$(this).dialog("close");
							},
							Cancel : function() {
								$(this).dialog("close");
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
		<!-- Row -->
		<div class="row">

			<!-- Section header -->
			<div class="section-header text-center">
				<h2 class="title">Services Provided</h2>
			</div>

			<div id="startDialog" title="Make your Choice" style="display: none;">
				<form id="dialogStartForm">
					<div class="row form-group">
						<div class="col-md-6">
							<label>Number of Questions</label>
						</div>
						<div class="col-md-6">
							<input type="number" id="itemCount"
								class="col-md-12 form-control" value="0" min="0">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-sm-6">
							<label>Subject</label>
						</div>
						<div class="col-sm-6">
							<select class="form-control" id="subject">
								<option selected="selected" value="ALL">ALL</option>
								<c:forEach items="${allSubjects}" var="eachSubject">
									<option value="${eachSubject}">${eachSubject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>
			</div>

			<div id="editDelDialog" title="Make your Choice"
				style="display: none;">
				<form id="dialogEditForm">
					<div class="row form-group">
						<div class="col-sm-6">
							<label>Subject</label>
						</div>
						<div class="col-sm-6">
							<select class="form-control" id="subject">
								<option selected="selected" value="ALL">ALL</option>
								<c:forEach items="${allSubjects}" var="eachSubject">
									<option value="${eachSubject}">${eachSubject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>
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
					<i class="fa fa-file-text"></i>
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

			<!-- service  -->
			<div class="col-md-4 col-sm-6">
				<div class="service" id="editQuestView.do">
					<i class="fa fa-pencil"></i>
					<h3>Edit / Delete Questions</h3>
					<p>Click here to Edit Questions</p>
				</div>
			</div>
			<!-- /service -->

			<!-- service  -->
			<div class="col-md-4 col-sm-6">
				<div class="service" id="editPersonView.do">
					<i class="fa fa-pencil"></i>
					<h4>Edit / Delete Personalities</h4>
					<p style="font-size: 15px;">Click here to Edit/Delete Personalities</p>
				</div>
			</div>
			<!-- /service -->



		</div>
		<!-- /Row -->

	</div>
	<!-- /Container -->

</div>