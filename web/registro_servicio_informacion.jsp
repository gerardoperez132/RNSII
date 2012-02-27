<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>
<script type="text/javascript">
	var objeto2;

	function soloDinero(objeto, e) {

		var keynum

		var keychar

		var numcheck

		if (window.event) { /*/ IE*/

			keynum = e.keyCode

		}

		else if (e.which) { /*/ Netscape/Firefox/Opera/*/

			keynum = e.which

		}

		if ((keynum >= 35 && keynum <= 37) || keynum == 8 || keynum == 9
				|| keynum == 46 || keynum == 39) {

			return true;

		}

		if (keynum == 190 || keynum == 110 || (keynum >= 95 && keynum <= 105)
				|| (keynum >= 48 && keynum <= 57)) {

			posicion = objeto.value.indexOf('.');

			if (posicion == -1) {

				return true;

			} else {

				if (!(keynum == 190 || keynum == 110)) {

					objeto2 = objeto;

					t = setTimeout('dosDecimales()', 150);

					return true;

				} else {

					objeto2 = null;

					return false;

				}

			}

		} else {

			return false;

		}

	}

	function dosDecimales() {

		var objeto = objeto2;

		var posicion = objeto.value.indexOf('.');

		var decimal = 3;

		if (objeto.value.length - posicion < decimal) {

			objeto.value = objeto.value.substr(0, objeto.value.length - 1);
			

		} else {

			objeto.value = objeto.value.substr(0, posicion + decimal + 1);

		}

		return;
	}
</SCRIPT>

