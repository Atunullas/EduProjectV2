<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- add styles -->
<link href="static/css/main.css" rel="stylesheet" type="text/css" />
<link href="static/css/jquery.Jcrop.css" rel="stylesheet"
	type="text/css" />
<!-- add scripts -->
<script src="static/js/Jcrop.js"></script>
<script src="static/js/profilePicScript.js"></script>

<div class="container section white-text">
	<div class="section-header text-center">
		<h3 class="title white-text">Edit your Personality</h3>
	</div>
	<div id="sucessMsgDiv"
		class="alert alert-success col-sm-offset-3 text-center"
		style="display: none;">
		<strong>Upload Successful</strong>
	</div>
	<form method="post" action="editPersonSave.do"
		enctype="multipart/form-data" autocomplete="off">
		<div class="row">
			<div class="col-sm-3 text-center">
				<div class="row">
					<label>Upload Picture</label>
				</div>
				<div class="row">
					<div class="row">
						<img src="data:image/jpeg;base64,${image}" alt="${personality.firstName} ${personality.lastName}'s Pic" />
					</div>
					<img id="preview" />
					<div class="error"></div>
					<div class="row">&nbsp;</div>
				</div>

				<div class="row">
					<input type="file" name="personPic" title="Upload Picture"
						id="personPic" onchange="fileSelectHandler()" />
				</div>

			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="fname">First Name</label> <input type="text"
								class="form-control" name="firstName" required="required" value="${personality.firstName}">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="lname">Last Name</label> <input type="text"
								class="form-control" name="lastName" required="required" value="${personality.lastName}">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label>Date of Birth :</label> <input type="date"
								class="form-control" name="personDOB" id="personDOB"
								required="required" value="${personality.personDOB}">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label>Date of Expiry :</label> <input type="date"
								class="form-control" name="personDOE" id="personDOE" value="${personality.personDOE}">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<label>Gender :</label> <select name="personGender"
							class="form-control" required="required">
							<option>Choose a Gender</option>
							<c:if test="${personality.personGender eq 'Male'}" >
								<option selected="selected" value="${personality.personGender}" >${personality.personGender}</option>
								<option value="Female">Female</option>
							</c:if>
							<c:if test="${personality.personGender eq 'Female'}" >
								<option selected="selected" value="${personality.personGender}" >${personality.personGender}</option>
								<option value="Male">Male</option>
							</c:if>
						</select>
					</div>
					<div class="col-sm-6">
						<label>Subject :</label> <select name="persSubjectId"
							class="form-control" required="required">
							<option value="">Choose the Subject</option>
							<option value="${personality.personSubject.subjectId}" selected="selected">${personality.personSubject.subjectName}</option>
							<c:forEach items="${allSubjects}" var="eachSubject">
								<c:if test="${personality.personSubject.subjectName ne eachSubject}">
									<option value="${eachSubject}">${eachSubject}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<label>About :</label>
						<textarea name="personAbout" class="form-control" id="personAbout"
							required="required">${personality.personAbout}</textarea>
					</div>
				</div>
				<input type="hidden" name="subject" value="${subject}">
				<div class="form-group">
					<hr>
					<div class="text-right">
						<input type="reset" id="resetBtn" class="btn btn-warning"
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