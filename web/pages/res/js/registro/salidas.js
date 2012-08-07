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
	Sexy.confirm('Está a punto de eliminar el dato de salida: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info('El dato de salida: <br><h3>'+nombre+'</h3> <br>Ha sido Eliminado', {
	    		  onComplete:
	    			  function(returnvalue) {
	    			  formulario.submit();
	    		  }
    		  });
	    		  
	      } else {
	    	  Sexy.alert('Acción Cancelada');
	      }
	    }
	  });			
};