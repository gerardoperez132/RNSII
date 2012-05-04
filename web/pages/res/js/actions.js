$(document).ready(function(){
	$("#eliminar_si").click(function (e){
		e.preventDefault();		
		alert("llegue hoy");
	});	
	$("#exa").click(function (e){
		e.preventDefault();
		jConfirm('¿Realemente desea eliminar el servicio de información?', '!Advertencia!', function(r) {
			if(r == true){
				jAlert('Servicio de información eliminado satifactoriamente hhh', 'Confirmación');
				document.hhh_0.submit(); 
			}else{
				jAlert('Acción cancelada', 'Confirmación');
			}		    
		});
	});		
});

function eliminar_SI(i){
	var action = 'id_'+i;	
	var formulario = document.getElementById(action);	
	jConfirm('¿Realemente desea eliminar el servicio de información?', '!Advertencia!', function(r) {
		if(r){
			formulario.submit();
		}else{
			jAlert('Acción cancelada', 'Confirmación');			
		}	
	});		
};