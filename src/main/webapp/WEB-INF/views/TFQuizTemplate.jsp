<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container section">
	<form action="nextQuest.do" method="post">
		<div class="section-header text-center">
			<h2 class="white-text">Welcome to Quiz</h2>
		</div>
		<input type="text" value="${questions.question}" class="form-control-plaintext"
			readonly="readonly">
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
