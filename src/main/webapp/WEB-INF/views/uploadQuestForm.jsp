<jsp:include page="commons/header.jsp"></jsp:include>
<script>
	$(document).ready(function() {

		$('#option1Label').click(function() {
			$('#uploadQuestForm').show();
			$('#uploadTFQuestForm').hide();
			$('input[type="radio"][name="ans"]').attr('type', 'checkbox');
			$('#questType').val('MUL_ANS');
			$('#resetBtn').show();
		});

		$('#option2Label').click(function() {
			$('#uploadQuestForm').show();
			$('#uploadTFQuestForm').hide();
			$('input[type="checkbox"][name="ans"]').attr('type', 'radio');
			$('#questType').val('BEST_ANS');
			$('#resetBtn').show();
		});

		$('#option3Label').click(function() {
			$('#uploadQuestForm').hide();
			$('#uploadTFQuestForm').show();
			$('#resetBtn').show();
		});
		
		$('#resetBtn').click(function(){
			$('.optEmpty').val('');
			$('.quesEmpty').val('');
		});
		
		$('#resetTFBtn').click(function(){
			$('.optEmpty').val('');
			$('.quesEmpty').val('');
		});

	});
</script>

<div class="container section" style="padding-top: 30px;">
	<div class="section-header text-center">
		<h2 class="title white-text">Upload your Questions</h2>
		<label>Please choose your Question Type &nbsp;</label>
		<i class="fa fa-arrow-right"></i>
		<div class="btn-group btn-group-toggle" data-toggle="buttons">
			<label class="btn btn-primary active" id="option1Label">
			<input type="radio" name="options" id="option1" autocomplete="off">
				Multiple Answer
			</label>
			<label class="btn btn-primary" id="option2Label">
			<input type="radio" name="options" id="option2" autocomplete="off">
				Best Answer
			</label>
			<label class="btn btn-primary" id="option3Label">
			<input type="radio" name="options" id="option3" autocomplete="off">
				True or False
			</label>
		</div>
	</div>


	<form id="uploadQuestForm" name="uploadQuestForm" action="saveUploadQuest.do" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-sm-2">Question :</label>
			<div class="col-sm-10">
				<textarea name="question" id="question" class="quesEmpty form-control" required="required" placeholder="Please type your Question here.." ></textarea>
			</div>
		</div>

		<div id="options">
			<div class="form-group">
				<label class="control-label col-sm-2">Option A : 
					<input type="checkbox" name="ans" value="A" title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionA" id="optionA" class="optEmpty form-control" placeholder="Please type Option A here.." required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Option B :
					<input type="checkbox" name="ans" value="B" title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionB" id="optionB" class="optEmpty form-control" placeholder="Please type Option B here.." required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Option C :
					<input type="checkbox" name="ans" value="C" title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionC" id="optionC" class="optEmpty form-control" placeholder="Please type Option C here.." required="required">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Option D :
					<input type="checkbox" name="ans" value="D" title="Check if it's the Correct Answer">
				</label>
				<div class="col-sm-10">
					<input type="text" name="optionD" id="optionD" class="optEmpty form-control" placeholder="Please type Option D here.." required="required">
				</div>
			</div>
		</div>
		
		<input type="hidden" name="questType" id="questType" value="MUL_ANS">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="upload" class="btn btn-success">Upload</button>
				<input type="button" class="btn btn-warning" id="resetBtn" value="Reset">
				<input type="button" class="btn btn-danger" onclick="window.location.href ='service.do'" value="Cancel">
			</div>
		</div>
	</form>
		
	<form id="uploadTFQuestForm" name="uploadQuestForm" action="saveUploadQuest.do" method="post" class="form-horizontal" style="display:none;">
		<div class="form-group">
			<label class="control-label col-sm-2">Question :</label>
			<div class="col-sm-10">
				<textarea name="question" id="question" class="quesEmpty form-control" required="required" placeholder="Please type your Question here.."></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<div id="truFalDiv" class="white-text col-sm-offset-2 col-sm-10">
				<input type="radio" name="truFalAns" value="A"> &nbsp;
				<label>True</label> &nbsp;
				<input type="radio" name="truFalAns" value="B"> &nbsp;
				<label>False</label>
			</div>
		</div>
		
		<input type="hidden" name="questType" value="TRUE_FALSE">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="upload" class="btn btn-success">Upload</button>
				<input type="button" class="btn btn-warning" id="resetTFBtn" value="Reset">
				<input type="button" class="btn btn-danger" onclick="window.location.href ='service.do'" value="Cancel">
			</div>
		</div>
	</form>
</div>