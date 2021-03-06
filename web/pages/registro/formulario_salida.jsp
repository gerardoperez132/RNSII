<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="robots" content="index, follow">
		<meta name="description" content="Registro Nacional de Servicios Interoperables del Estado Venezolano" />
 		<meta name="keywords" content="Servicios Interoperables del Estado Venezolano, rnsii, servicios web, interoperabilidad Venezuela, interoperabilidad Gobierno de Venezuela,UDDI Venezuela" />
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script src="res/js/messages_es.js" type="text/javascript" charset="UTF-8"></script>
<script src="res/js/main.js" type="text/javascript" charset="UTF-8"></script>
<script src="res/js/registro/formulario_salida.js" type="text/javascript" charset="UTF-8"></script>
<!-- Added... -->
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<%@include file="../layout/header_joomla.jsp" %>
<s:set name="finalizar" value="true" />
<s:iterator value="tabs_incompletas" status="status">
<s:if test="detalles.size()>0">
	<s:set name="finalizar" value="false" />
</s:if>		
</s:iterator>
<title><s:text name="registro.title"></s:text></title>
</head>
<body class="bg clearfix">
	<s:iterator value="tabs_incompletas" status="status">			
		<div class="h<s:property value="tab" />" style="visibility: hidden; display: none;">	
			<s:if test="detalles.size()>0">
				<s:iterator value="detalles">
					<p><s:property/></p>
				</s:iterator>
			</s:if>		
			<s:else>
				<p><s:text name="servicio.tab.complete"></s:text></p>
			</s:else>			
		</div>						
	</s:iterator>
	<div class="h2" style="visibility: hidden; display: none;">
			<p><s:text name="servicio.tab.complete"></s:text></p>
	</div>
	<div class="n" style="visibility: hidden; display: none;">
		<s:property value="tabs_incompletas.size()+1" />
	</div>
	<s:if test="modificar!=true">
		<s:set name="action" var="action">registrarSalida</s:set>
		<s:set name="modificar" value="%{false}" />
		<s:set name="submit" var="submit">
			<s:text name="guardar"></s:text>
		</s:set>
	</s:if>
	<s:else>
		<s:set name="action" var="action">modificarSalida</s:set>
		<s:set name="modificar" value="%{true}" />
		<s:set name="submit" var="submit">
			<s:text name="modificar"></s:text>
		</s:set>
	</s:else>
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
			<li>
				<a href="prepararDescripcionGeneral">
					<s:text name="tab1.title"></s:text>
					<s:iterator value="tabs_incompletas" status="status">
					<s:if test="tab==1">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h1" alt="ayuda" 
							onmouseover="tip(this);" name="h1" height="15" width="15" />
						</s:if>							
						<s:else>
							<img src="res/img/correcto.png" id="h1" alt="ayuda" 
							onmouseover="tip(this);" name="h1" height="15" width="15" />
						</s:else>
					</s:if>					
					</s:iterator>
				</a>
			</li>
			<li>
				<a href="prepararAspectosLegales">
					<s:text name="tab2.title"></s:text>							
				</a>
			</li>
			<li>
				<a href="prepararDescripcionTecnica">
					<s:text name="tab3.title"></s:text>
					<s:iterator value="tabs_incompletas" status="status">
					<s:if test="tab==3">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h3" alt="ayuda" 
							onmouseover="tip(this);" name="h3" height="15" width="15" />
						</s:if>							
						<s:else>
							<img src="res/img/correcto.png" id="h3" alt="ayuda" 
							onmouseover="tip(this);" name="h3" height="15" width="15" />
						</s:else>
					</s:if>					
					</s:iterator>
				</a>
			</li>
			<li class="active">
				<a>
					<s:text name="funcionalidades"></s:text>
					<s:iterator value="tabs_incompletas" status="status">
					<s:if test="tab==4">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h4" alt="ayuda" 
							onmouseover="tip(this);" name="h4" height="15" width="15" />
						</s:if>							
						<s:else>
							<img src="res/img/correcto.png" id="h4" alt="ayuda" 
							onmouseover="tip(this);" name="h4" height="15" width="15" />
						</s:else>
					</s:if>					
					</s:iterator>
				</a>
			</li>
			<li>
				<a href="prepararDescripcionSoporte">
					<s:text name="tab4.title"></s:text>
					<s:iterator value="tabs_incompletas" status="status">
					<s:if test="tab==5">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h5" alt="ayuda" 
							onmouseover="tip(this);" name="h5" height="15" width="15" />
						</s:if>							
						<s:else>
							<img src="res/img/correcto.png" id="h5" alt="ayuda" 
							onmouseover="tip(this);" name="h5" height="15" width="15" />
						</s:else>
					</s:if>					
					</s:iterator>
				</a>
			</li>
		</ul>
		
		<div class="tab_description">
			<div class="tab_description_left">
				<h3 class="formulario">
					<s:text name="funcionalidad.registro" />
				</h3>
				<small><s:text name="funcionalidad.registro.description"></s:text></small>
			</div>	
			<div class="tab_description_right_2">
					<table>
						<tr>
							<td>
								<input type="button" id="sub_regresar" class="button_h btn_oculto" value="<s:text name="regresar"></s:text>">
							</td>							
						</tr>
						<tr>
							<td>
								<input type="button" id="sub_guardar_salida" class="button_h btn_oculto" value="<s:property value="#submit"></s:property>" >
							</td>
						</tr>
						<tr>
							<td>
								<s:if test="finalizar">					
								<br>
								<form action="terminar_registro_si" id="formSI" name="formSI" method="post" enctype="multipart/form-data">
									<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
								</form>
								</s:if>	
							</td>
						</tr>
					</table>
					
									
				</div>														
		</div>

		<div class="tab_container" style="height: 450px;">					
			<div class="tab_content">
						
			<ul class="tabs">
				<li><a href="#tab1">
					<s:text name="tab1.title"></s:text>
					<img src="res/img/correcto.png" id="h2" alt="ayuda" 
								onmouseover="tip(this);" name="h2" height="15" width="15" />
				</a>
				</li>
				<li><a><s:text name="tab.entrada"></s:text>
				</a></li>
				<li class="active">
					<a href="#tab3">
						<s:text name="tab.salida"></s:text>
						<s:iterator value="tabs_incompletas" status="status">
						<s:if test="tab==6">
							<s:if test="detalles.size()>0">
								<img src="res/img/important.png" id="h6" alt="ayuda" 
								onmouseover="tip(this);" name="h6" height="15" width="15" />
							</s:if>							
							<s:else>
								<img src="res/img/correcto.png" id="h6" alt="ayuda" 
								onmouseover="tip(this);" name="h6" height="15" width="15" />
							</s:else>
						</s:if>					
						</s:iterator> 
					</a>
				</li>
				<li><a href="#tab4"><s:text name="tab.resumen"></s:text> </a>
				</li>
			</ul>
			<div class="tab_container">				
				<!-- Formulario para registrar o modificar entrada -->				
				<div id="tab2" class="tab_content">
				<table>
					<tr>												
						<td align="right">
						<form action="prepararSalidas" method="POST" name="f_regresar">
							<s:hidden name="id_servicio_informacion"></s:hidden>
							<s:hidden name="id_funcionalidad"></s:hidden>
							<input type="submit" value="<s:text name="regresar"></s:text>" id="btn_regresar"/>
						</form>
						</td>
					</tr>
					<tr>
						<td>
						<h6 class="formulario">
							<s:text name="funcionalidad.title">
								<s:param>
									<s:property value="funcionalidad.nombre" />
								</s:param>
							</s:text>
						</h6>
						</td>
					</tr>			
				</table>
				<hr>
				<h5 class="requerido">
					<s:text name="usuario.modificar.requerido" />
				</h5>
				<form action="<s:property value="#action"></s:property>"
					method="post" name="formES" id="formES">
					
					<table>
					<tr>
						<!-- Etiquetas -->
						<td>
							<h5 class="formulario">
								<span style="color:red;">*</span>
								<s:text name="nombre.title"></s:text>								
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t1" id="t1" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>salida.nombre</s:param>
							</s:fielderror>
						</td>
						<td style="width:60px;"><!-- td vacio --></td>
						<td>
							<h5 class="formulario">
								<span style="color:red;">*</span>
								<s:text name="descripcion.title"></s:text>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t2" id="t2" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>salida.descripcion</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<!-- Elementos del formularios -->
						<td>
							<s:textfield name="salida.nombre" id="salida.nombre"/>
						</td>
						<td style="width:60px;"><!-- td vacio --></td>
						<td rowspan="4">
							<s:textarea name="salida.descripcion" id="salida.descripcion" cols="30" rows="6" />
						</td>
					</tr>	
					<s:if test="!complejo"> 
					<tr>
						<!-- Etiquetas -->
						<td>
							<h5 class="formulario">
								<span style="color:red;">*</span>
								<s:text name="dato.title"></s:text>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t3" id="t3" onmouseover="tip(this);" title="">
							</h5>
							<s:fielderror>
								<s:param>tipodato</s:param>
							</s:fielderror>
						</td>
						<td style="width:60px;"><!-- td vacio --></td>						
					</tr>
					<tr>
						<!-- Elementos del formularios -->
						<td><s:select name="salida.id_tipo_dato" id="salida.id_tipo_dato" list="tipoDatos"
								listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
								headerValue="%{getText('form.salida.dato.select')}" >
							</s:select></td>
						<td style="width:60px;"><!-- td vacio --></td>						
					</tr>
					</s:if>
					</table>					
					<s:if test="!complejo"> 
					<table>
					<tr>
						<!-- Etiquetas -->
						<td>
							<div id="capa_formato_label" style="visibility: visible; position:relative;">
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="form.salida.formato"/>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
									name="t4" id="t4" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>formato</s:param>
								</s:fielderror>
							</div>									
						</td>
						<td style="width:60px;"><!-- td vacio --></td>
						<td>
							<div id="capa_longitud_label" style="visibility: visible; position:relative;">
								<h5 class="formulario">
									<span style="color:red;">*</span>
									<s:text name="form.salida.longitud"/>
									<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip"
									 name="t5" id="t5" onmouseover="tip(this);" title="">
								</h5>
								<s:fielderror>
									<s:param>longitud</s:param>
								</s:fielderror>
								<span id="longitud_msj"></span>								
							</div>
							
						</td>
					</tr>
					<tr>
						<!-- Elementos del formularios -->
						<td>
							<div id="capa_formato_element_form" style="visibility: visible; position:relative;">
								<select name="salida.id_formato" id="salida.id_formato" class="<s:property value='salida.id_formato'/>">
									<s:set var="idF" value="salida.id_formato" />
									<optgroup label="">
										<option value="-1">
											<s:text name="form.salida.formato.select"></s:text>
										</option>
									</optgroup>
									<s:iterator value="tipoDatos" status="td_status">										
										<s:set var="id_td" value="id_tipo_dato"/>
										<s:if test="hasformatted">
										<optgroup label="<s:property value="nombre"/>" 
										id="opt_group_<s:property value="id_tipo_dato"/>">
											<s:iterator value="formatos">
												<s:if test="%{#id_td==id_tipo_dato}">
													<s:set var="cen" value="true"/>
													<option class="opt_element" id="opt_element_<s:property value="id_formato"/>"  
														value="<s:property value="id_formato"/>" 
													<s:if test="%{#idF == id_formato}">selected="selected"</s:if>>
														<s:property value="formato"/>
													</option>
												</s:if>		
											</s:iterator>											
										</optgroup>
										</s:if>										
									</s:iterator>
								</select>							
							</div>
							
						</td>
						<td style="width:60px;"><!-- td vacio --></td>
						<td>
							<div id="capa_longitud_element_form" style="visibility: visible; position:relative;">								
								<s:textfield name="salida.longitud" maxlength="10" id="salida.longitud"/>
							</div>
						</td>
					</tr>
					</table>	
					</s:if>
					<s:else>
						<s:hidden name="salida.id_tipo_dato"></s:hidden>
					</s:else>						
					<s:hidden name="id_servicio_informacion"></s:hidden>
					<s:hidden name="id_funcionalidad"></s:hidden>
					<s:hidden name="id_entrada_salida"></s:hidden>
					<s:hidden name="id_salida_padre"></s:hidden>
					<s:hidden name="modificar"></s:hidden>
					<s:hidden name="complejo"></s:hidden>
					<input type="submit" value="<s:property value="#submit"></s:property>" id="btn_guardar_salida"/>
					
				</form>
				</div>
			</div>
		</div>
		</div>
	</div>			
	</div>	
	<div style="clear: both"></div>
	<div class="vacio"></div>
	<div class="t1" style="visibility: hidden; display: none;">
		<s:text name="tooltip.funcionalidad.salida.nombre"/>
	</div>
	<div class="t2" style="visibility: hidden; display: none;">
		<s:text name="tooltip.funcionalidad.salida.descripcion"/>
	</div>
	<div class="t3" style="visibility: hidden; display: none;">
		<s:text name="tooltip.funcionalidad.salida.dato"/>
	</div>
	<div class="t4" style="visibility: hidden; display: none;">
		<s:text name="tooltip.funcionalidad.salida.formato"/>
	</div>
	<div class="t5" style="visibility: hidden; display: none;">
		<s:text name="tooltip.funcionalidad.salida.longitud"/>
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