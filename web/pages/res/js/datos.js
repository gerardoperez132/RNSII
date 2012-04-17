$(document).ready(function(){
	$("#tipoDato").change(function (e){	
		if($("#tipoDato").val()== -1){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==2){
			$('#string').attr('style', 'visibility: visible; position:relative;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==3){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: visible; position:relative;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==4){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: visible; position:relative;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==5){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: visible; position:relative;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==6){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: visible; position:relative;');
			$('#time').attr('style', 'visibility: hidden; position:fixed;');
		}else if($("#tipoDato").val()==7){
			$('#string').attr('style', 'visibility: hidden; position:fixed;');
			$('#integer').attr('style', 'visibility: hidden; position:fixed;');
			$('#decimal').attr('style', 'visibility: hidden; position:fixed;');
			$('#boolean').attr('style', 'visibility: hidden; position:fixed;');
			$('#date').attr('style', 'visibility: hidden; position:fixed;');
			$('#time').attr('style', 'visibility: visible; position:relative;');
		}
		
		
	});
});