$(document).ready(function(){
	/**Comprueba la fortaleza de las contraseñas mediante los siguientes criterios:
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
        	//$("#modificar_clave").submit(); 
        	document.modificarClave.submit(); 
        }
    });
	
});