<title>SRSI - Inicio</title>
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
</head>
<body>

	<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la página -->
		<div id="container">
			<!-- Este es el div de la cabecera -->
			<div id="header">
				<img src="res/img/header.png" width="880" height="70"
					alt="Cintillo Gobierno Bolivariano" /> <img src="res/img/mio.png"
					width="874" height="116" alt="Marco de Interoperabilidad"
					style="border: 3px solid #57cedc;" />
			</div>

			<!-- Este es el div de los menus -->
			<div id="menu"></div>

			<!-- Esta es la barra lateral -->
			<div id="sidebar">

				<small>Paso 1 Registro de Servicio de Información</small><br> <br>
				<small>Paso 2 Registro de Funcionalidad(es)</small> <br> <br>
				<small>Paso 3 Registro de Entradas/Salidas</small><br> <br>
				<small>Paso 4 Verificar y guardar</small>


			</div>



			<!-- Este es el div de contenidos -->
			<div id="content">

				<h3>Registro de Servicio de Información</h3>

				<hr>

				<ul class="tabs">
					<li><a href="#tab1">Descripción General</a></li>
					<li><a href="#tab2">Aspectos legales</a></li>
					<li><a href="#tab3">Descripción técnica</a></li>
					<li><a href="#tab4">Descripción de Soporte</a></li>
				</ul>

				<form action="registrarServicioInformacion" method="post"
					enctype="multipart/form-data">

					<div class="tab_container">
						<div id="tab1" class="tab_content">

							<p>Descripción General del Servicio</p>
							<hr>
							
							<h5 class="formulario">Sector:</h5>
							<s:fielderror>
								<s:param>sector</s:param>
							</s:fielderror>
							<s:select name="sector" list="sectores" listKey="id_sector"
								listValue="nombre" headerKey="-1"
								headerValue="Seleccione un sector"></s:select>


							<h5 class="formulario">Nombre:</h5>
							<s:fielderror>
								<s:param>nombre</s:param>
							</s:fielderror>
							<s:textfield name="nombre" />



							<h5 class="formulario">Descripción:</h5>
							<s:fielderror>
								<s:param>descripcion</s:param>
							</s:fielderror>
							<s:textarea name="descripcion" cols="40" rows="10" />



							<h5 class="formulario">Estado del Servicio:</h5>
							<s:fielderror>
								<s:param>estado</s:param>
							</s:fielderror>
							<s:select name="estado" list="estados" listKey="id_estado"
								listValue="nombre" headerKey="-1" headerValue="Seleccione"></s:select>


						</div>

						<div id="tab2" class="tab_content">
							<p class="formulario">Aspectos legales que rigen al servicio</p>
							<small>Incluir documento de acuerdo de nivel de
								servicio(SLA) por el qué se regirá este Servicio de Información.</small>
							<hr>

							<h5 class="formulario">Nombre del Documento:</h5>
							<s:fielderror>
								<s:param>aspectoLegal</s:param>
							</s:fielderror>
							<s:textfield name="aspectoLegal" labelposition="top" />


							<h5 class="formulario">Adjuntar SLA:</h5>
							<s:fielderror>
								<s:param>documento</s:param>
							</s:fielderror>
							<s:file name="archivo"></s:file>

						</div>

						<div id="tab3" class="tab_content">

							<p class="formulario">Descripción técnica del servicio</p>
							<small>Especificaciones del intercambio de Información</small>
							<hr>

							<h5 class="formulario">Orientado a:</h5>
							<s:fielderror>
								<s:param>area</s:param>
							</s:fielderror>
							<s:checkboxlist list="areas" listKey="id_area" listValue="nombre"
								name="area" value="area" />

							<h5 class="formulario">Seguridad:</h5>
							<s:fielderror>
								<s:param>seguridad</s:param>
							</s:fielderror>
							<s:select list="l_seguridad" listKey="id_seguridad"
								listValue="nombre" headerKey="-1" headerValue="Seleccione"
								name="seguridad"></s:select>

							<h5 class="formulario">Arquitectura:</h5>
							<s:fielderror>
								<s:param>arquitectura</s:param>
							</s:fielderror>
							<s:checkboxlist list="arquitecturas" listKey="id_arquitectura"
								listValue="nombre" name="arquitectura" />

							<br>
							<h5 class="formulario">Versión:</h5>
							<s:fielderror>
								<s:param>version</s:param>
							</s:fielderror>
							<s:textfield name="version" onkeydown="return soloDinero(this, event);" />			


							<br>

							<h5 class="formulario">Tipo de Intercambio:</h5>
							<s:fielderror>
								<s:param>intercambio</s:param>
							</s:fielderror>
							<select name="intercambio">
								<optgroup label="">
									<option value="-1">Seleccione</option>
								</optgroup>
								<s:iterator value="intercambiosPadres">

									<s:set name="padre" value="id_intercambio"></s:set>
									<s:set name="nombrePadre" value="nombre"></s:set>
									<optgroup label="<s:property value="nombre"/>">

										<s:iterator value="intercambiosHijos">
											<s:if test="%{#padre == id_padre}">

												<option value="<s:property value="id_intercambio"/>"
													<s:if test="intercambio == id_intercambio"> selected="selected"</s:if>>
													<s:property value="nombre" />
												</option>

											</s:if>
										</s:iterator>
									</optgroup>
								</s:iterator>
							</select>


						</div>

						<div id="tab4" class="tab_content">

							<h5 class="formulario">
								Responsable del Servicio:
								<s:property value="responsable" />
							</h5>
							<s:hidden name="responsable"></s:hidden>
							<hr>

							<h4>Soporte Técnico</h4>
							<h5 class="formulario">Teléfono de Contacto:</h5>
							
							<h6 class="formulario">Código de Área:</h6>
							<s:fielderror>
								<s:param>codigoArea</s:param>
							</s:fielderror>							
							<s:textfield name="codigoArea" labelposition="top"
								size="4" maxlength="3"
								onkeyup="var no_digito = /\D/g; this.value = this.value.replace(no_digito , '');" />
							
							<h6 class="formulario">Número de Teléfono:</h6>
							<s:fielderror>
								<s:param>telefonoContacto</s:param>
							</s:fielderror>						
							<s:textfield name="telefonoContacto" labelposition="top"
								maxlength="7"
								onkeyup="var no_digito = /\D/g; this.value = this.value.replace(no_digito , '');" />

							<h5 class="formulario">Correo de Contacto:</h5>
							<s:fielderror>
								<s:param>correoContacto</s:param>
							</s:fielderror>
							<s:textfield name="correoContacto"></s:textfield>
							<input type="submit" value="Registrar" />

						</div>




					</div>

				</form>


			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>