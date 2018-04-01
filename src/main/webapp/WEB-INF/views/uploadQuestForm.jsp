<%@ include file="commons/header.jsp"%>
<script>

$(document).ready(function(){
	$('#truFalse').click(){
		if($(this).is(':checked')){
			$('#optionA').hide();
			$('#optionB').hide();
			$('#optionC').hide();
			$('#optionD').hide();
		}	
	}
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
		<input type="checkbox" name="ans">
		Option A : <input type="text" name="optionA" id="optionA">
		<input type="checkbox" name="ans">
		Option B : <input type="text" name="optionB" id="optionB">
		<input type="checkbox" name="ans">
		Option C : <input type="text" name="optionC" id="optionC">
		<input type="checkbox" name="ans">
		Option D : <input type="text" name="optionD" id="optionD">
		<input type="hidden" name="questType" value="MUL">
		<button>Upload</button>
		<input type="button" onclick="window.location.href ='home.do'" value="Cancel">
	</form>
</div>