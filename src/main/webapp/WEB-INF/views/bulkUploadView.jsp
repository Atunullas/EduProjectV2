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
	<div>
		Choose the Upload type <select name="csvType" id="csvType">
			<option value="0" selected="selected">Choose the Upload type</option>
			<option value="1">Upload Questions as CSV</option>
			<option value="2">Upload Personalities as CSV</option>
		</select>
	</div>
	<form id="uploadCSV" enctype="multipart/form-data" method="post"
		action="bulkUploadQuestion.do">
		<input type="file" name="csvFile" /> <input type="submit"
			value="Submit" />
	</form>
</div>
