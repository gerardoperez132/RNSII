<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/I18">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/SRSI/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.alerts.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/actions.js" charset="UTF-8"></script>

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
<s:if test="tab==0">
	<s:set name="tab" value="%{setTab(2)}" />
	<s:set name="action" value="%{'registrarAspectosLegales'}" />
	<s:set name="id_servicio" value="#session.id_servicio_informacion" />
</s:if>
<title><s:text name="title" /></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		
		<div class="container">
			<%@include file="../layout/header_ge.jsp"%>
		
		<!-- Esta es la barra lateral -->
		<%@include file="../layout/sidebar.jsp"%>			

		<!-- Este es el div de contenidos -->
		<div class="contenido">
		<div class="Titulo" style="width:800px;">
   			<h1><s:text name="titulo2" /></h1>
   		</div>
   			
		<div class="busqueda" style="width:800px;">
			<form method="post" action="Buscar_Servicio">
				<label><s:text name="buscarServicio"/></label>
				<s:textfield name="cadena" id="buscar" cssClass="inputBusqueda buscar" style="width:640px;"/>
				<input type="submit" class="submit" value="&nbsp;"/>
			</form>
		</div>
		
		<table class="main_user">	
		<tr>
			<td>
				<div>
					<h4 style="margin: 0;">
					<s:text name="bienvenido" /> 
					<s:property value="%{#session.usuario.nombre}"/>
					</h4>
				</div>
			</td>			
		</tr>		
		<tr>
			<td>
				<h4 style="margin: 0;"><s:text name="ente" />
				<s:property value="%{#session.ente_sesion.nombre}"/></h4>
			</td>										
		</tr>								
		</table>
		<div class="pasos">
			<table><tr>
				<td><small>
					<strong><s:text name="paso1" />.<s:property value="tab"/>: </strong><s:text name="tab%{tab}.title" /> - <s:property value="tab"/>
				</small></td>
				<td><s:if test="modificar">					
				<form action="prepararFuncionalidades" method="post">
					<s:hidden name="id_servicio_informacion"></s:hidden>
					<s:hidden name="modificar"></s:hidden>
					- <input type="submit" value='<s:text name="paso2" />' class="btn_form"/>
												
				</form>	
				</s:if></td>
			</tr></table>				
		</div>
				
				<h3>
					<s:text name="title"></s:text>
				</h3>
				<hr>
				<ul class="tabs">
					<s:if test="%{tab==1}">
						<li class="active">
							<a href="#tab1"><s:text	name="tab1.title"></s:text></a>
						</li>
					</s:if>
					<s:else>
						<s:if test="!nuevo">
							<s:url id="prepararFormulario" action="prepararDescripcionGeneral" />
						</s:if>
						<li>
							<s:a href="%{prepararFormulario}"><s:text name="tab1.title"></s:text></s:a>
						</li>
					</s:else>
					<s:if test="%{tab==2}">
						<li class="active">
							<a href="#tab2"><s:text	name="tab2.title"></s:text> </a>
						</li>
					</s:if>
					<s:else>
						<s:if test="!nuevo">
							<s:url id="prepararFormulario" action="prepararAspectosLegales" />
						</s:if>
						<li>
							<s:a href="%{prepararFormulario}"><s:text name="tab2.title"></s:text></s:a>
						</li>
					</s:else>
					<s:if test="%{tab==3}">
						<li class="active">
							<a href="#tab3"><s:text	name="tab3.title"></s:text> </a>
						</li>
					</s:if>
					<s:else>
					<s:if test="!nuevo">
						<s:url id="prepararFormulario" action="prepararDescripcionTecnica" />
					</s:if>
					<li>
						<s:a href="%{prepararFormulario}"><s:text name="tab3.title"></s:text></s:a>
					</li>
					</s:else>
					<s:if test="%{tab==4}">
						<li class="active">
							<a href="#tab4"><s:text name="tab4.title"></s:text> </a>
						</li>
					</s:if>
					<s:else>
						<s:if test="!nuevo">
							<s:url id="prepararFormulario"	action="prepararDescripcionSoporte" />
						</s:if>
						<li>
							<s:a href="%{prepararFormulario}"><s:text name="tab4.title"></s:text></s:a>
						</li>
					</s:else>
				</ul>
				<div class="tab_container">
					<s:if test="%{tab==1}">
						<div id="tab1" class="tab_content">
							<form action="<s:property value="#action"/>" id="formSI" name="formSI" method="post" enctype="multipart/form-data">
								<p class="formulario">
									<s:text name="tab1.subtitle" />
								</p>
								<small><s:text name="tab1.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<label for="sector">
									<s:text name="sector.title" />
									</label>
								</h5>
								<s:fielderror>
									<s:param>sector</s:param>
								</s:fielderror>
								<s:select name="sector" id="sector" list="sectores" listKey="id_sector"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('sector.select')}"></s:select>
								<h5 class="formulario">
									<s:text name="nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.nombre</s:param>
								</s:fielderror>
								<s:textfield name="servicio.nombre" id='servicio.nombre'/>
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="servicio.descripcion" id="servicio.descripcion" cols="40" rows="10" />
								<h5 class="formulario">
									<s:text name="area.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>area</s:param>
								</s:fielderror>
								<s:checkboxlist list="areas" listValue="nombre" name="area" id="area" listKey="id_area" />
								<h5 class="formulario">
									<s:text name="estado.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>estado</s:param>
								</s:fielderror>
								<s:select name="estado" id="estado" list="estados" listKey="id_estado"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('estado.select')}"></s:select>
								<s:token name="token" />
								<s:hidden name="tab" value="1" />
								<s:hidden name="id_servicio_informacion" />
								<s:hidden name="modificar"></s:hidden>
								<input type="submit" value='<s:property value="#submit"/>' />
							</form>
						</div>
					</s:if>						
					<s:if test="%{tab==2}">
					<!-- START TAB 2 -->
						<div id="tab2" class="tab_content">
							<form action="<s:property value="#action"/>" method="post"
								enctype="multipart/form-data" id="formSI_Tab2" name="formSI_Tab2">
								<p class="formulario">
									<s:text name="tab2.subtitle" />
								</p>
								<small><s:text name="tab2.description">
										<s:param>2</s:param>
									</s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="documento.name" />
								</h5>
								<s:if test="#id_servicio > 0">
									<span class="errorMessage"><s:text
											name="struts.messages.error.file.too.large" /></span>
								</s:if>
								<s:fielderror>
									<s:param>name</s:param>
								</s:fielderror>
								<s:textfield name="name" id="name"/>
								<h5 class="formulario">
									<s:text name="documento.file"></s:text>
								</h5>
								<s:fielderror>
									<s:param>file</s:param>
								</s:fielderror>
								<s:file name="file" id="file"/>
								<s:token name="token" />
								<s:hidden name="tab" value="2" />
								<s:if test="#id_servicio > 0">
									<s:hidden name="id_servicio_informacion"
										value="%{id_servicio}" />
								</s:if>
								<s:else>
									<s:hidden name="id_servicio_informacion" />
								</s:else>
								<s:hidden name="modificar"></s:hidden>
								<input type="submit" value='<s:property value="#submit"/>' />
							</form>
							<br>
							<s:if test="files.size() > 0">
								<table class="results">
									<tr>
										<th><s:text name="nombre"/></th>
										<th><s:text name="fecha"/></th>
										<th><s:text name="descargar"/></th>
										<th><s:text name="eliminar"/></th>
									</tr>
									<s:iterator value="files">
										<tr>
											<td><s:property value="nombre" /></td>												
											<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
											<td><a href="..<s:property value='url' />"><s:text name="descargar"/></a></td>
											<td>
												<form action="eliminarAspectoLegal" method="post">
													<s:hidden name="id_aspecto_legal" />
													<s:token name="token" />
													<input type="submit" value="<s:text name="eliminar"/>">
												</form>
											</td>
										</tr>
									</s:iterator>
								</table>
							</s:if>
							<s:if test="#id_servicio > 0">
								<s:bean	name="ve.gob.cnti.srsi.controlador.ServicioInformacionControlador">
									<s:param name="id_servicio_informacion" value="%{id_servicio}"></s:param>
									<table>
										<s:iterator value="files2">
											<tr>
												<td><s:property value="nombre" /></td>
												<td><a href="..<s:property value='url' />">Descargar</a>
												</td>
												<td><s:property value="fecha_creado" /></td>
												<td><form action="eliminarAspectoLegal" method="post">
														<s:hidden name="id_aspecto_legal" />
														<s:token name="token" />
														<input type="submit" value="<s:text name="eliminar"/>">
													</form></td>
											</tr>
										</s:iterator>
									</table>
								</s:bean>
							</s:if>
						</div>
					<!-- END TAB 2 -->
					</s:if>
					<s:if test="%{tab==3}">
					<!-- START TAB 3 -->
						<div id="tab3" class="tab_content">
							<form action="<s:property value="#action"/>" method="post"
								enctype="multipart/form-data" id="formSI_Tab3" name="formSI_Tab3">
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
									headerValue="%{getText('seguridad.select')}" name="seguridad"
									id="seguridad"></s:select>
								<h5 class="formulario">
									<s:text name="arquitectura.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>arquitectura</s:param>
								</s:fielderror>
								<s:checkboxlist list="arquitecturas" listValue="nombre"
									name="arquitectura" id="arquitectura" listKey="id_arquitectura" />
								<br>
								<h5 class="formulario">
									<s:text name="version.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.version</s:param>
								</s:fielderror>
								<s:textfield name="servicio.version" id="servicio.version"
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
								<select name="intercambio" id="intercambio">
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
								<s:hidden name="id_servicio_informacion" />
								<s:hidden name="modificar"></s:hidden>
								<input type="submit" value='<s:property value="#submit"/>' />
							</form>
						</div>
					<!-- END TAB 3 -->
					</s:if>						
					<s:if test="%{tab==4}">
					<!-- START TAB 4 -->
						<div id="tab4" class="tab_content">
							<form action="<s:property value="#action"/>" method="post"
								enctype="multipart/form-data" id="formSI_Tab4" name="formSI_Tab4">
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
								<s:textfield name="servicio.responsable" id="servicio.responsable" labelposition="top" />
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
												maxlength="7" id="telefono"
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
								<s:textfield name="correo" id="correo"/>
								<s:token name="token" />
								<s:hidden name="tab" value="4" />
								<s:hidden name="id_servicio_informacion" />
								<s:hidden name="modificar"></s:hidden>
								<input type="submit" value='<s:property value="#submit"/>' />
							</form>
						</div>
					</s:if>
					<!-- END TAB 4 -->
				</div>
			</div>
			
		</div>
		<div style="clear: both"></div>
	<div class="vacio"></div>
	<%@include file="../layout/footer_ge.jsp"%>
		
	</body>
</s:i18n>
</html>