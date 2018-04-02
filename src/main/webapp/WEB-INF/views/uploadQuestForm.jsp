<%@ include file="commons/header.jsp"%>
<script>

$(document).ready(function(){
	$('#truFalse').click(function(){
		if($(this).is(':checked')){
			$('#options').hide();
			$('#truFalDiv').show();
			$('#questType').val('TRUE_FALSE');
			$('.optEmpty').val('');
		}
	});
	
	$('#mulChoice').click(function(){
		if($(this).is(':checked')){
			$('#options').show();
			$('input[type="radio"][name="ans"]').attr('type', 'checkbox'); 
			$('#truFalDiv').hide();
			$('#questType').val('MUL_ANS');
		}
	});
	
	$('#bstAns').click(function(){
		if($(this).is(':checked')){
			$('#options').show();
			$('input[type="checkbox"][name="ans"]').attr('type', 'radio'); 
			$('#truFalDiv').hide();
			$('#questType').val('BEST_ANS');
		}
	});
		
	
});
</script>

<div class="container section md-padding">
	<h3>Upload your Questions here</h3>
	<h5>Please select your Question Type</h5>
	
	<ul>
	<li><input type="radio" name="qType" id="truFalse"> True or False</li>
	<li><input type="radio" name="qType" id="mulChoice"> Multiple Choice</li>
	<li><input type="radio" name="qType" id="bstAns"> Best Answer</li>
	</ul>
	
	<form name="uploadQuestForm" action="saveUploadQuest.do" method="post">
		Question : <input type="text" name="question" id="question">
	
	<div id="options">
		<input type="checkbox" name="ans" value="A">
		Option A : <input type="text" name="optionA" id="optionA" class="optEmpty">
		<input type="checkbox" name="ans" value="B">
		Option B : <input type="text" name="optionB" id="optionB" class="optEmpty">
		<input type="checkbox" name="ans" value="C">
		Option C : <input type="text" name="optionC" id="optionC" class="optEmpty">
		<input type="checkbox" name="ans" value="D">
		Option D : <input type="text" name="optionD" id="optionD" class="optEmpty">
	</div>
	
	<div id="truFalDiv" style="display:none;">
		<input type="radio" name="truFalAns" value="A">True
		<input type="radio" name="truFalAns" value="B"> False 
	</div>
		
		<input type="hidden" name="questType" id="questType" value="MUL_ANS">
		<button>Upload</button>
		<input type="button" onclick="window.location.href ='home.do'" value="Cancel">
	</form>
</div>