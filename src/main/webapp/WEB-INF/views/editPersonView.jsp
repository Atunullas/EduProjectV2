<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#editBtn').click(function() {
			var id = [];
			$("input:checkbox[name=personId]:checked").each(function() {
				id.push($(this).val());
			});
			if(id.length==0 || id.length>1){
				alert('Please choose only one for edit Option');
			} else{
				console.log('id ' + id[0]);
				$('#editDelForm').attr('action', 'editPersonSelect.do?personalityId='+id[0]);
				$('#editDelForm').submit();
			}
		});
		$('#delBtn').click(function() {
			var id='';
			var first = true;
			$("input:checkbox[name=personId]:checked").each(function() {
				if(first){
					id = $(this).val();	
				}else {
					id = id + $(this).val();
				}
			});
			console.log('id ' + id);
			if(id!=''){
				$('#editDelForm').attr('action', 'deletePersonSelect.do?personalityId='+id);
				$('#editDelForm').submit();
			}
		});
	});
</script>

<div class="container">
	<div class="row">&nbsp;</div>
	<div class="row">&nbsp;</div>
	<div class="row">
		<div style="float: right;">
			<input type="button" id="editBtn" class="btn btn-primary"
				value="Edit" /> <input type="button" id="delBtn"
				class="btn btn-danger" value="Delete"> <input type="button"
				class="btn btn-warning" onclick="window.location.href ='service.do'"
				value="Cancel" />
		</div>
	</div>
	<div class="row">&nbsp;</div>
	<div class="row">
		<form id="editDelForm" method="post">
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="col-md-1"><label>Choose</label></th>
						<th class="col-md-6"><label>Personality Name</label></th>
						<th class="col-md-3"><label>Personality DOB</label></th>
						<th class="col-md-5"><label>Subject</label></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${allPersonality}" var="eachPerson">
						<tr>
							<td><input type="checkbox" name="personId"
								value="${eachPerson.personId}" required="required"></td>
							<td>${eachPerson.personName}</td>
							<td>${eachPerson.personDOB}</td>
							<td>${eachPerson.personSubject}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</form>
	</div>
</div>