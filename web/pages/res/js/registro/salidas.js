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
 * que sirven para eliminar un dato de salida de un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del dato de salida a eliminar. 
 */
function eliminar_Salida(i,nombre){
	var action = 'id_elim_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm(data['mensajes']['eliminar_salida']+'<br>'+
			'<br><h3>'+nombre+'</h3> <br>'+data['mensajes']['desea_continuar'], {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info(data['mensajes']['el_dato_salida']+'<br><h3>'+nombre+'</h3> <br>'
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