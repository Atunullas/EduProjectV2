<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container text-center md-padding">
	<c:if test="${noOfTotalQuizQues ne '0'}">
		<div class="row ">The test contains ${noOfTotalQuizQues}
			questions and there is no time limit.</div>
		<div class="row">Count Your Score You will get 1 point for each
			correct answer.</div>
		<div class="row">At the end of the Quiz, your total score will
			be displayed. Maximum score is ${noOfTotalQuizQues} points.</div>
		<br>
		<h2 class="white-text">Good luck!</h2>
		<form action="startQuest.do" method="post">
			<div class="row">
				<button class="btn btn-success">Start the Quiz</button>
			</div>
		</form>
	</c:if>
</div>