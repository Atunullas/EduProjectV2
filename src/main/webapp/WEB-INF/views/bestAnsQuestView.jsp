<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {
		if ("${prevAns}" != '') {
			var ansArry = "${prevAns}".split(',');
			var arrLength = ansArry.length;
			var i;
			for (i = 0; i < arrLength; i++) {
				$('#' + ansArry[i]).attr('checked', true);
			}
		}
	});
</script>
<div class="container section">
	<form action="nextQuest.do" method="post" class="form-horizontal">
		<div class="section-header text-center">
			<h2 class="white-text">Quiz</h2>
		</div>

		<div class="row">
			<div class="form-group text-center">Question no :
				${curQuestNo+1} out of ${noOfTotalQuizQues}</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="form-control">${curQuestion.questionTxt}</label>
			</div>
			<c:forEach items="${curQuestion.options}" var="eachOption">
				<div>
					<input type="radio" name="selOpt" value="${eachOption.optionId}" id="${eachOption.optionId}">
					&nbsp; <label>${eachOption.optionTxt}</label>
				</div>
			</c:forEach>
		</div>
		<input type="hidden" name="curQuestionID" value="${questions.id}">
		<hr>
		<div class="row">
			<div class="form-group">
				<div class="col-sm-6 text-left">
					<c:if test="${isFirst eq false}">
						<button class="btn btn-warning" formaction="prevQuest.do">Previous</button>
					</c:if>
				</div>
				<div class="col-sm-6 text-right">
					<button class="btn btn-success">Next</button>
				</div>
			</div>
		</div>
	</form>
</div>
