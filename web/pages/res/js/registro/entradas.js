/**
 * Variable con las claves de internacionalización del archivo JSON.
 */
var data;

$(document).ready(function() {
	/**
	 * Obteniendo los valores de internacionalización del archivo JSON
	 */
	$.ajax({
		url : "getJSONResult.action",
		type : "GET",
		dataType : "json",
		async : false,
		success : function(source) {
			data = source;
		}
	});
});

/**
 * Función que muestra unos cuadros de diálogo realizados con el plugin Sexy
 * Alert que sirven para eliminar una entrada.
 * 
 * @param i
 *            Objeto HTML del formulario.
 * @param nombre
 *            Nombre de la entrada a eliminar.
 */
function eliminar_Entrada(i, nombre) {
	var action = 'id_elim_' + i;
	var formulario = document.getElementById(action);
	Sexy.confirm(data['mensajes']['eliminar_entrada'] + '<br>' + '<br><h3>'
			+ nombre + '</h3> <br>' + data['mensajes']['desea_continuar'], {
		onComplete : function(returnvalue) {
			if (returnvalue) {
				Sexy.info(data['mensajes']['el_dato_entrada'] + '<br><h3>'
						+ nombre + '</h3> <br>'
						+ data['mensajes']['ha_sido_eliminado'], {
					onComplete : function(returnvalue) {
						formulario.submit();
					}
				});

			} else {
				Sexy.alert(data['mensajes']['accion_cancelada']);
			}
		}
	});
};
