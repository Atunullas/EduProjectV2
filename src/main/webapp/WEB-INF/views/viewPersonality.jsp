<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function() {
	
});
</script>
<div class="container section white-text">
	<div class="text-center">
		<h3 class="title white-text">About Noble Personality</h3>
		<div class="row">
			<div class="form-group text-center">
				Personality no : ${curPerson} out of ${totPerson}
			</div>
		</div>
	</div>
	<form class="form form-vertical" action="nextPerson.do" method="get">
		<div class="row">
			<div class="col-sm-3 text-center">
				<div class="row">
					<label>${person.personPic}</label>
				</div>
				<i class="fa fa-user" style="font-size: 8.5em;"></i>
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="fname">Name</label>
							<label class="form-control">${person.personName}</label>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="lname">Age</label>
							<label class="form-control">${person.personAge}</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label>Date of Birth :</label>
							<label class="form-control">${person.personDOB}</label>
						</div>
					</div>
					<c:if test="${not empty person.personDOE} ">
						<div class="col-sm-6">
							<div class="form-group">
								<label>Date of Expiry :</label>
								<label class="form-control">${person.personDOE}</label>
							</div>
						</div>
					</c:if>
				</div>
				
				<div class="row">
					<div class="col-sm-6">
							<label>Gender :</label>
							<label class="form-control">${person.personGender}</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-12">
							<label>About :</label>
							<textarea class="form-control" readonly="readonly" >${person.personAbout}</textarea>
					</div>
				</div>
				<input type="hidden" name="id" value="${curPerson}">				
				<div class="form-group">
					<hr>
					<div class="text-right">
						<button type="submit" class="btn btn-primary">Next</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>