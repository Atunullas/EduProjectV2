<jsp:include page="commons/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="text-center md-padding">
		<h2 class="white-text">${errorMessage}</h2>
		<c:if test="${showClose eq true}">
			<button class="btn btn-success" onclick="javascript:window.close();window.location.href='service.do'">Click to Close</button>
		</c:if>
	</div>
</div>