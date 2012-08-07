/*
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para eliminar un dato de entrada de un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del dato de entrada a eliminar. 
 */
function eliminar_Entrada(i,nombre){
	var action = 'id_elim_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm('Está a punto de eliminar el dato de entrada: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info('El dato de entrada: <br><h3>'+nombre+'</h3> <br>Ha sido Eliminado', {
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