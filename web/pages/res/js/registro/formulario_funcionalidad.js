$.validator.addMethod('regexTitle', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras y espacios');

$.validator.addMethod('regexDescription', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras, números y puntos');

$(document).ready(function() {
	/********************************************************************
	 * Validaciones para el formulario creación/modificación de una 
	 * funcionalidad. funcionalidad.nombre funcionalidad.descripcion
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
	    	'servicio.nombre': {required:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
	    	'servicio.descripcion': {required:"Debe introducir una descripción",regexDescription:'Sólo puede introducir letras, números y puntos'},	    	
	    }
  });  
	
});