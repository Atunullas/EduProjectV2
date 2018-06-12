<jsp:include page="commons/header.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		/* $('#personDOB').datepicker({
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate() + 1);
				$("#personDOE").datepicker("option", "minDate", dt);
			}
		});
		$('#personDOE').datepicker({
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate() - 1);
				$("#personDOB").datepicker("option", "maxDate", dt);
			}
		}); */
	});
</script>
<!-- add styles -->
<link href="static/css/main.css" rel="stylesheet" type="text/css" />
<link href="static/css/jquery.Jcrop.css" rel="stylesheet"
	type="text/css" />
<!-- add scripts -->
<script src="static/js/Jcrop.js"></script>
<script src="static/js/profilePicScript.js"></script>

<div class="container section white-text">
	<div class="section-header text-center">
		<h3 class="title white-text">Upload your Personality here</h3>
	</div>
	<div id="sucessMsgDiv" class="alert alert-success col-sm-offset-3 text-center" style="display:none;">
		<strong>Upload Successful</strong>
	</div>
	<form method="post" action="savePersonality.do"
		enctype="multipart/form-data" autocomplete="off">
		<div class="row">
			<div class="col-sm-3 text-center">
				<div class="row">
					<label>Upload Picture</label>
				</div>
				<div class="row">
					<div class="row">&nbsp;</div>
					<img id="preview" />
					<div class="error"></div>
					<div class="row">&nbsp;</div>
				</div>
				
				<div class="row">
				<input type="file" name="personPic" title="Upload Picture"
					id="personPic" onchange="fileSelectHandler()"/>
				</div>

			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="fname">First Name</label> <input type="text"
								class="form-control" name="firstName" required="required">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="lname">Last Name</label> <input type="text"
								class="form-control" name="lastName" required="required">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label>Date of Birth :</label> <input type="date"
								class="form-control" name="personDOB" id="personDOB"
								required="required">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label>Date of Expiry :</label> <input type="date"
								class="form-control" name="personDOE" id="personDOE">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<label>Gender :</label> <select name="personGender"
							class="form-control" required="required">
							<option selected="selected">Choose a Gender</option>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<label>About :</label>
						<textarea name="personAbout" class="form-control" id="personAbout"
							required="required"></textarea>
					</div>
				</div>

				<div class="form-group">
					<hr>
					<div class="text-right">
						<input type="button" id="resetBtn" class="btn btn-warning"
							value="Reset" /> <input type="submit" class="btn btn-primary"
							value="Submit"> <input type="button"
							class="btn btn-danger"
							onclick="window.location.href ='service.do'" value="Cancel" />
					</div>
				</div>
			</div>
		</div>
	</form>
</div>