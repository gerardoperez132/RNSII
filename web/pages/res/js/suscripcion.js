$(document).ready(
		function() {
			/*******************************************************************
			 * 
			 * Validación de formulario de Modificar clave
			 * 
			 ******************************************************************/
			$("#enviar_solicitud").click(
					function(e) {
						/* e.preventDefault(); */
						$(document.createElement('span')).attr('class',
								'error_pass').html('Acerda de funciona');
					});
		});