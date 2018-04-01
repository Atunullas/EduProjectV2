<%@ include file="commons/header.jsp"%>
<div class="container section md-padding" >
	<form action="nextQuest.do" method="post">
		<div>Welcome to Quiz</div>
		<div>
			<input type="text" value="${questions.question}" disabled>
		</div>
		<div>
			<input type="radio" name="selOpt"> &nbsp;
			${questions.optionA}
		</div>
		<div>
			<input type="radio" name="selOpt"> &nbsp;
			${questions.optionB}
		</div>
		<div>
			<input type="radio" name="selOpt"> &nbsp;
			${questions.optionC}
		</div>
		<div>
			<input type="radio" name="selOpt"> &nbsp;
			${questions.optionD}
		</div>
		<input type="hidden" name="curQuestionID" value="${questions.id}">
		<button>Previous</button>
		<button>Next</button>
	</form>
</div>
