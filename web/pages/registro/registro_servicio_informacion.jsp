<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/RNSII/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/RNSII/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.alerts.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script src="res/js/messages_es.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/registro_servicio_informacion.js" charset="UTF-8"></script>

<!-- Added... -->
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<%@include file="../layout/header_joomla.jsp" %>

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
	<body class="bg clearfix">
	<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="../layout/menus.jsp"%>
		<div class="content">
			
		
		<!-- Esta es la barra lateral -->
		<%@include file="../layout/sidebar.jsp"%>			

		<!-- Este es el div de contenidos -->
		<div class="contenido">
		<h1><a><s:text name="titulo2" /></a></h1>
		<div class="pasos">
			<table><tr><td>
				<span style="font-weight: bolder;">
					<s:text name="registro.title"/>							
				</span></td>
			<td></tr></table>				
		</div>
				
		<ul class="tabs">
			<s:if test="%{tab==1}">
				<li class="active">
					<a><s:text	name="tab1.title"></s:text></a>
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
					<a><s:text	name="tab2.title"></s:text> </a>
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
					<a><s:text	name="tab3.title"></s:text> </a>
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
			<s:if test="nuevo">
				<li>
					<s:a><s:text name="funcionalidades"></s:text></s:a>
				</li>
			</s:if>
			<s:else>
				<li>
					<s:a href="prepararFuncionalidades"><s:text name="funcionalidades"></s:text></s:a>
				</li>
			</s:else>	
			<s:if test="%{tab==4}">
				<li class="active">
					<a><s:text name="tab4.title"></s:text> </a>
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
			<s:if test="%{tab==1}">
				<form action="<s:property value="#action"/>" id="formSI" name="formSI" method="post" enctype="multipart/form-data">
				<div class="tab_description">
					<div class="tab_description_left">
					<h3 class="formulario">
						<s:text name="tab1.subtitle" />
					</h3>
					<small class="form_small"><s:text name="tab1.description"></s:text> </small>
					</div>
					<div class="tab_description_right">
					<input type="submit" class="tab_button" name="submit" value='<s:property value="#submit"/>' id="btn_submit"/>
					<br>
					<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
					</div>					
				</div>
				<div class="tab_container_height tab_container">
					<div id="tab1" class="tab_content">	
						<h5 class="requerido">
							<s:text name="usuario.modificar.requerido" />
						</h5>
						<table>
							<tr>
							<!-- 1° col -->
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<label for="sector">
									<s:text name="sector.title" />
									</label>									 
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t1" id="t1" 
									onmouseover="tip(this);" title="">									
								</h5>
								
								<s:fielderror>
									<s:param>sector</s:param>
								</s:fielderror>
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="estado.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t5" id="t5" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>estado</s:param>
								</s:fielderror>
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:select name="sector" id="sector" list="sectores" listKey="id_sector"
									listValue="nombre" headerKey="-1" headerValue="%{getText('sector.select')}">
								</s:select>
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
								<s:select name="estado" id="estado" list="estados" listKey="id_estado"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('estado.select')}"/>								
							</td>							
							</tr>
						</table>
						
						<!-- 2° tabla -->
						<table>
							<tr>
							<!-- 1° col -->
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="nombre.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
										name="t2" id="t2" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>servicio.nombre</s:param>
								</s:fielderror>
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="area.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t4" id="t4" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>area</s:param>
								</s:fielderror>
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:textfield name="servicio.nombre" id='servicio.nombre' style="width: 221px;"/>
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
								<s:checkboxlist list="areas" listValue="nombre" name="area" id="area" 
									listKey="id_area" />							
							</td>							
							</tr>
						</table>	
							
							
							<h5 class="formulario">
								<span style="color:red;">*</span>
								<s:text name="descripcion.title"></s:text>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t3" id="t3" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>servicio.descripcion</s:param>
							</s:fielderror>
							<s:textarea name="servicio.descripcion" id="servicio.descripcion" cols="40" rows="10" />
														
							
							<s:token name="token" />
							<s:hidden name="tab" value="1" />
							<s:hidden name="id_servicio_informacion" />
							<s:hidden name="modificar"></s:hidden>													
					</div>
					</div>
					</form>
					<div class="t1" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.sector"/>
					</div>
					<div class="t2" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.nombre"/>
					</div>
					<div class="t3" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.descripcion"/>
					</div>
					<div class="t4" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.area"/>
					</div>
					<div class="t5" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.estado"/>
					</div>
				</s:if>						
				<s:if test="%{tab==2}">
				<!-- START TAB 2 -->
				
				<div class="tab_description">
					<div class="tab_description_left">
					<h3 class="formulario">
						<s:text name="tab2.subtitle" />
					</h3>
					<small class="form_small"><s:text name="tab2.description"></s:text> </small>
					</div>
					<div class="tab_description_right">
					<br>
					<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
					</div>
				</div>
				<div class="tab_container_height tab_container">
					<div id="tab2" class="tab_content">
						<form action="<s:property value="#action"/>" method="post"
							enctype="multipart/form-data" id="formSI_Tab2" name="formSI_Tab2">
						
							<h5 class="formulario">
								<s:text name="tab2.info"/>
							</h5>	
							<h5 class="requerido" style="margin-left: 10px;">
								<s:text name="usuario.modificar.requerido" />
							</h5>
							
						<table>
							<tr>
							<!-- 1° col -->
							<td>
							
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="documento.name" />
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t1" id="t1" onmouseover="tip(this);" title="">
							
								</h5>
								<s:if test="#id_servicio > 0">
									<span class="errorMessage"><s:text
											name="struts.messages.error.file.too.large" /></span>
								</s:if>
								<s:fielderror>
									<s:param>name</s:param>
								</s:fielderror>								
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="documento.file"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t2" id="t2" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>file</s:param>
								</s:fielderror>
								
								
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:textfield name="name" id="name"/>
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
								<s:file name="file" id="file"/>							
							</td>							
							</tr>
						</table>
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
							<input class="button_h" type="submit" value="<s:text name="upload"/>" />
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
							<s:bean	name="ve.gob.cnti.rnsii.controlador.ServicioInformacionControlador">
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
					</div>
				<div class="t1" style="visibility: hidden; display: none;">
					<s:text name="tooltip.registro.documento.nombre"/>
				</div>
				<div class="t2" style="visibility: hidden; display: none;">
					<s:text name="tooltip.registro.documento.file"/>
				</div>
				<!-- END TAB 2 -->
				</s:if>
				<s:if test="%{tab==3}">
				<!-- START TAB 3 -->
				<form action="<s:property value="#action"/>" method="post"
							enctype="multipart/form-data" id="formSI_Tab3" name="formSI_Tab3">
				<div class="tab_description">
					<div class="tab_description_left">
					<h3 class="formulario">
						<s:text name="tab3.subtitle" />
					</h3>
					<small class="form_small"><s:text name="tab3.description"></s:text> </small>
					</div>
					<div class="tab_description_right">
					<input type="submit" class="tab_button" value='<s:property value="#submit"/>' id="btn_submit" />
					<br>
					<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
					</div>					
				</div>	
				<div class="tab_container_height tab_container">			
					<div id="tab3" class="tab_content">
						<h5 class="requerido">
							<s:text name="usuario.modificar.requerido" />
						</h5>
						<table>
							<tr>
							<!-- 1° col -->
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="seguridad.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t1" id="t1" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>seguridad</s:param>
								</s:fielderror>							
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="arquitectura.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
										name="t2" id="t2" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>arquitectura</s:param>
								</s:fielderror>								
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:select list="niveles" listKey="id_seguridad" listValue="nombre" 
									headerKey="-1" headerValue="%{getText('seguridad.select')}" 
									name="seguridad" id="seguridad"/>
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
								<s:checkboxlist list="arquitecturas" listValue="nombre"
									name="arquitectura" id="arquitectura" listKey="id_arquitectura" />						
							</td>							
							</tr>
						</table>
						
						<table>
							<tr>
							<!-- 1° col -->
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="version.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t3" id="t3" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>servicio.version</s:param>
								</s:fielderror>		
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="intercambio.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t4" id="t4" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>intercambio</s:param>
								</s:fielderror>
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:textfield name="servicio.version" id="servicio.version"
										onkeyup="var pattern = /[^0-9\.]/g;
								this.value = this.value.replace(pattern, '');"
										maxlength="7" style="width:214px;"/>
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
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
							</td>							
							</tr>
						</table>
							
							
							<h5 class="formulario">
								<s:text name="wsdl.title"/>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t5" id="t5" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>wsdl</s:param>
							</s:fielderror>
							<s:textfield name="wsdl" id="wsdl"/>							
							<s:token name="token" />
							<s:hidden name="tab" value="3" />
							<s:hidden name="id_servicio_informacion" />
							<s:hidden name="modificar"/>
					</div>
					</div>
					</form>
					<div class="t1" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.seguridad"/>
					</div>
					<div class="t2" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.arquitectura"/>
					</div>
					<div class="t3" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.version"/>
					</div>
					<div class="t4" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.intercambio"/>
					</div>
					<div class="t5" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.wsdl"/>
					</div>
				<!-- END TAB 3 -->
				</s:if>						
				<s:if test="%{tab==4}">
				<form action="<s:property value="#action"/>" method="post"
							enctype="multipart/form-data" id="formSI_Tab4" name="formSI_Tab4">
				<div class="tab_description">
					<div class="tab_description_left">
					<h3 class="formulario">
						<s:text name="tab4.subtitle" />
					</h3>
					<small class="form_small"><s:text name="tab4.description"></s:text> </small>
					</div>
					<div class="tab_description_right">
					<input type="submit" class="tab_button" value='<s:property value="#submit"/>' id="btn_submit" />
					<br>
					<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
					</div>					
				</div>	
				<div class="tab_container_height tab_container">	
				<!-- START TAB 4 -->
					<div id="tab4" class="tab_content">
						<h5 class="requerido">
							<s:text name="usuario.modificar.requerido" />
						</h5>
						<table>
							<tr>
							<!-- 1° col -->
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="responsable.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t1" id="t1" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>servicio.responsable</s:param>
								</s:fielderror>	
							</td>							
							<td style="width:60px;">
								<!-- td vacio -->
							</td>
							<td>
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="telefono.title"></s:text>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
													name="t2" id="t2" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>telefono</s:param>
								</s:fielderror>															
							</td>							
							</tr>
							<tr>
							<!-- 2° col -->
							<td>
								<s:textfield name="servicio.responsable" id="servicio.responsable" 
									labelposition="top" />
							</td>								
							<td style="width:60px;">
								<!-- td vacio -->
							</td>						
							<td>
								<table>
									<tr>
										<td><s:select name="codigo" list="codigos" /></td>
										<td><s:textfield name="telefono" labelposition="top"
												maxlength="7" id="telefono"	/>												
										</td>
									</tr>
								</table>						
							</td>							
							</tr>
						</table>							
							
							<h5 class="formulario">
								<span style="color:red;">*</span>
								<s:text name="correo.title"/>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t3" id="t3" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>correo</s:param>
							</s:fielderror>
							<s:textfield name="correo" id="correo"/>
							
							<s:token name="token" />
							<s:hidden name="tab" value="4" />
							<s:hidden name="id_servicio_informacion" />
							<s:hidden name="modificar"></s:hidden>													
					</div></div>
					</form>
					<div class="t1" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.responsable"/>
					</div>
					<div class="t2" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.telefono"/>
					</div>
					<div class="t3" style="visibility: hidden; display: none;">
						<s:text name="tooltip.registro.correo"/>
					</div>
				</s:if>
				<!-- END TAB 4 -->
			</div>
			</div>
		<div class="clr"></div>
		<%@include file="../layout/breadcrumbs.jsp"%>	
		<%@include file="../layout/links_over_footer.jsp"%>	
		</div>		
		</div>		
		<!-- Footer -->	
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>

