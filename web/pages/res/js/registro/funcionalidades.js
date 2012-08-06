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
	Sexy.confirm('Está a punto de eliminar la Funcionalidad: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info('El Servicio: <br><h3>'+nombre+'</h3> <br>Ha sido Eliminado', {
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

function salir(nombre){	
	var formulario = document.getElementById('salir');	
	Sexy.confirm('Está abandonar la carga de datos para este servicio de información: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {	    	  
	    	  formulario.submit();
	      } else {
	    	  Sexy.alert('Acción Cancelada');
	      }
	    }
	  });			
};