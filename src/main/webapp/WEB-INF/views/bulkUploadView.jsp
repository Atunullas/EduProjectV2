<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function() {
		$("#uploadCSV").submit(function(e) {
			if ($('#csvType').val() == 1) {
				$("#uploadCSV").attr('action', "bulkUploadQuestion.do");
				$("#uploadCSV").submit();
			} else if ($('#csvType').val() == 2) {
				$("#uploadCSV").attr('action', "bulkUploadPersonality.do");
				$("#uploadCSV").submit();
			} else if ($('#csvType').val() == 3) {
				$("#uploadCSV").attr('action', "bulkUploadSubject.do");
				$("#uploadCSV").submit();
			} else {
				e.preventDefault();
			}
			return;
		});
	});
</script>
<div class="container section white-text">
	<div class="section-header text-center">
		<h3 class="title white-text">Bulk Upload</h3>
	</div>
	<form id="uploadCSV" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="labelForUploadType">Choose the Upload Type</label> <select
				name="csvType" class="form-control" id="csvType">
				<option value="0" selected="selected">Please choose the
					Upload Type</option>
				<option value="1">Upload Questions as CSV</option>
				<option value="2">Upload Personalities as CSV</option>
				<option value="3">Upload Subject as CSV</option>
			</select>
		</div>
		<div class="form-group">
			<input type="file" name="csvFile" />
		</div>
		<input type="submit" value="Submit" class="btn btn-primary" />
		<input type="reset" value="Reset" class="btn btn-default" />
		<input type="button" value="Cancel" class="btn btn-warning" onclick="location.href='service.do'"/>
		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>
		<ul class="row">
			<li> <i class="fa fa-info-circle"></i> To get the CSV template click below</li>
			<li> <i class="fa fa-file-excel-o"></i> <a href="downloadQuestCSVFile.do">Question CSV Template</a></li>
			<li> <i class="fa fa-file-excel-o"></i> <a href="downloadPersnCSVFile.do">Noble Personality CSV Template</a></li>
			<li> <i class="fa fa-file-excel-o"></i> <a href="downloadSubjectCSVFile.do">Subject CSV Template</a></li>
		</ul>
	</form>
</div>