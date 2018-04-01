<%@ include file="commons/header.jsp"%>
<div class="container section md-padding">
	<form name="uploadQuestForm" action="saveUploadQuest.do" method="post">
		<div>Upload your Questions here</div>
		Question : <input type="text" name="question">
		Option A : <input type="text" name="optionA">
		Option B : <input type="text" name="optionB">
		Option C : <input type="text" name="optionC">
		Option D : <input type="text" name="optionD">
		<input type="hidden" name="questType" value="MUL">
		<button>Upload</button>
		<button>Cancel</button>
	</form>
</div>