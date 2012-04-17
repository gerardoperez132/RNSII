<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/registro_servicio_informacion">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<script type="text/javascript" src="res/js/tabs.js"></script>
<s:if test="modificar">
	<s:set name="submit" value="%{getText('actualizar')}" />
	<s:set name="title" value="%{getText('actualizar.title')}" />
</s:if>
<s:else>
	<s:set name="submit" value="%{getText('guardar')}" />
	<s:set name="title" value="%{getText('registro.title')}" />
</s:else>
<s:if test="tab==1">
	<s:set name="action" value="%{'registrarDescripcionGeneral'}" />
</s:if>
<s:if test="tab==2">
	<s:set name="action" value="%{'registrarAspectosLegales'}" />
</s:if>
<s:if test="tab==3">
	<s:set name="action" value="%{'registrarDescripcionTecnica'}" />
</s:if>
<s:if test="tab==4">
	<s:set name="action" value="%{'registrarDescripcionSoporte'}" />
</s:if>
<title><s:text name="title"></s:text></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<%@include file="../layout/sidebar.jsp"%>
				<div id="content">
					<small><s:text name="title" /> / <strong>Paso 1:</strong>
						/ Paso 2 / Paso 3</small>
					<h3>
						<s:text name="title"></s:text>
					</h3>
					<hr>
					<ul class="tabs">
						<s:if test="%{tab==1}">
							<li class="active"><a href="#tab1"><s:text
										name="tab1.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<s:url id="prepararFormulario"
								action="prepararDescripcionGeneral" />
							<li><s:a href="%{prepararFormulario}">
									<s:text name="tab1.title"></s:text>
								</s:a></li>
						</s:else>
						<s:if test="%{tab==2}">
							<li class="active"><a href="#tab2"><s:text
										name="tab2.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<s:url id="prepararFormulario" action="prepararAspectosLegales" />
							<li><s:a href="%{prepararFormulario}">
									<s:text name="tab2.title"></s:text>
								</s:a></li>
						</s:else>
						<s:if test="%{tab==3}">
							<li class="active"><a href="#tab3"><s:text
										name="tab3.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<s:url id="prepararFormulario"
								action="prepararDescripcionTecnica" />
							<li><s:a href="%{prepararFormulario}">
									<s:text name="tab3.title"></s:text>
								</s:a></li>
						</s:else>
						<s:if test="%{tab==4}">
							<li class="active"><a href="#tab4"><s:text
										name="tab4.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<s:url id="prepararFormulario"
								action="prepararDescripcionSoporte" />
							<li><s:a href="%{prepararFormulario}">
									<s:text name="tab4.title"></s:text>
								</s:a></li>
						</s:else>
					</ul>
					<div class="tab_container">
						<s:if test="%{tab==1}">
							<div id="tab1" class="tab_content">
								<form action="<s:property value="#action"/>" method="post"
									enctype="multipart/form-data">
									<p class="formulario">
										<s:text name="tab1.subtitle" />
									</p>
									<small><s:text name="tab1.description"></s:text> </small>
									<hr>
									<h5 class="formulario">
										<s:text name="sector.title" />
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
									<s:token name="token" />
									<s:hidden name="tab" value="1" />
									<s:hidden name="id_servicio_informacion"
										value="%{id_servicio_informacion}" />
									<input type="submit" value='<s:property value="#submit"/>' />
								</form>
							</div>
						</s:if>
						<!-- START TAB 2 -->
						<s:if test="%{tab==2}">
							<div id="tab2" class="tab_content">
								<form action="<s:property value="#action"/>" method="post"
									enctype="multipart/form-data">
									<p class="formulario">
										<s:text name="tab2.subtitle" />
									</p>
									<small><s:text name="tab2.description"></s:text> </small>
									<hr>
									<h5 class="formulario">
										<s:text name="documento.name"></s:text>
									</h5>
									<s:fielderror>
										<s:param>name</s:param>
									</s:fielderror>
									<s:textfield name="name" />
									<h5 class="formulario">
										<s:text name="documento.file"></s:text>
									</h5>
									<s:fielderror>
										<s:param>file</s:param>
									</s:fielderror>
									<s:file name="file" />
									<s:token name="token" />
									<s:hidden name="tab" value="2" />
									<s:hidden name="id_servicio_informacion"
										value="%{id_servicio_informacion}" />
									<input type="submit" value='<s:property value="#submit"/>' />
								</form>
								<s:if test="files.size() > 0">
									<table>
										<s:iterator var="files">
											<tr>
												<td><s:property value="name" />
												</td>
												<td><s:property value="fileName" />
												</td>
											</tr>
										</s:iterator>
									</table>
								</s:if>
							</div>
						</s:if>
						<!-- END TAB 2 -->
						<!-- START TAB 3 -->
						<s:if test="%{tab==3}">
							<div id="tab3" class="tab_content">
								<form action="<s:property value="#action"/>" method="post"
									enctype="multipart/form-data">
									<p class="formulario">
										<s:text name="tab3.subtitle" />
									</p>
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
									<s:checkboxlist list="arquitecturas" listValue="nombre"
										name="arquitectura" listKey="id_arquitectura" />
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
									<s:token name="token" />
									<s:hidden name="tab" value="3" />
									<s:hidden name="id_servicio_informacion"
										value="%{id_servicio_informacion}" />
									<input type="submit" value='<s:property value="#submit"/>' />
								</form>
							</div>
						</s:if>
						<!-- END TAB 3 -->
						<!-- START TAB 4 -->
						<s:if test="%{tab==4}">
							<div id="tab4" class="tab_content">
								<form action="<s:property value="#action"/>" method="post"
									enctype="multipart/form-data">
									<p class="formulario">
										<s:text name="tab4.subtitle" />
									</p>
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
											<td><s:select name="codigo" list="codigos" /></td>
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
									<s:token name="token" />
									<s:hidden name="tab" value="4" />
									<s:hidden name="id_servicio_informacion"
										value="%{id_servicio_informacion}" />
									<input type="submit" value='<s:property value="#submit"/>' />
								</form>
							</div>
						</s:if>
						<!-- END TAB 4 -->
					</div>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>