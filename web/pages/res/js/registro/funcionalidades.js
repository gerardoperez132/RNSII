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
	
});

/* 
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para eliminar un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del servicio de información a despublicar 
 */
function eliminar_Fun(i,nombre){
	var action = 'id_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm(data['mensajes']['eliminar_funcionalidad']+'<br>'+
			'<br><h3>'+nombre+'</h3> <br>'+data['mensajes']['desea_continuar'], {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info(data['mensajes']['el_servicio']+'<br><h3>'+nombre+'</h3> <br>'
	    			  +data['mensajes']['ha_sido_eliminado'], {
	    		  onComplete:
	    			  function(returnvalue) {
	    			  formulario.submit();
	    		  }
    		  });
	    		  
	      } else {
	    	  Sexy.alert(data['mensajes']['accion_cancelada']);
	      }
	    }
	  });			
};

/* 
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para abandonar la carga de datos para un servicio de información.
 * 
 * parametros:
 * 		nombre = nombre del servicio de información 
 */
function salir(nombre){	
	var formulario = document.getElementById('salir');	
	Sexy.confirm(data['mensajes']['abandonar_carga_datos_si']+' <br>'+
			'<br><h3>'+nombre+'</h3> <br>'+data['mensajes']['desea_continuar'], {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {	    	  
	    	  formulario.submit();
	      } else {
	    	  Sexy.alert(data['mensajes']['accion_cancelada']);
	      }
	    }
	  });			
};