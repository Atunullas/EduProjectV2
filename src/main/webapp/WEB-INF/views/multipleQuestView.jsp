<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function(){
	if("${prevAns}" != '' && "${prevAns}" == 'A'){
		$('#radioA').attr('checked',true);
	}else if("${prevAns}" == 'B'){
		$('#radioB').attr('checked',true);
	}else if("${prevAns}" == 'C'){
		$('#radioC').attr('checked',true);
	}else if("${prevAns}" == 'D'){
		$('#radioD').attr('checked',true);
	}
	
	if("${prevAns}" != ''){
		var ans = "${prevAns}".split(',');
		var arrLength = ans.length;
		var i;
		for (i = 0; i < arrLength; i++) {
			$('#checkbox' + ans[i]).attr('checked',true);
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
			<div class="form-group text-center">
				Question no : ${curQuestNo} out of ${noOfTotalQuizQues}
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label class="form-control">${questions.question}</label>
			</div>
			<div>
				<input type="checkbox" name="selOpt" value="A" id="checkboxA"> &nbsp;
				<label>${questions.optionA}</label>
			</div>
			<div>
				<input type="checkbox" name="selOpt" value="B" id="checkboxB"> &nbsp;
				<label>${questions.optionB}</label>
			</div>
			<div>
				<input type="checkbox" name="selOpt" value="C" id="checkboxC"> &nbsp;
				<label>${questions.optionC}</label>
			</div>
			<div>
				<input type="checkbox" name="selOpt" value="D" id="checkboxD"> &nbsp;
				<label>${questions.optionD}</label>
			</div>
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
