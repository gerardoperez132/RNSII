/*
 * Variable con las claves de intercionalización del archivo json. 
 */
var data;

var isLongitud;
var isFormato;

$(document)
		.ready(
				function() {

					/*
					 * Ocultando botones submit del formulario y mostrando los
					 * botones que están en la descripción
					 */
					$("#btn_guardar_entrada").hide();
					$("#btn_regresar").hide();
					$("#sub_regresar").show();
					$("#sub_guardar_entrada").show();

					/*
					 * Botones que envian los formularios
					 */
					$("#sub_regresar").click(function() {
						document.f_regresar.submit();
					});
					$("#sub_guardar_entrada").click(function() {
						document.formES.submit();
					});

					/*
					 * Obteniendo los valores de intercionalización del archivo
					 * JSON
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

					/*
					 * Creación de método para validar una expresión regular del
					 * tipo title, usada por el plugin jquery validator
					 */
					$.validator.addMethod('regexTitle', function(value) {
						var valor = value;
						var soloTexto = new RegExp(
								data['constants']['REGEX_TITLE']);
						return soloTexto.test(valor.toUpperCase());
					});

					/*
					 * Creación de método para validar una expresión regular del
					 * tipo description, usada por el plugin jquery validator
					 */
					$.validator.addMethod('regexDescription', function(value) {
						var valor = value;
						var soloTexto = new RegExp(
								data['constants']['REGEX_DESCRIPTION']);
						return soloTexto.test(valor.toUpperCase());
					});

					/*
					 * Creación de método para validar si el usuario ha escogido
					 * un formato, usada por el plugin jquery validator
					 */
					$.validator.addMethod('formatoValidate', function(value) {
						if ($('#capa_formato').data("formato")) {
							if ($("#entrada\\.id_formato").val() == -1) {
								return false;
							} else {
								return true;
							}
						} else {
							return true;
						}
					});

					/*
					 * Creación de método para validar si el usuario ha
					 * introduccido una longitud, usada por el plugin jquery
					 * validator
					 */
					$.validator.addMethod('longitudValidate', function(value) {
						if ($('#capa_longitud').data("longitud")) {
							if ($("#entrada\\.longitud").val().length < 1) {
								return false;
							} else {
								return true;
							}
						} else {
							return true;
						}
					});

					/*
					 * Validaciones para el formulario creación/modificación de
					 * una Entrada/Salida.
					 */
					$("#formES")
							.validate(
									{
										errorPlacement : function(error,
												element) {
											error.insertBefore(element);
										},
										rules : {
											'entrada.nombre' : {
												required : true,
												regexTitle : true
											},
											'entrada.descripcion' : {
												required : true,
												regexDescription : true
											},
											'entrada.id_tipo_dato' : {
												min : 0
											},
											'entrada.id_formato' : {
												formatoValidate : true
											},
											'entrada.longitud' : {
												longitudValidate : true
											}
										},
										messages : {
											'entrada.nombre' : {
												required : data['errores']['error.entrada.nombre'],
												regexTitle : data['errores']['error.regex.title']
											},
											'entrada.descripcion' : {
												required : data['errores']['error.entrada.descripcion'],
												regexDescription : data['errores']['error.regex.description']
											},
											'entrada.id_tipo_dato' : data['errores']['error.entrada.tipodato'],
											'entrada.id_formato' : data['errores']['error.entrada.format'],
											'entrada.longitud' : data['errores']['error.entrada.longitud'],
										}
									});

					var select = $("#entrada.id_formato").val();
					var i;
					for (i = 2; i <= 7; i++) {
						$('#opt_group_' + i).remove();
					}

					/*
					 * Oculta las capas formato y longitud al cargar el DOM para
					 * esperar la elección del tipo de dato y mostrarle que
					 * introdusca el formato y/o la longitud dependiendo de la
					 * naturaleza del dato primitivo.
					 */
					limpiar_options();
					fomato_longitud_visible();

					if (select > 0) {
						$('#opt_element_' + select).attr("selected", true);
					}
					select = -1;

					$("#entrada\\.id_tipo_dato").change(function(e) {
						limpiar_longitud_formato();
						fomato_longitud_visible();
					});

					/*
					 * Valida que solo metan números.
					 */
					$('#entrada\\.longitud').keyup(
							function(e) {
								if ($("#entrada\\.id_tipo_dato").val() == 4) {// decimales
									if ($.isNumeric($("#entrada\\.longitud")
											.val()) == false) {
										this.value = this.value.substring(0,
												(this.value.length - 1));
										$('#longitud_msj').html(
												data['errores']['error.num']);
										$('#longitud_msj').attr('class',
												'error_pass');
									} else {
										$('#longitud_msj').html('');
									}
								} else { // Naturales
									if (!/^([0-9])*$/.test($(
											"#entrada\\.longitud").val())) {
										this.value = this.value.substring(0,
												(this.value.length - 1));
										$('#longitud_msj').html(
												data['errores']['error.num']);
										$('#longitud_msj').attr('class',
												'error_pass');
									} else {
										$('#longitud_msj').html('');
									}
								}
							});

					function limpiar_options() {
						i = 1;
						for (i = 1; i <= 13; i++) {
							$('#opt_element_' + i).remove();
						}
					}

					function limpiar_longitud_formato() {
						$("#entrada\\.longitud").val('');
						$("#entrada\\.id_formato").val('-1');
						limpiar_options();
					}

					function fomato_longitud_visible() {

						/*
						 * Oculta las capas formato y longitud si el usuario no
						 * selecciona ningún tipo de dato.
						 */
						if ($("#entrada\\.id_tipo_dato").val() == -1) {
							$('#capa_formato').attr('style',
									'visibility: hidden; position:fixed;');
							$('#capa_longitud').attr('style',
									'visibility: hidden; position:fixed;');
						} else {
							// Muestra la capa longitud si el dato posse esta
							// caracteristica
							$
									.ajax({
										type : 'GET',
										url : 'dato_haslength.action',
										cache : false,
										async : false,
										data : {
											id_tipo_dato : $(
													"#entrada\\.id_tipo_dato")
													.val()
										},
										success : function(result) {
											var boleano = new String(''
													+ result);
											if (boleano.toLowerCase().indexOf(
													'l=true') != -1) {
												$('#capa_longitud')
														.attr('style',
																'visibility: visible; position:relative;')
														.data("longitud", true);
											} else {
												$('#capa_longitud')
														.attr('style',
																'visibility: hidden; position:fixed;')
														.data("longitud", false);
											}
										}
									});
							// Muestra la capa formato si el dato posse esta
							// caracteristica
							$
									.ajax({
										type : 'GET',
										url : 'dato_hasformatted.action',
										cache : false,
										async : false,
										data : {
											id_tipo_dato : $(
													"#entrada\\.id_tipo_dato")
													.val()
										},
										success : function(result2) {
											var boleano = new String(''
													+ result2);
											if (boleano.toLowerCase().indexOf(
													'f=true') != -1) {
												$('#capa_formato')
														.attr('style',
																'visibility: visible; position:relative;')
														.data("formato", true);
												// Crea las opciones del select
												// formato
												$
														.ajax({
															type : 'GET',
															url : 'list_format.action',
															cache : false,
															async : false,
															data : {
																id_tipo_dato : $(
																		"#entrada\\.id_tipo_dato")
																		.val()
															},
															success : function(
																	result3) {
																$(
																		"#entrada\\.id_formato")
																		.append(
																				result3);
																$(
																		'#opt_element_'
																				+ $(
																						"#entrada\\.id_formato")
																						.attr(
																								'class')
																				+ '')
																		.attr(
																				'selected',
																				'selected');
															}
														});
											} else {
												$('#capa_formato')
														.attr('style',
																'visibility: hidden; position:fixed;')
														.data("formato", false);
											}
										}
									});
						}

					}

					/*
					 * funciones para el correcto funcionamiento de links de la
					 * vista de gobierno en linea
					 */
					$(".masPie").hide();

					$(".cerrarPie").toggle(function() {
						$(".masPie").slideDown();
						$(".cerrarPie").text("-");
						return false;
					}, function() {
						$(".masPie").slideUp();
						$(".cerrarPie").text("+");
						return false;
					});

					$('.m_a').lksMenu();

					$(".m_a ul li ul").attr("style", "display: none");
					$(".m_a ul li ul li ul").attr("style", "display: none");
				});

