<jsp:include page="commons/header.jsp"></jsp:include>
<script>
$(document).ready(function(){
	$('input[type="radio"][name="personAlive"]').click(function(){
		if($('#personAliveYes').is(':checked')){
			$('#doe').hide();
		} else{
			$('#doe').show();
		}
	});
});	
</script>
<div class="container section white-text">
	<div class="section-header text-center">
		<h3 class="title white-text">Upload your Personality here</h3>
	</div>
	<form name="uploadPersonalityForm" action="savePersonality.do" method="post">
		Name :<input type="text" name="personName" id="personName">
		Age :<input type="text" name="personAge" id="personAge">
		Date of Birth :<input type="date" name="personDOB" id="personDOB">
		Alive : <input type="radio" name="personAlive" id="personAliveYes" value="true"> Yes 
		<input type="radio" name="personAlive"  id="personAliveNo" value="false"> No <br>
		<div id="doe" style="display:none;">
			Date of Expiry :<input type="date" name="personDOD" id="personDOD">
		</div>
		About : <input type="text" name="personAbout" id="personAbout">
		Upload Picture : 	<i class="fa fa-user"></i>
		<input type="text" name="personPic" id="personPic">
		<br>
		<button class="main-color">Upload</button>
		<input class="main-color" type="button" onclick="window.location.href ='home.do'" value="Cancel">
	</form>
</div>