<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container text-center md-padding">
	<div class="row ">
		The Test The test contains ${totQuests} questions and there is no time limit.
	</div>
	<div class="row">
		Count Your Score You will get 1 point for each correct answer.
	</div>
	<div class="row">
		At the end of the Quiz, your total score will be displayed. Maximum score is ${totQuests} points. 
	</div>
	<br>
	<h2 class="white-text">Good luck!</h2>
	<form action="startQuest.do">
		<div class="row">
			<button class="btn btn-success">Start the Quiz </button>
		</div>
	</form>
</div>