$(document).ready(function(){
	/*******************************************************
	 * 
	 * Validación de formulario de suscripción
	 * 
	 ******************************************************/
	$("#enviar_solicitud").click(function (e){	
		e.preventDefault();
		var enoughRegex = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");
		var enoughRegex2 = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		var enoughRegex3 = new RegExp("^[a-zA-Z0-9 áéíóúAÉÍÓÚÑñ]+$");
		
		if( $("#nombre").val() == "" ){
			$('#m_nombre').attr('class','error_pass').html('Debe llenar este campo');       	 
        }else if($("#nombre").val().length < 4){
        	$('#m_nombre').attr('class','error_pass').html('Su nombre debe poseer al menos 4 caracteres');
        }else if(!enoughRegex.test($('#nombre').val())){        	
        	$('#m_nombre').html('Sólo puede introducir letras y espacios');
        	$('#m_nombre').attr('class','error_pass');
        }else{        	
        	$('#m_nombre').attr('class','ok_pass').html('OK');
        }
		
		if( $("#cargo").val() == "" ){
			$('#m_cargo').attr('class','error_pass').html('Debe llenar este campo');       	 
        }else if($("#cargo").val().length < 4){
        	$('#m_cargo').attr('class','error_pass').html('Su cargo debe poseer al menos 4 caracteres');
        }else if(!enoughRegex.test($('#cargo').val())){        	
        	$('#m_cargo').html('Sólo puede introducir letras y espacios');
        	$('#m_cargo').attr('class','error_pass');
        }else{        	
        	$('#m_cargo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#telefono").val() == "" ){
			$('#m_telefono').attr('class','error_pass').html('Debe llenar este campo');       	 
        }else if($("#telefono").val().length < 7){
        	$('#m_telefono').attr('class','error_pass').html('Debe introducir un número telefónico válido de 7 dígitos');
        }else if(!$.isNumeric($('#telefono').val())){        	
        	$('#m_telefono').html('Sólo puede introducir números');
        	$('#m_telefono').attr('class','error_pass');
        }else{        	
        	$('#m_telefono').attr('class','ok_pass').html('OK');
        }
		
		if( $("#correo").val() == "" ){
			$('#m_correo').attr('class','error_pass').html('Debe llenar este campo');       	 
        }else if(!enoughRegex2.test($('#correo').val())){        	
        	$('#m_correo').html('Esta dirección de correo electrónico es inválida');
        	$('#m_correo').attr('class','error_pass');
        }else{        	
        	$('#m_correo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#motivo").val() == "" ){
			$('#m_motivo').attr('class','error_pass').html('Debe llenar este campo');       	 
        }else if($("#motivo").val().length < 20){
        	$('#m_motivo').attr('class','error_pass').html('Su motivo debe poseer al menos 20 caracteres');
        }else if(!enoughRegex3.test($('#motivo').val())){        	
        	$('#m_motivo').html('Sólo puede introducir letras y espacios');
        	$('#m_motivo').attr('class','error_pass');
        }else{        	
        	$('#m_motivo').attr('class','ok_pass').html('OK');
        }
		
		 
	});
});