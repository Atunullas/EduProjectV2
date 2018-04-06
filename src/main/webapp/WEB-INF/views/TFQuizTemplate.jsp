<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container section md-padding" >
	<form action="nextQuest.do" method="post">
		<div>Welcome to Quiz</div>
		<div>
			<input type="text" value="${questions.question}" disabled>
		</div>
		<div>
			<input type="radio" name="selOpt" value="A"> &nbsp;
			${questions.optionA}
		</div>
		<div>
			<input type="radio" name="selOpt" value="B"> &nbsp;
			${questions.optionB}
		</div>
		<input type="hidden" name="curQuestionID" value="${questions.id}">
		<button>Previous</button>
		<button>Next</button>
	</form>
</div>
