<%@ include file="commons/header.jsp"%>
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
<div class="container section md-padding">
	<h3>Upload your Personality here</h3>
	<form name="uploadPersonalityForm" action="savePersonality.do" method="post">
		Name :<input type="text" name="personName" id="personName">
		Age :<input type="text" name="personAge" id="personAge">
		Date of Birth :<input type="text" name="personDOB" id="personDOB">
		Alive : <input type="radio" name="personAlive" id="personAliveYes" value="true"> Yes 
		<input type="radio" name="personAlive"  id="personAliveNo" value="false"> No <br>
		<div id="doe" style="display:none;">
			Date of Expiry :<input type="text" name="personDOD" id="personDOD">
		</div>
		About : <input type="text" name="personAbout" id="personAbout">
		Upload Picture : <input type="file" name="personPic" id="personPic">
		<button>Upload</button>
		<input type="button" onclick="window.location.href ='home.do'" value="Cancel">
	</form>
</div>