/*
 * funciones para el correcto funcionamiento de links de la vista de gobierno en
 * linea
 */
function changeValues(page, campoId1, valor1, campoId2, valor2, form) {
	var campo1 = document.getElementById(campoId1);
	campo1.value = valor1;
	var campo2 = document.getElementById(campoId2);
	campo2.value = valor2;
	submitForm(page, form);
}
function changeValue(page, campoId, valor, form) {
	var campo = document.getElementById(campoId);
	campo.value = valor;
	submitForm(page, form);
}
function submitForm(page, form) {
	var form = document.getElementById(form);
	form.action = "http://gobiernoenlinea.gob.ve/home/" + page;
	form.submit();
}
function actualizarClima(cadena) {
	var T = cadena.split(",");
	$('#t_max').html('' + T[0]);
	$('#t_min').html('' + T[1]);
}

(function($) {
	// fn es un shortcut al prototipo (prototypo) de la libreria jquery
	// declarando un metodo dentro de esta librería la extendemos para ser usada
	// con cualquier selector
	$.fn.lksMenu = function() {
		// para mantener la posibilidad de concatenar metodos es que retornamos
		// la función en
		// lugar de solo ejecutar algo y ya.
		// en este caso usamos un each, porque el selector sobre el que
		// aplicamos la funcion
		// podria contener mas de un elemento , esto es , aplicaria
		// $('.menu').menu() lo cual
		// ejecutaria el codigo para todos los elementos que tengan la clase
		// menu, como puede
		// haber mas de uno es que ejecutamos el each para que corra sobre todos
		// ellos
		return this
				.each(function() {
					// obtenemos el elemento que se esta analizando en esta
					// vuelta del each
					var menu = $(this);
					// localizamos los links principales y le asignamos un
					// evento click
					menu
							.find('ul li > a')
							.bind(
									'click',
									function(event) {
										// identificamos el elemento sobre el
										// que se hizo click
										var currentlink = $(event.currentTarget);
										// los ul que tengan la clase active
										// seran los que estan abiertos
										// si el link sobre que presionamos ya
										// estaba abierto , es decir tenia
										// la clase active, lo cerramos y nada
										// mas
										if (currentlink.parent().find(
												'ul.active').size() == 1) {
											// cerramos el link porque apretamos
											// sobre el mismo abierto
											currentlink
													.parent()
													.find('ul.active')
													.slideUp(
															'medium',
															function() {
																// le quitamos
																// la clase
																currentlink
																		.parent()
																		.find(
																				'ul.active')
																		.removeClass(
																				'active');
															});
										}
										// si ningun link estaba apretado
										else if (menu.find('ul li ul.active')
												.size() == 0) {
											// no hace falta cerrar ninguno y
											// solo abrimos el elemento al
											// cual se le hizo click
											show(currentlink);
										} else {
											// si ya habia un item abierto ,
											// simplemente lo localizamos
											// con find, y lo cerramos con
											// slideup,
											menu
													.find('ul li ul.active')
													.slideUp(
															'medium',
															function() {
																// una vez que
																// cerramos el
																// que estaba
																// abierto
																// le quitamos
																// la clase
																// active
																menu
																		.find(
																				'ul li ul')
																		.removeClass(
																				'active');
																// abrimos uno
																// nuevo que
																// corresponde
																// al que se
																// clickeo
																show(currentlink);
															});
										}
									});

					menu
							.find('ul li > a')
							.bind(
									'click',
									function(event) {

										var currentlink = $(event.currentTarget);

										if (currentlink.parent().find(
												'span.active').size() == 1) {
											currentlink.parent().find(
													'span.active').html("+");
											currentlink.parent().find(
													'span.active').removeClass(
													'active');
										} else if (menu.find('span.active')
												.size() == 0) {
											show2(currentlink);
										} else {
											menu.find('span').html("+");
											menu.find('span').removeClass(
													'active');
											show2(currentlink);
										}
									});

					// esta funcion es de soporte
					// todo lo que hace es abrir el elemento y asignarle la
					// clase active
					function show(currentlink) {
						currentlink.parent().find('ul').addClass('active');
						currentlink.parent().find('ul').slideDown('medium');
					}

					function show2(currentlink) {
						currentlink.parent().find('span').addClass('active');
						currentlink.parent().find('span.active').html("-");
					}
				});
	};
	// esto es lo que deciamos al principio , ejecutamos la funcion por eso los
	// parentesis y le pasamos por parametro la libreria jQuery.
})(jQuery);
