<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container section md-padding" >
	<form action="nextQuest.do" method="post">
		<div>Welcome to Quiz</div>
		<div>
			<input type="text" value="${questions.question}" class="form-control" readonly="readonly">
		</div>
		<div>
			<input type="checkbox" name="selOpt" value="A"> &nbsp;
			${questions.optionA}
		</div>
		<div>
			<input type="checkbox" name="selOpt" value="B"> &nbsp;
			${questions.optionB}
		</div>
		<div>
			<input type="checkbox" name="selOpt" value="C"> &nbsp;
			${questions.optionC}
		</div>
		<div>
			<input type="checkbox" name="selOpt" value="D"> &nbsp;
			${questions.optionD}
		</div>
		<input type="hidden" name="curQuestionID" value="${questions.id}">
		<button>Previous</button>
		<button>Next</button>
	</form>
</div>
