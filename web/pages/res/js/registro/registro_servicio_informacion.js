$.validator.addMethod('regexTitle', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras y espacios');

$.validator.addMethod('regexDescription', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras, números y puntos');

$.validator.addMethod('regexVersion', function (value) {		    	
	return $.isNumeric(value);
}, 'Sólo puede introducir letras, números y puntos');

$(document).ready(function() {
	/********************************************************************
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
	    	'sector': "Debe seleccionar un sector",
	    	'servicio.nombre': {required:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
	    	'servicio.descripcion': {required:"Debe introducir una descripción",regexDescription:'Sólo puede introducir letras, números y puntos'},
	    	'area':"Debe seleccionar las áreas a las que está orientado el servicio de información",
	    	'estado':"Debe seleccionar el estado actual del servicio"
	    }
  });
  
	/********************************************************************
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
	    	'name': {required:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
	    	'file': {reqired:"Debe adjuntar un archivo",accept:'Sólo se permiten archivos en formato PDF'}	    	
	    }
	});
	
	/********************************************************************
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
	    	 'intercambio': {min: 0 }	    	
	    },
	    messages: {	    	
	    	'seguridad' : "Debe seleccionar un nivel de seguridad",
	    	 'arquitectura': "Debe seleccionar un tipo de arquitectura",
	    	 'servicio.version':{
	    		 required: "Debe introducir la versión",
	    		 regexVersion: "La versión sólo debe tener números en un formato XXX.XXX",
	    		 min:"El número de versión está fuera del rango, el formato es XXX.XXX",
	    		 max:"El número de versión está fuera del rango, el formato es XXX.XXX"},
	    	 'intercambio': {min: "Debe seleccionar un tipo de intercambio"}	    	
	    }
	});
	
	/********************************************************************
	 * Validaciones para el formulario del tab4
	 * servicio.responsable telefono correo
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
	    		required:"Debe introducir el responsable del servicio de información",
	    		regexTitle:'Sólo puede introducir letras y espacios'},
	    	 'telefono': {
	    		required:"Debe introducir un número de teléfono",
	    		digits:'El teléfono sólo puede estar conformado por números.'},	    	 
	    	 'correo': {
	    		required:"Debe introducir una dirección de correo electrónico",
	    		email:'Esta dirección de correo electrónico es inválida'}
	    }
	});
});