$(document).ready(function(){	
	/*
	 * LLenando variable que lee el número de servicios incompletos
	 */
	var n = $('div.n').html();
	
	/*
	 * Creación del número de capas necesarias para la visualización de los sexys tool tips
	 */
	for(var i=0;i<=n;i++){
		$("#h"+i).tooltip (""+$('div.h'+i).html(), {
		      width: 300,
		      style: 'alert',		      
		      hook: 1
	    });
	}
});

/*
 * Ejecución de los sexys tool tips, en el que se lee la capa oculta en el html
 * donde se encuentrán los items incompletos del servicio de información.
 * 
 * elem = objeto html img para uso del tool tip
 * 
 * $('div.'+elem.name).html() = Lee una capa oculta con los detalles del servicio incompleto
 * 
 * $("#"+elem.name).tooltip = es el identificado de la imagen la cual mostrarará los detalles 
 * del servicio incompleto.
 */
function tip(elem){	
	$("#"+elem.name).tooltip (""+$('div.'+elem.name).html(), {
	      width: 250,
	      style: 'alert',		      
	      hook: 1
    });
}

/*
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para publicar un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del servicio de información a publicar 
 */
function publicar_SI(i,nombre){
	var action = 'id_pub_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm('Está a punto de publicar el servicio de información: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info('El Servicio: <h3>'+nombre+'</h3> ha sido publicado', {
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

/*
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para despublicar un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del servicio de información a despublicar 
 */
function despublicar_SI(i,nombre){
	var action = 'id_despub_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm('Está a punto de despublicar el servicio de información: <br>'+
			'<br><h3>'+nombre+'</h3> <br>¿Desea Continuar?', {
	  onComplete:
	    function(returnvalue) {
	      if (returnvalue) {
	    	  Sexy.info('El Servicio: <br><h3>'+nombre+'</h3> <br>Ha sido despublicado', {
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

/*
 * Función que muestra unos cuadro de dialogos hecho con el plugin sexy alert
 * que sirven para eliminar un servicio de información.
 * 
 * parametros:
 * 		i = Objeto html del formulario
 * 		nombre = nombre del servicio de información a despublicar 
 */
function eliminar_SI(i,nombre){
	var action = 'id_'+i;	
	var formulario = document.getElementById(action);	
	Sexy.confirm('Está a punto de eliminar el servicio de información: <br>'+
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