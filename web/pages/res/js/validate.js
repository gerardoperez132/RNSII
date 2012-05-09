$(document).ready(function(){
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
	 var strongRegex = new RegExp("(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,})$", "g");  
	 var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");  
	 var enoughRegex = new RegExp("(?=.{6,}).*", "g");  
	 
	 if (false == enoughRegex.test($(this).val())) {  
		 $('#passstrength').html('Introdusca más caracteres'); 
		 $('#passstrength').attr('class', 'error_pass');
	 } else if (strongRegex.test($(this).val())) {  
		 $('#passstrength').attr('class', 'ok_pass'); 
		 $('#passstrength').html('Fortaleza de la Contraseña Fuerte!');  
	 } else if (mediumRegex.test($(this).val())) {  
		 $('#passstrength').attr('class', 'alert_pass');
		 $('#passstrength').html('Fortaleza de la Contraseña Media!');  
	 } else { 
		 $('#passstrength').attr('class', 'error_pass');
		 $('#passstrength').html('Fortaleza de la Contraseña debil!');  
	 }  
	 if($('#pass2').val()!=""){
		 $('#passequal').html('');		 
		 $('#pass2').attr('value', '');
	 }
	 return true;  
	 });
	
	$('#pass2').keyup(function(e) {  	
		 if ($(this).val() != $('#pass').val() ) {  
			 $('#passequal').html('Las contraseñas no coinciden!'); 
			 $('#passequal').attr('class', 'error_pass');
		 } else{ 
			 $('#passequal').attr('class', 'ok_pass');
			 $('#passequal').html('Contraseña iguales!');  
		 }  
		 return true;  
	});
	
	/*******************************************************
	 * 
	 * Validación de formulario de Modificar clave
	 * 
	 ******************************************************/
	$("#modificar_clave").click(function (e){		  
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		var error = false;
		e.preventDefault();
        if( $("#clave_actual").val() == "" ){
        	$('#passrequired').html('Debe proporcionar la clave actual'); 
			$('#passrequired').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#passrequired').html('');
        } 
        
        if ($('#pass').val() == "") {  
			 $('#passstrength').html('La nueva contraseña debe tener 6 caracteres como mínimo'); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
       }else if (false == enoughRegex.test($('#pass').val())) {  
			 $('#passstrength').html('La nueva contraseña debe tener 6 caracteres como mínimo'); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
        }else{
        	$('#passstrength').html('');
        }
        
        if ($('#pass2').val() == "") {  
			 $('#passequal').html('Debe llenar este campo!'); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
       }else if ($('#pass').val() != $('#pass2').val()) {  
			 $('#passequal').html('Las contraseñas no coinciden!'); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
        }else{
        	$('#passequal').html('');
        }
        
        if(error == false){        	 
        	document.modificarClave.submit(); 
        }
    });
	/*******************************************************************
	 * Validación de formulario de Modificar datos del usuario
	 *******************************************************************/
	$("#modificar_datos").click(function (e){
		var error = false;
		e.preventDefault();
        
		if( $("#nombre").val() == "" ){
        	$('#nombre_required').html('Debe escribir su nombre completo'); 
			$('#nombre_required').attr('class', 'error_pass');
            error = true;
        }else if($("#nombre").val().length < 4){
        	$('#nombre_required').html('Su nombre debe poseer al menos 4 caracteres'); 
			$('#nombre_required').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#nombre_required').html('');
        }
        
        if( $("#apellido").val() == "" ){
        	$('#apellido_required').html('Debe escribir su apellido completo'); 
			$('#apellido_required').attr('class', 'error_pass');
            error = true;
        }else if($("#apellido").val().length < 4){
        	$('#apellido_required').html('Su apellido debe poseer al menos 4 caracteres'); 
			$('#apellido_required').attr('class', 'error_pass');
            error = true;
        }else{
        	$('#apellido_required').html('');
        }
        
        if( $("#cedula").val() == "" ){
        	$('#cedula_required').html('Debe escribir su número de cédula.'); 
			$('#cedula_required').attr('class', 'error_pass');
            error = true;
        }else if($.isNumeric($("#cedula").val()) == false){
        	$('#cedula_required').html('Su cédula debe poseer solo números'); 
			$('#cedula_required').attr('class', 'error_pass');
            error = true;
        }else if($.isNumeric($("#cedula").val()) == true){
        	if($("#cedula").val() < 0){
        		$('#cedula_required').html('Su número de cédula no es válido'); 
    			$('#cedula_required').attr('class', 'error_pass');
                error = true;
        	}else if($("#cedula").val().length < 4 || $("#cedula").val().length > 9){
        	$('#cedula_required').html('Su cédula debe poseer un mínimo 4 digitos y un máximo de 9 digitos'); 
			$('#cedula_required').attr('class', 'error_pass');
            error = true;
        	}
        } else{
        	$('#cedula_required').html('');
        }
        
        if(error == false){        	 
        	document.modificarDatos.submit(); 
        }
		
	});
	
	
	
	/*******************************************************
	 * 
	 * Validación de formulario de cambiar clave
	 * 
	 ******************************************************/
	$("#cambiar_clave").click(function (e){		  
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		var error = false;
		e.preventDefault();         
        
        if ($('#pass').val() == "") {  
			 $('#passstrength').html('La nueva contraseña debe tener 6 caracteres como mínimo'); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
       }else if (false == enoughRegex.test($('#pass').val())) {  
			 $('#passstrength').html('La nueva contraseña debe tener 6 caracteres como mínimo'); 
			 $('#passstrength').attr('class', 'error_pass');
			 error = true;			
        }else{
        	$('#passstrength').html('');
        }
        
        if ($('#pass2').val() == "") {  
			 $('#passequal').html('Debe llenar este campo!'); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
       }else if ($('#pass').val() != $('#pass2').val()) {  
			 $('#passequal').html('Las contraseñas no coinciden!'); 
			 $('#passequal').attr('class', 'error_pass');
			 error = true;
        }else{
        	$('#passequal').html('');
        }
        
        if(error == false){        	 
        	document.modificarClave.submit(); 
        }
    });
});