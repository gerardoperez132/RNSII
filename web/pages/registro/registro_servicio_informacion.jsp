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


<title>SRSI - Inicio</title>
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
</head>
<body>
	<s:i18n name="ve/gob/cnti/srsi/i18n/registro_servicio_informacion">
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
					<div class="main_cont">
						<div class="menu_top_bg">Navegacion</div>
						<div class="sub_menu">
							<ul>
								<li><a class="selected">Paso 1</a></li>
								<li><a href="#">Paso 2</a></li>
								<li><a href="#">Paso 3 </a></li>
							</ul>
						</div>
					</div>
					<small>Paso 1 Registro de Servicio de Información</small><br>
					<br> <small>Paso 2 Registro de Funcionalidad(es)</small> <br>
					<br> <small>Paso 3 Registro de Entradas/Salidas</small><br>
					<br> <small>Paso 4 Verificar y guardar</small>
				</div>
				<!-- Este es el div de contenidos -->
				<div id="content">
					<h3>
						<s:text name="registro.title"></s:text>
					</h3>
					<hr>
					<ul class="tabs">
						<li><a href="#tab1"><s:text name="tab1.title"></s:text> </a>
						</li>
						<li><a href="#tab2"><s:text name="tab2.title"></s:text> </a>
						</li>
						<li><a href="#tab3"><s:text name="tab3.title"></s:text> </a>
						</li>
						<li><a href="#tab4"><s:text name="tab4.title"></s:text> </a>
						</li>
					</ul>
					<form action="registrarServicioInformacion" method="post"
						enctype="multipart/form-data">
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<p>Descripción General del Servicio ¿Hace falta esto?</p>
								<hr>
								<!-- <a class="tooltip"> Sector: <span class="custom help"><img
										src="res/img/Info.png" alt="Help" height="48" width="48" /> <em>ayuda</em>This
										is just an example of what you can do using a CSS tooltip,
										feel free to get creative and produce your own! </span> </a><br>-->
								<h5 class="formulario">
									<s:text name="sector.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>sector</s:param>
								</s:fielderror>
								<s:select name="sector" list="sectores" listKey="id_sector"
									listValue="nombre" headerKey="-1"
									headerValue="<s:text name="sector.title"></s:text>"></s:select>
								<h5 class="formulario">
									<s:text name="nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>nombre</s:param>
								</s:fielderror>
								<s:textfield name="nombre" />
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>descripcion</s:param>
								</s:fielderror>
								<s:textarea name="descripcion" cols="40" rows="10" />
								<h5 class="formulario">
									<s:text name="area.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>area</s:param>
								</s:fielderror>
								<s:checkboxlist list="areas" listKey="id_area"
									listValue="nombre" name="area" value="area" />
								<h5 class="formulario">
									<s:text name="estado.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>estado</s:param>
								</s:fielderror>
								<s:select name="estado" list="estados" listKey="id_estado"
									listValue="nombre" headerKey="-1" headerValue="Seleccione"></s:select>
							</div>
							<div id="tab2" class="tab_content">
								<p class="formulario">
									<s:text name="documento.title"></s:text>
								</p>
								<small><s:text name="documento.descripcion"></s:text> </small>
								<hr>
								<h5 class="formulario">Nombre del Documento Legal:</h5>
								<s:fielderror>
									<s:param>documentoLegalNombre</s:param>
								</s:fielderror>
								<s:textfield name="documentoLegalNombre" labelposition="top" />


								<h5 class="formulario">Adjuntar Documento Legal:</h5>
								<s:fielderror>
									<s:param>documentoLegal</s:param>
								</s:fielderror>
								<s:file name="documentoLegal" value="documentoLegalFileName"></s:file>

								<br> <br> <br> <br>

								<h5 class="formulario">Nombre del Documento SLA:</h5>
								<s:fielderror>
									<s:param>slaNombre</s:param>
								</s:fielderror>
								<s:textfield name="slaNombre" labelposition="top" />


								<h5 class="formulario">Adjuntar SLA:</h5>
								<s:fielderror>
									<s:param>sla</s:param>
								</s:fielderror>
								<s:file name="sla" value="slaFileName"></s:file>



							</div>

							<div id="tab3" class="tab_content">

								<p class="formulario">Descripción técnica del servicio</p>
								<small>Especificaciones del intercambio de Información</small>
								<hr>

								<h5 class="formulario">gagw</h5>
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
								<s:textfield name="version"
									onkeyup="var pattern = /[^0-9\.]/g;
								this.value = this.value.replace(pattern, '');"
									maxlength="7" />
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

								<h6 class="codTel">Código de Área:</h6>
								<s:fielderror>
									<s:param>codArea</s:param>
								</s:fielderror>
								<s:textfield name="codArea" labelposition="top" size="4"
									maxlength="3" cssClass="codTel"
									onkeyup="var no_digito = /\D/g; this.value = this.value.replace(no_digito , '');" />

								<h6 class="codTel">Número de Teléfono:</h6>
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
	</s:i18n>
</body>
</html>