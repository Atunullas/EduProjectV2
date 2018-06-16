<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('input:button[name=editBtn]').click(function() {
			var selectedRow = $("input:checkbox[name=questionId]:checked").attr('id');
			$('#editDelForm').attr('action','editQuestSelect.do?questionId='+$('#'+selectedRow).val()+"&subject=${subject}");
			$('#editDelForm').submit();
		});
		
		$('input:button[name=delBtn]').click(function() {
			var selectedRow = $("input:checkbox[name=questionId]:checked").attr('id');
			$('#editDelForm').attr('action','deleteQuest.do?questionId='+$('#'+selectedRow).val()+"&subject=${subject}");
			$('#editDelForm').submit();
		});
		
		$('#delAllBtn').click(function() {
			$("input:checkbox[name=questionId]:checked").each(function() {
				$.ajax({
					url: "deleteQuest.do?questionId="+$(this).val()+"&subject=${subject}",
					method:'POST',
					error: function(result){
						$("#div1").html(result);
					},
					complete: function(result){
						$("#div1").html('Delete Successful');
						location.reload(true);
					}	    	
				});
			});
		});
		
	
		$('#checkAll').click(function(){
			if($('#checkAll').prop('checked')){
				$("input:checkbox[name=questionId]").prop('checked',true);
				if($("input:checkbox[name=questionId]:checked").length>0){
					$("#delAllBtn").show();
					$('#operationLabel').hide();
				}
			}else{
				$("input:checkbox[name=questionId]").prop('checked',false);
				$("#delAllBtn").hide();
				$('#operationLabel').show();
			}
		});
	
		$("input:checkbox[name=questionId]").click(function(){
			if($("input:checkbox[name=questionId]:checked").length>1){
				$("input:button[name=editBtn]").attr('disabled',true);
				$("input:button[name=delBtn]").attr('disabled',true);
				$("#delAllBtn").show();
				$('#operationLabel').hide();
			} else if ($("input:checkbox[name=questionId]:checked").length>0){
				var selectedRow = $("input:checkbox[name=questionId]:checked").attr('id');
				selectedRow = selectedRow.substring(10,selectedRow.length);
				$('#editBtn'+selectedRow).attr('disabled',false);
				$('#delBtn'+selectedRow).attr('disabled',false);
			} else{
				$("input:button[name=editBtn]").attr('disabled',true);
				$("input:button[name=delBtn]").attr('disabled',true);
				$("#delAllBtn").hide();
				$('#operationLabel').show();
			}
			if($("input:checkbox[name=questionId]").length == $("input:checkbox[name=questionId]:checked").length){
				$('#checkAll').prop('checked',true);
			} else{
				$('#checkAll').prop('checked',false);
			}
		});
	
	});
</script>

<div class="container section" style="padding-top: 30px;">
	<div class="section-header text-center">
		<h2 class="title white-text">Edit or Delete Questions</h2>
			<div class="row">
			<form id="editDelForm" method="post">
				<div id="div1"></div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th style="text-align: center;" class="col-md-1">
								<div><label>Choose</label></div>
								<div><input type="checkbox" id="checkAll" title="Choose All"></div>
							</th>
							<th class="col-md-8">
								<div><label>Question</label></div>
							</th>
							<th style="text-align: center;" class="col-md-1">
								<div><label>Subject</label></div>
							</th>
							<th style="text-align: center;" class="col-md-2">
								<div><input type="button" id="delAllBtn" title="Delete Selected" class="btn btn-danger" value="Delete Selected" style="display: none;"></div>
								<div id="operationLabel"><label>Operation</label></div>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allQuestions}" var="eachQuest" varStatus="iterate">
							<tr>
								<td style="text-align: center;">
									<input type="checkbox" name="questionId" value="${eachQuest.questionId}" id="questionId${iterate.count}" required="required"></td>
								<td>${eachQuest.questionTxt}</td>
								<td style="text-align: center;">${eachQuest.questionSubject.subjectName}</td>
								<td style="text-align: center;">
									<input type="button" name="editBtn" id="editBtn${iterate.count}" title="Edit" class="btn btn-warning" value="Edit" disabled="disabled">
									<input type="button" name="delBtn" id="delBtn${iterate.count}" title="Delete" class="btn btn-danger" value="Delete" disabled="disabled">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>
