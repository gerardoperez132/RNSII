$(document).ready(function(){
	$("#tipoDato").change(function (e){		
		if($("#tipoDato").val()==2){
			$('#string').attr('style', 'visibility: visible; position:relative;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==3){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: visible; position:relative;');
		}
	});
});