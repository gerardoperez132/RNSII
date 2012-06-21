$.validator.addMethod('regexTitle', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras y espacios');

$.validator.addMethod('regexDescription', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras, números y puntos');

        
 $(document).ready(function() {
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
		    	'servicio.nombre': {reqired:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
		    	'servicio.descripcion': {reqired:"Debe introducir una descripción",regexDescription:'Sólo puede introducir letras, números y puntos'},
		    	'area':"Debe seleccionar las áreas a las que está orientado el servicio de información",
		    	'estado':"Debe seleccionar el estado actual del servicio"
		    }
		  });
});