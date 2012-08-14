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
	 * Creación de método para validar una expresión regular del tipo description,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexDescription', function (value) {
		var valor = value;
		var soloTexto = new RegExp(data['constants']['REGEX_DESCRIPTION']);		
		return soloTexto.test(valor.toUpperCase());
	});

	
	/*
	 * Validaciones para el formulario creación/modificación de una 
	 * funcionalidad. 
	 */
	  $("#formFunc").validate({
		  errorPlacement: function (error, element) { 
		        error.insertBefore(element);    
		     },
		     rules: {	    	
		    	'funcionalidad.nombre': {required: true, regexTitle: true},
		    	'funcionalidad.descripcion': {required: true, regexDescription: true}
		    },
		    messages: {	    	
		    	'funcionalidad.nombre': {required:data['errores']['error.funcionalidad.nombre'],regexTitle:data['errores']['error.regex.title']},
		    	'funcionalidad.descripcion': {required:data['errores']['error.funcionalidad.descripcion'],regexDescription:data['errores']['error.regex.description']},	    	
		    }
	  });  
	
});