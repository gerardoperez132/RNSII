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
	
	/*
	 * Creación de método para validar una expresión regular del tipo title,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexTitle', function (value) {	
		var valor = value;
		var soloTexto = new RegExp(data['constants']['REGEX_TITLE']);		    	
		return soloTexto.test(valor.toUpperCase());
	});
	
	/*
	 * Creación de método para validar números,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexNumeric', function (value) {		    	
		return $.isNumeric(value);
	});
	
	
	/***********************************************
	 *  Comprueba la fortaleza de las contraseñas mediante los siguientes criterios:
	 * 
	 *  1 Contraseñas que contengan al menos una letra mayúscula.
	 *	2 Contraseñas que contengan al menos una letra minúscula.
	 *	3 Contraseñas que contengan al menos un número o caracter especial.
	 *	4 Contraseñas cuya longitud sea como mínimo 8 caracteres.
	 *		  
	 * */
	$('#pass').keyup(function(e) {  
	 var strongRegex = new RegExp(data['constants']['REGEX_PASS_STRONG'],"g"); 
	 var mediumRegex = new RegExp(data['constants']['REGEX_PASS_MEDIUM'],"g");  
	 var enoughRegex = new RegExp(data['constants']['REGEX_PASS_ENOUGH'],"g");  
	 
	 if (false == enoughRegex.test($(this).val())) {  
		 $('#passstrength').html(data['mensajes']['introduzca_mas_caracteres']); 
		 $('#passstrength').attr('class', 'error_pass');
	 } else if (strongRegex.test($(this).val())) {  
		 $('#passstrength').attr('class', 'ok_pass'); 
		 $('#passstrength').html(data['mensajes']['pass_Strong']);  
	 } else if (mediumRegex.test($(this).val())) {  
		 $('#passstrength').attr('class', 'alert_pass');
		 $('#passstrength').html(data['mensajes']['pass_Medium']);  
	 } else { 
		 $('#passstrength').attr('class', 'error_pass');
		 $('#passstrength').html(data['mensajes']['pass_Enough']);  
	 }  
	 if($('#pass2').val()!=""){
		 $('#passequal').html('');		 
		 $('#pass2').attr('value', '');
	 }
	 return true;  
	 });
	
	$('#pass2').keyup(function(e) {  	
		 if ($(this).val() != $('#pass').val() ) {  
			 $('#passequal').html(data['errores']['error.login.password.match']); 
			 $('#passequal').attr('class', 'error_pass');
		 } else{ 
			 $('#passequal').attr('class', 'ok_pass');
			 $('#passequal').html(data['mensajes']['pass_Equals']);  
		 }  
		 return true;  
	});
	
	/*******************************************************
	 * 
	 * Validación de formulario de Modificar clave
	 * 
	 ******************************************************/
	$("#modificar_clave").click(function (e){		  
		var enoughRegex = new RegExp(data['constants']['REGEX_PASS_ENOUGH'], "g");
		var error = false;
		e.preventDefault();
        if( $("#clave_actual").val() == "" ){
        	$('#passrequired').html(data['errores']['error.required']); 
			$('#passrequired').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#passrequired').html('');
        } 
        
        if ($('#pass').val() == "") {  
			 $('#passstrength').html(data['errores']['error.required']); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
       }else if (false == enoughRegex.test($('#pass').val())) {  
			 $('#passstrength').html(data['errores']['error.login.password.length']); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
        }else{
        	$('#passstrength').html('');
        }
        
        if ($('#pass2').val() == "") {  
			 $('#passequal').html(data['errores']['error.required']); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
       }else if ($('#pass').val() != $('#pass2').val()) {  
			 $('#passequal').html(data['errores']['error.login.password.match']); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
        }else{
        	$('#passequal').html('');
        }
        
        if(error == false){    
        	Sexy.confirm(data['mensajes']['cambiar_clave_acceso']+
        			'<br>'+data['mensajes']['desea_continuar'], {
        	  onComplete:
        	    function(returnvalue) {
        	      if (returnvalue) {        	    	  
        	    	  document.modificarClave.submit(); 
        	      } else {
        	    	  Sexy.alert(data['mensajes']['accion_cancelada']);
        	      }
        	    }
        	  });        	
        }
    });
	
	
	/*******************************************************************
	 * Validación de formulario de Modificar datos del usuario
	 *******************************************************************/
	$("#modificar_datos").click(function (e){
		var soloTexto = new RegExp(data['constants']['REGEX_TITLE']);	
		var error = false;
		e.preventDefault();
        
		if( $("#nombre").val() == "" ){
        	$('#nombre_required').html(data['errores']['error.required']); 
			$('#nombre_required').attr('class', 'error_pass');
            error = true;
        }else if($("#nombre").val().length < 4){
        	$('#nombre_required').html(data['errores']['error.nombre.min']);  
			$('#nombre_required').attr('class', 'error_pass');
            error = true;
        }else if(!soloTexto.test($("#nombre").val().toUpperCase())){
        	$('#nombre_required').html(data['errores']['error.regex.title']);  
			$('#nombre_required').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#nombre_required').html('');
        }
        
        if( $("#apellido").val() == "" ){
        	$('#apellido_required').html(data['errores']['error.required']); 
			$('#apellido_required').attr('class', 'error_pass');
            error = true;
        }else if($("#apellido").val().length < 4){
        	$('#apellido_required').html(data['errores']['error.apellido.min']);  
			$('#apellido_required').attr('class', 'error_pass');
            error = true;
        }else if(!soloTexto.test($("#apellido").val().toUpperCase())){
        	$('#apellido_required').html(data['errores']['error.regex.title']);  
			$('#apellido_required').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#apellido_required').html('');
        }
        
        if( $("#cedula").val() == "" ){
        	$('#cedula_required').html(data['errores']['error.required']); 
			$('#cedula_required').attr('class', 'error_pass');
            error = true;
        }else if($.isNumeric($("#cedula").val()) == false){
        	$('#cedula_required').html(data['errores']['error.num']); 
			$('#cedula_required').attr('class', 'error_pass');
            error = true;
        }else if($.isNumeric($("#cedula").val()) == true){
        	if(!$("#cedula").val() > 0){
	        	$('#cedula_required').html(data['errores']['error.cedula.invalid']); 
				$('#cedula_required').attr('class', 'error_pass');
	            error = true;
        	}
        } else{
        	$('#cedula_required').html('');
        }
        
        if(error == false){        	 
        	Sexy.confirm(data['mensajes']['cambiar_datos_usuario']+
        			'<br>'+data['mensajes']['desea_continuar'], {
        	  onComplete:
        	    function(returnvalue) {
        	      if (returnvalue) {        	    	  
        	    	  document.modificarDatos.submit(); 
        	      } else {
        	    	  Sexy.alert(data['mensajes']['accion_cancelada']);
        	      }
        	    }
        	  }); 
        }
		
	});		
				
	/*******************************************************************
	 * Validaciones para registrar un usuario
	 *******************************************************************/
	$("#registrar_usuario").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	
	    	 'usuario.id_ente' : {min: 1},
	    	 'usuario.nombre' : {required: true, regexTitle: true, minlength: 4},
	    	 'usuario.apellido' : {required: true, regexTitle: true, minlength: 4},
	    	 'usuario.cedula': { required: true, regexNumeric: true,min: 0 ,max: 999999999 },
	    	 'correo':{required: true,email: true }
	    },
	    messages: {
	    	'usuario.id_ente' : {	    		 
	    		min:data['errores']['error.required']},
	    	'usuario.nombre' : {
	    		required:data['errores']['error.required'],
	    		regexTitle:data['errores']['error.regex.title'], 
	    		minlength:data['errores']['error.nombre.min']},
	    	'usuario.apellido' : {
	    		required:data['errores']['error.required'],
	    		regexTitle:data['errores']['error.regex.title'], 
	    		minlength:data['errores']['error.apellido.min']},
	    	 'usuario.cedula': {
	    		 required: data['errores']['error.required'],
	    		 regexNumeric: data['errores']['error.num'], 
	    		 min:data['errores']['error.cedula.range'],
	    		 max:data['errores']['error.cedula.range']},	    	 
	    	 'correo': {
	    		required:data['errores']['error.required'],
	    		email:data['errores']['error.regex.email']}
	    }
	});
	
});
