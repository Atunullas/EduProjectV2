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
				$('#editDelForm').attr('action', 'editQuestSelect.do?questionId='+id[0]);
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
					id = id + ',' + $(this).val();
				}
			});
			console.log('id ' + id);
			if(id!=''){
				$('#editDelForm').attr('action', 'deleteQuest.do?questionId='+id);
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
						<th class="col-md-8"><label>Question</label></th>
						<th class="col-md-4"><label>Subject</label></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${allQuestions}" var="eachQuest">
						<tr>
							<td><input type="checkbox" name="personId"
								value="${eachQuest.questionId}" required="required"></td>
							<td>${eachQuest.questionTxt}</td>
							<td>${eachQuest.questionSubject}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</form>
	</div>
</div>
