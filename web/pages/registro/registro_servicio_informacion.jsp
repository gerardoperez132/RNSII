<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/registro_servicio_informacion">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>
<title><s:text name="registro.title"></s:text>
</title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
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
								<p class="formulario">Descripción General del Servicio ¿Hace
									falta esto?</p>
								<small><s:text name="tab1.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="sector.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>sector</s:param>
								</s:fielderror>
								<s:select name="sector" list="sectores" listKey="id_sector"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('sector.select')}"></s:select>
								<h5 class="formulario">
									<s:text name="nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.nombre</s:param>
								</s:fielderror>
								<s:textfield name="servicio.nombre" />
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="servicio.descripcion" cols="40" rows="10" />
								<h5 class="formulario">
									<s:text name="area.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>area</s:param>
								</s:fielderror>
								<s:checkboxlist list="areas" listValue="nombre" name="area"
									listKey="id_area" />
								<h5 class="formulario">
									<s:text name="estado.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>estado</s:param>
								</s:fielderror>
								<s:select name="estado" list="estados" listKey="id_estado"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('estado.select')}"></s:select>
							</div>
							<!-- PROBAR A PARTIR DE AQUÍ... -->


							<!-- *************************** ARREGLAR ARCHIVOS. ******************** -->
							<div id="tab2" class="tab_content">
								<p class="formulario">Aspectos Legales (¿Hará falta esto?)</p>
								<small><s:text name="tab2.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="documento.name"></s:text>
								</h5>
								<s:fielderror>
									<s:param>name</s:param>
								</s:fielderror>
								<s:textfield name="name" labelposition="top" />
								<h5 class="formulario">
									<s:text name="documento.file"></s:text>
								</h5>
								<s:fielderror>
									<s:param>file</s:param>
								</s:fielderror>
								<s:file name="file" value="filename"></s:file>
								<br> <br> <br> <br>
							</div>
							<!-- *************************** ARREGLAR ARCHIVOS. ******************** -->

							<div id="tab3" class="tab_content">
								<p class="formulario">Descripción técnica del servicio ¿Hará
									falta esto?</p>
								<small><s:text name="tab3.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="seguridad.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>seguridad</s:param>
								</s:fielderror>
								<s:select list="niveles" listKey="id_seguridad"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('seguridad.select')}" name="seguridad"></s:select>
								<h5 class="formulario">
									<s:text name="arquitectura.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>arquitectura</s:param>
								</s:fielderror>
								<s:checkboxlist list="arquitecturas" listValue="nombre" name="arquitectura"
									listKey="id_arquitectura" />
								<br>
								<h5 class="formulario">
									<s:text name="version.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.version</s:param>
								</s:fielderror>
								<s:textfield name="servicio.version"
									onkeyup="var pattern = /[^0-9\.]/g;
								this.value = this.value.replace(pattern, '');"
									maxlength="7" />
								<br>
								<h5 class="formulario">
									<s:text name="intercambio.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>intercambio</s:param>
								</s:fielderror>
								<select name="intercambio">
									<optgroup label="">
										<option value="-1">
											<s:text name="intercambio.select"></s:text>
										</option>
									</optgroup>
									<s:iterator value="parents">
										<s:set name="padre" value="id_intercambio"></s:set>
										<s:set name="nombrePadre" value="nombre"></s:set>
										<optgroup label="<s:property value="nombre"/>">
											<s:iterator value="children">
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
								<p class="formulario">Soporte Técnico (¿Hará falta esto?)</p>
								<small><s:text name="tab4.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="responsable.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.responsable</s:param>
								</s:fielderror>
								<s:textfield name="servicio.responsable" labelposition="top" />
								<h5 class="formulario">
									<s:text name="telefono.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>telefono</s:param>
								</s:fielderror>
								<table>
									<tr>
										<td><s:select name="codigo" list="codigos" />
										</td>
										<td><s:textfield name="telefono" labelposition="top"
												maxlength="7"
												onkeyup="var no_digito = /\D/g; this.value = this.value.replace(no_digito , '');" />
										</td>
									</tr>
								</table>
								<h5 class="formulario">
									<s:text name="correo.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>correo</s:param>
								</s:fielderror>
								<s:textfield name="correo"></s:textfield>
								<input type="submit" value='<s:text name="guardar"></s:text>' />
							</div>
						</div>
					</form>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>