/*
 * Variable con las claves de intercionalización del archivo json. 
 */
var data;

$(document).ready(function() {	
	
	/*
	 * Obteniendo los valores de intercionalización del archivo JSON
	 */
	$.ajax({
		url: "getJSONResult.action",		
		type: "GET",
		dataType: "json",
		async:false,		
		success: function(source){	
			data = source;			
		}	
	});
	
	/*******************************************************
	 * 
	 * Validación de formulario de suscripción
	 * 
	 ******************************************************/
	$("#enviar_solicitud").click(function (e){	
		
		e.preventDefault();
		
		var enoughRegex = new RegExp(data['constants']['REGEX_TITLE']);
		var enoughRegex2 = new RegExp(data['constants']['REGEX_EMAIL']);
		var enoughRegex3 = new RegExp(data['constants']['REGEX_DESCRIPTION']);
		
		var error = false;
		
		if( $("#nombre").val() == "" ){
			$('#m_nombre').attr('class','error_pass').html(data['errores']['error.required']); 
			error = true;
        }else if($("#nombre").val().length < 4){
        	$('#m_nombre').attr('class','error_pass').html(data['errores']['error.nombre.min']);
        	error = true;
        }else if(!enoughRegex.test($('#nombre').val().toUpperCase())){        	
        	$('#m_nombre').html(data['errores']['error.regex.title']);
        	$('#m_nombre').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_nombre').attr('class','ok_pass').html('OK');
        }
		
		if( $("#cargo").val() == "" ){
			$('#m_cargo').attr('class','error_pass').html(data['errores']['error.required']);  
			error = true;
        }else if($("#cargo").val().length < 4){
        	$('#m_cargo').attr('class','error_pass').html(data['errores']['error.cargo.min']);
        	error = true;
        }else if(!enoughRegex.test($('#cargo').val().toUpperCase())){        	
        	$('#m_cargo').html(data['errores']['error.regex.title']);
        	$('#m_cargo').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_cargo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#telefono").val() == "" ){
			$('#m_telefono').attr('class','error_pass').html(data['errores']['error.required']);    
			error = true;
        }else if($("#telefono").val().length < 7){
        	$('#m_telefono').attr('class','error_pass').html(data['errores']['error.servicio.telefono.digit ']);
        	error = true;
        }else if(!$.isNumeric($('#telefono').val())){        	
        	$('#m_telefono').html(data['errores']['error.num']);
        	$('#m_telefono').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_telefono').attr('class','ok_pass').html('OK');
        }
		
		if( $("#correo").val() == "" ){
			$('#m_correo').attr('class','error_pass').html(data['errores']['error.required']);   
			error = true;
        }else if(!enoughRegex2.test($('#correo').val().toUpperCase())){        	
        	$('#m_correo').html(data['errores']['error.regex.email']);
        	$('#m_correo').attr('class','error_pass');
        	error = true;
        }else{        	
        	$('#m_correo').attr('class','ok_pass').html('OK');
        }
		
		if( $("#motivo").val() == "" ){
			$('#m_motivo').attr('class','error_pass').html(data['errores']['error.required']); 
			error = true;
        }else if($("#motivo").val().length < 20){
        	$('#m_motivo').attr('class','error_pass').html(data['errores']['error.motivo.min']);
        	error = true;
        }else if(!enoughRegex3.test($('#motivo').val().toUpperCase())){        	
        	$('#m_motivo').html(data['errores']['error.regex.description']);
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
		var enoughRegex = new RegExp(data['constants']['REGEX_TITLE']);
		
		if($(this).val() == ''){       	
        	$('#m_nombre').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val().toUpperCase())){        	
        	$('#m_nombre').html(data['errores']['error.regex.title']);
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
		var enoughRegex = new RegExp(data['constants']['REGEX_TITLE']);
		
		if($(this).val() == ''){       	
        	$('#m_cargo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val().toUpperCase())){        	
        	$('#m_cargo').html(data['errores']['error.regex.title']);
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
        	$('#m_telefono').html(data['errores']['error.num']);
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
		var enoughRegex = new RegExp(data['constants']['REGEX_EMAIL']);
		if($(this).val() == ''){       	
        	$('#m_correo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val())){        	
        	$('#m_correo').html(data['errores']['error.regex.email']);
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
		var enoughRegex = new RegExp(data['constants']['REGEX_DESCRIPTION']);
		
		if($(this).val() == ''){       	
        	$('#m_motivo').attr('class','error_pass').html('*');        	
        }else if(!enoughRegex.test($(this).val().toUpperCase())){        	
        	$('#m_motivo').html(data['errores']['error.regex.description']);
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
		var enoughRegex = new RegExp(data['constants']['REGEX_DESCRIPTION']);
		var error = false;
		
		//Validando el motivo de la decisión
		if($('#motivo').val() == ''){       	
        	$('#m_motivo').attr('class','error_pass').html(data['errores']['error.required']);  
        	error = true;
        }else if(!enoughRegex.test($('#motivo').val().toUpperCase())){        	
        	$('#m_motivo').html(data['errores']['error.regex.description']);
        	$('#m_motivo').attr('class','error_pass');     
        	error = true;
        }else if($('#motivo').val().length < 20){       	
        	$('#m_motivo').attr('class','alert_pass').html(data['errores']['error.motivo.min']);
        	error = true;
        }
		
		//validando que escoja una decisión
		if($('#ops1').val() == '' && $('#ops2').val() == '' ){       	
        	$('#m_sentencia').attr('class','error_pass').html(data['errores']['error.required']);
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
	$("input[@name='solicitud.sentencia']:radio").change(function(e) {		
		$('#m_sentencia').attr('class','ok_pass').html('OK');
	});
	
});
