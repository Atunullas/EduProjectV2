<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('input:button[name=editBtn]').click(function() {
		var selectedRow = $("input:checkbox[name=personId]:checked").attr('id');
		$('#editDelForm').attr('action','editPersonSelect.do?personalityId='+$('#'+selectedRow).val()+"&subject=${subject}");
		$('#editDelForm').submit();
	});
	
	$('input:button[name=delBtn]').click(function() {
		var selectedRow = $("input:checkbox[name=personId]:checked").attr('id');
		$('#editDelForm').attr('action','deletePerson.do?personalityId='+$('#'+selectedRow).val()+"&subject=${subject}");
		$('#editDelForm').submit();
	});
	
	$('#delAllBtn').click(function() {
		$("input:checkbox[name=personId]:checked").each(function() {
			$.ajax({
				url: "deletePerson.do?personalityId="+$(this).val()+"&subject=${subject}",
				method:'POST',
				error: function(result){
					$("#div1").html(result);
				},
				complete: function(result){
					$("#div1").html('Delete Successful');
				}		    	
			});
		});
	});
	

	$('#checkAll').click(function(){
		if($('#checkAll').prop('checked')){
			$("input:checkbox[name=personId]").prop('checked',true);
			if($("input:checkbox[name=personId]:checked").length>0){
				$("#delAllBtn").show();
				$('#operationLabel').hide();
			}
		}else{
			$("input:checkbox[name=personId]").prop('checked',false);
			$("#delAllBtn").hide();
			$('#operationLabel').show();
		}
	});

	$("input:checkbox[name=personId]").click(function(){
		if($("input:checkbox[name=personId]:checked").length>1){
			$("input:button[name=editBtn]").attr('disabled',true);
			$("input:button[name=delBtn]").attr('disabled',true);
			$("#delAllBtn").show();
			$('#operationLabel').hide();
		} else if ($("input:checkbox[name=personId]:checked").length>0){
			var selectedRow = $("input:checkbox[name=personId]:checked").attr('id');
			selectedRow = selectedRow.substring(8,selectedRow.length);
			$('#editBtn'+selectedRow).attr('disabled',false);
			$('#delBtn'+selectedRow).attr('disabled',false);
		} else{
			$("input:button[name=editBtn]").attr('disabled',true);
			$("input:button[name=delBtn]").attr('disabled',true);
			$("#delAllBtn").hide();
			$('#operationLabel').show();
		}
		if($("input:checkbox[name=personId]").length == $("input:checkbox[name=personId]:checked").length){
			$('#checkAll').prop('checked',true);
		} else{
			$('#checkAll').prop('checked',false);
		}
	});

});
</script>

<div class="container section" style="padding-top: 30px;">
	<div class="section-header text-center">
		<h2 class="title white-text">Edit or Delete Personality</h2>
		<div class="row">
			<form id="editDelForm" method="post">
				<div id="div1"></div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th style="text-align: center;" class="col-md-1">
								<div><label>Choose</label></div>
								<div><input type="checkbox" id="checkAll" title="Choose All"></div>
							<th class="col-md-3">
								<div><label>First Name</label></div></th>
							<th class="col-md-2">
								<div><label>Last Name</label></div></th>
							<th style="text-align: center;" class="col-md-2">
								<div><label>Personality DOB</label></div></th>
							<th style="text-align: center;" class="col-md-2">
								<div><label>Subject</label></div></th>
							<th style="text-align: center;" class="col-md-2">
								<div><input type="button" id="delAllBtn" title="Delete Selected" class="btn btn-danger" value="Delete Selected" style="display: none;"></div>
								<div id="operationLabel"><label>Operation</label></div>
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allPersonality}" var="eachPerson" varStatus="iterate">
							<tr>
								<td style="text-align: center;">
									<input type="checkbox" name="personId" id="personId${iterate.count}" value="${eachPerson.personId}" required="required">
								</td>
								<td>${eachPerson.firstName}</td>
								<td>${eachPerson.lastName}</td>
								<td style="text-align: center;">${eachPerson.personDOB}</td>
								<td style="text-align: center;">${eachPerson.personSubject.subjectName}</td>
								<td style="text-align: center;">
									<input type="button" name="editBtn" id="editBtn${iterate.count}" title="Edit" class="btn btn-warning" value="Edit" disabled="disabled">
									<input type="button" name="delBtn" id="delBtn${iterate.count}" title="Delete" class="btn btn-danger" value="Delete" disabled="disabled">
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<input type="button" value="Back" class="btn btn-default" onclick="window.location.href='service.do'"/>
		</div>
	</div>
</div>