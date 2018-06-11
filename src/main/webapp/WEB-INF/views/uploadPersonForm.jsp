<jsp:include page="commons/header.jsp"></jsp:include>
<script>
	$(document).ready(function() {

	});
</script>
<div class="container section white-text">
	<div class="section-header text-center">
		<h3 class="title white-text">Upload your Personality here</h3>
	</div>
	<form method="post" action="savePersonality.do"
		enctype="multipart/form-data" autocomplete="off">
		<div class="row">
			<div class="col-sm-3 text-center">
				<div class="row">
					<label>Upload Picture</label>
				</div>
				<i class="fa fa-user" style="font-size: 8.5em;"></i> <input
					type="file" name="personPic" title="Upload Picture" />
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="fname">First Name</label> <input type="text"
								class="form-control" name="firstName" id="personName"
								required="required">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="lname">Last Name</label> <input type="text"
								class="form-control" name="lastName" id="personName"
								required="required">
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
						<label>Gender :</label>
							<select name="personGender" class="form-control">
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