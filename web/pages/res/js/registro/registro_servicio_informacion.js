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
	 * Creación de método para validar números,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexVersion', function (value) {		    	
		return $.isNumeric(value);
	});
	
	/*
	 * Validaciones para el formulario del tab1
	 */
	$("#formSI").validate({
		  errorPlacement: function (error, element) { 
		        error.insertBefore(element);    
		     },
		     rules: {
		    	'sector' : {min: 0 },
		    	'servicio.nombre': {required: true, regexTitle: true},
		    	'servicio.descripcion': {required: true, regexDescription: true},
		    	'area': { required: true, minlength: 1 },
		    	'estado':{min: 0 }
		    },
		    messages: {
		    	'sector': data['errores']['error.servicio.sector'],
		    	'servicio.nombre': {required:data['errores']['error.servicio.nombre'],regexTitle:data['errores']['error.regex.title']},
		    	'servicio.descripcion': {required:data['errores']['error.servicio.descripcion'],regexDescription:data['errores']['error.regex.description']},
		    	'area':data['errores']['error.servicio.area'],
		    	'estado':data['errores']['error.servicio.estado']
		    }
	});

	/*
	 * Validaciones para el formulario del tab2
	 */
	$("#formSI_Tab2").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	    	
	    	'name': {required: true, regexTitle: true},
	    	'file': {required: true, accept: "pdf"}	    	
	    },
	    messages: {	    	
	    	'name': {required:data['errores']['error.servicio.file.name'],regexTitle:data['errores']['error.servicio.title']},
	    	'file': {reqired:data['errores']['error.servicio.file'],accept:data['errores']['error.servicio.format']}	    	
	    }
	});
	
	/*
	 * Validaciones para el formulario del tab3
	 */
	$("#formSI_Tab3").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	
	    	 'seguridad' : {min: 0 },
	    	 'arquitectura': { required: true, minlength: 1 },
	    	 'servicio.version':{required: true,regexVersion: true,min: 0 ,max: 1000 },
	    	 'intercambio': {min: 0 },
	    	 'wsdl':{url:true}
	    },
	    messages: {	    	
	    	'seguridad' : data['errores']['error.servicio.seguridad'],
	    	 'arquitectura': data['errores']['error.servicio.arquitectura'],
	    	 'servicio.version':{
	    		 required: data['errores']['error.servicio.version'],
	    		 regexVersion: data['errores']['error.servicio.version.format'],
	    		 min:data['errores']['error.servicio.nombre.version.range'],
	    		 max:data['errores']['error.servicio.version.range']},
	    	 'intercambio': {min: data['errores']['error.servicio.intercambio']},
	    	 'wsdl': data['errores']['error.regex.url']
	    }
	});
		
	/*
	 * Validaciones para el formulario del tab4
	 */
	$("#formSI_Tab4").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	
	    	 'servicio.responsable' : {required: true, regexTitle: true},
	    	 'telefono': { required: true, digits: true },
	    	 'correo':{required: true,email: true }
	    },
	    messages: {	    	
	    	'servicio.responsable' : {
	    		required:data['errores']['error.servicio.responsable'],
	    		regexTitle:data['errores']['error.servicio.title']},
	    	 'telefono': {
	    		required:data['errores']['error.servicio.telefono'],
	    		digits:data['errores']['error.servicio.telefono.regex']},	    	 
	    	 'correo': {
	    		required:data['errores']['error.servicio.correo'],
	    		email:data['errores']['error.regex.email']}
	    }
	});
});