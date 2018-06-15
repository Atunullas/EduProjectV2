<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {

		$('#mulChoice').click(function() {
			$('#mulChoice').addClass("active");
			$('#choose').removeClass("active");
			$('#trueFalse').removeClass("active");
			$('#uploadQuestForm').show();
			$('#uploadTFQuestForm').hide();
			$('input[type="radio"][name="isAns"]').attr('type', 'checkbox');
			$('#questionType').val('MUL_ANS');
			$('#resetBtn').show();
		});

		$('#choose').click(function() {
			$('#choose').addClass("active");
			$('#mulChoice').removeClass("active");
			$('#trueFalse').removeClass("active");
			$('#uploadQuestForm').show();
			$('#uploadTFQuestForm').hide();
			$('input[type="checkbox"][name="isAns"]').attr('type', 'radio');
			$('#questionType').val('BEST_ANS');
			$('#resetBtn').show();
		});

		$('#trueFalse').click(function() {
			$('#trueFalse').addClass("active");
			$('#mulChoice').removeClass("active");
			$('#choose').removeClass("active");
			$('#uploadQuestForm').hide();
			$('#uploadTFQuestForm').show();
			$('#resetBtn').show();
		});

	});
</script>
<div class="container section" style="padding-top: 30px;">
	<div class="section-header text-center">
		<h2 class="title white-text">Upload your Questions</h2>
		<ul class="nav nav-tabs">
			<li class="active" id="mulChoice"><a href="#">Multiple
					Choice Question</a></li>
			<li id="choose"><a href="#">Choose the best answer</a></li>
			<li id="trueFalse"><a href="#">True or False Question</a></li>
			<li style="float: right;"><select name="subject"
				class="form-control">
					<option selected="selected" value="">Choose the Subject</option>
					<c:forEach items="${allSubjects}" var="eachSubject">
						<option value="${eachSubject}">${eachSubject}</option>
					</c:forEach>
			</select></li>
		</ul>
	</div>

	<form id="uploadQuestForm" name="uploadQuestForm"
		action="saveUploadQuest.do" method="post" class="form-horizontal"
		autocomplete="off">
		<div class="form-group">
			<label class="control-label col-sm-2">Question :</label>
			<div class="col-sm-10">
				<textarea name="questionTxt" id="question"
					class="quesEmpty form-control" required="required"
					placeholder="Please type your Question here.."></textarea>
			</div>
		</div>

		<div id="options">
			<div class="form-group">
				<label class="control-label col-sm-2">Option A : <input
					type="checkbox" name="isAns" value="A"
					title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionTxt" id="optionA"
						class="optEmpty form-control"
						placeholder="Please type Option A here.." required="required">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">Option B : <input
					type="checkbox" name="isAns" value="B"
					title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionTxt" id="optionB"
						class="optEmpty form-control"
						placeholder="Please type Option B here.." required="required">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">Option C : <input
					type="checkbox" name="isAns" value="C"
					title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionTxt" id="optionC"
						class="optEmpty form-control"
						placeholder="Please type Option C here.." required="required">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2">Option D : <input
					type="checkbox" name="isAns" value="D"
					title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionTxt" id="optionD"
						class="optEmpty form-control"
						placeholder="Please type Option D here.." required="required">
				</div>
			</div>
		</div>

		<input type="hidden" name="questionType" id="questionType"
			value="MUL_ANS">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="upload" class="btn btn-success">Upload</button>
				<input type="reset" class="btn btn-warning" id="resetBtn"
					value="Reset"> <input type="button" class="btn btn-danger"
					onclick="window.location.href ='service.do'" value="Cancel">
			</div>
		</div>
	</form>

	<form id="uploadTFQuestForm" name="uploadQuestForm"
		action="saveUploadQuest.do" method="post" class="form-horizontal"
		style="display: none;" autocomplete="off">
		<div class="form-group">
			<label class="control-label col-sm-2">Question :</label>
			<div class="col-sm-10">
				<textarea name="questionTxt" id="question"
					class="quesEmpty form-control" required="required"
					placeholder="Please type your Question here.."></textarea>
			</div>
		</div>

		<div class="form-group">
			<div id="truFalDiv" class="white-text col-sm-offset-2 col-sm-10">
				<input type="radio" name="isAns" value="True"> &nbsp; <label>True</label>
				&nbsp; <input type="radio" name="False" value="B"> &nbsp; <label>False</label>
			</div>
		</div>

		<input type="hidden" name="questionType" value="TRUE_FALSE">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="upload" class="btn btn-success">Upload</button>
				<input type="reset" class="btn btn-warning" id="resetTFBtn"
					value="Reset"> <input type="button" class="btn btn-danger"
					onclick="window.location.href ='service.do'" value="Cancel">
			</div>
		</div>
	</form>
</div>