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
		var enoughRegex3 = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");
		
		var error = false;
		
		if( $("#nombre").val() == "" ){
			$('#m_nombre').attr('class','error_pass').html('Debe llenar este campo'); 
			error = true;
        }else if($("#nombre").val().length < 4){
        	$('#m_nombre').attr('class','error_pass').html('Su nombre debe poseer al menos 4 caracteres');
        	error = true;
        }else if(!enoughRegex.test($('#nombre').val())){        	
        	$('#m_nombre').html('Sólo puede introducir letras y espacios');
        	$('#m_nombre').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_nombre').attr('class','ok_pass').html('OK');
        }
		
		if( $("#cargo").val() == "" ){
			$('#m_cargo').attr('class','error_pass').html('Debe llenar este campo');  
			error = true;
        }else if($("#cargo").val().length < 4){
        	$('#m_cargo').attr('class','error_pass').html('Su cargo debe poseer al menos 4 caracteres');
        	error = true;
        }else if(!enoughRegex.test($('#cargo').val())){        	
        	$('#m_cargo').html('Sólo puede introducir letras y espacios');
        	$('#m_cargo').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_cargo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#telefono").val() == "" ){
			$('#m_telefono').attr('class','error_pass').html('Debe llenar este campo');    
			error = true;
        }else if($("#telefono").val().length < 7){
        	$('#m_telefono').attr('class','error_pass').html('Debe introducir un número telefónico válido de 7 dígitos');
        	error = true;
        }else if(!$.isNumeric($('#telefono').val())){        	
        	$('#m_telefono').html('Sólo puede introducir números');
        	$('#m_telefono').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_telefono').attr('class','ok_pass').html('OK');
        }
		
		if( $("#correo").val() == "" ){
			$('#m_correo').attr('class','error_pass').html('Debe llenar este campo');   
			error = true;
        }else if(!enoughRegex2.test($('#correo').val())){        	
        	$('#m_correo').html('Esta dirección de correo electrónico es inválida');
        	$('#m_correo').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_correo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#motivo").val() == "" ){
			$('#m_motivo').attr('class','error_pass').html('Debe llenar este campo'); 
			error = true;
        }else if($("#motivo").val().length < 20){
        	$('#m_motivo').attr('class','error_pass').html('Su motivo debe poseer al menos 20 caracteres');
        	error = true;
        }else if(!enoughRegex3.test($('#motivo').val())){        	
        	$('#m_motivo').html('Sólo puede introducir letras, números y puntos');
        	$('#m_motivo').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_motivo').attr('class','ok_pass').html('OK');
        }
		
		if(error == false){        	 
        	document.suscripcionForm.submit(); 
        }
	});
	
	/*******************************************************
	 * 
	 * Validando el campo nombre al teclear
	 * 
	 ******************************************************/	
	$('#nombre').keyup(function(e) {		
		var enoughRegex = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");
		
		if($(this).val() == ''){       	
        	$('#m_nombre').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val())){        	
        	$('#m_nombre').html('Sólo puede introducir letras y espacios');
        	$('#m_nombre').attr('class','error_pass');        	
        }else if($(this).val().length < 4){       	
        	$('#m_nombre').attr('class','alert_pass').html(''+(4 - $(this).val().length));        	
        }else{        	
        	$('#m_nombre').attr('class','ok_pass').html('OK');
        }
	});
	
	/*******************************************************
	 * 
	 * Validando el campo cargo al teclear
	 * 
	 ******************************************************/	
	$('#cargo').keyup(function(e) {		
		var enoughRegex = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");
		
		if($(this).val() == ''){       	
        	$('#m_cargo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val())){        	
        	$('#m_cargo').html('Sólo puede introducir letras y espacios');
        	$('#m_cargo').attr('class','error_pass');        	
        }else if($(this).val().length < 4){       	
        	$('#m_cargo').attr('class','alert_pass').html(''+(4 - $(this).val().length));        	
        }else{        	
        	$('#m_cargo').attr('class','ok_pass').html('OK');
        }
	});
	
	/*******************************************************
	 * 
	 * Validando el campo telefono al teclear
	 * 
	 ******************************************************/	
	$('#telefono').keyup(function(e) {		
		if($(this).val() == ''){       	
        	$('#m_telefono').attr('class','error_pass').html('*');        	
        }else if(!$.isNumeric($(this).val())){        	
        	$('#m_telefono').html('Sólo puede introducir números');
        	$('#m_telefono').attr('class','error_pass');       	
        }else if($(this).val().length < 7){       	
        	$('#m_telefono').attr('class','alert_pass').html(''+(7 - $(this).val().length));        	
        }else{        	
        	$('#m_telefono').attr('class','ok_pass').html('OK');
        }
	});
	
	/*******************************************************
	 * 
	 * Validando el campo correo al teclear
	 * 
	 ******************************************************/	
	$('#correo').change(function(e) {		
		var enoughRegex = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");		
		if($(this).val() == ''){       	
        	$('#m_correo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val())){        	
        	$('#m_correo').html('Esta dirección de correo electrónico es inválida');
        	$('#m_correo').attr('class','error_pass');        	
        }else{        	
        	$('#m_correo').attr('class','ok_pass').html('OK');
        }
	});
	
	/*******************************************************
	 * 
	 * Validando el campo motivo al teclear
	 * 
	 ******************************************************/	
	$('#motivo').keyup(function(e) {		
		var enoughRegex = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");
		
		if($(this).val() == ''){       	
        	$('#m_motivo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val())){        	
        	$('#m_motivo').html('Sólo puede introducir letras, números y puntos');
        	$('#m_motivo').attr('class','error_pass');        	
        }else if($(this).val().length < 20){       	
        	$('#m_motivo').attr('class','alert_pass').html(''+(20 - $(this).val().length));        	
        }else{        	
        	$('#m_motivo').attr('class','ok_pass').html('OK');
        }
	});
	
	
	/*******************************************************
	 * 
	 * Validando el formulario para sentenciar la solicitud de una suscripción
	 * 
	 ******************************************************/	
	$('#sentenciar').click(function (e){	
		
		e.preventDefault();		
		var enoughRegex = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");
		var error = false;
		
		//Validando el motivo de la decisión
		if($('#motivo').val() == ''){       	
        	$('#m_motivo').attr('class','error_pass').html('* Debe introducir el motivo del dictamen');  
        	error = true;
        }else if(!enoughRegex.test($('#motivo').val())){        	
        	$('#m_motivo').html('Sólo puede introducir letras, números y puntos');
        	$('#m_motivo').attr('class','error_pass');     
        	error = true;
        }else if($('#motivo').val().length < 20){       	
        	$('#m_motivo').attr('class','alert_pass').html('Su motivo debe poseer al menos 20 caracteres');
        	error = true;
        }
		
		//validando que escoja una decisión
		if($('#ops1').val() == '' && $('#ops2').val() == '' ){       	
        	$('#m_sentencia').attr('class','error_pass').html('Debe elegir un dictamen de la solicitud de suscripción');
        	error = true;
        }
		
		if(error == false){        	 
        	document.dictamenForm.submit(); 
        }
		
	});	
	
	/*******************************************************
	 * 
	 * mostrando un mensaje 'OK' en el dictamen
	 * 
	 ******************************************************/
	$("input[@name='ops']:radio").change(function(e) {		
		$('#m_sentencia').attr('class','ok_pass').html('OK');
	});
	
	
	
	
	
});
