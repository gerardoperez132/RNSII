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
<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script src="res/js/messages_es.js" type="text/javascript" charset="UTF-8"></script>
<script src="res/js/registro/formulario_salida.js" type="text/javascript" charset="UTF-8"></script>
<title><s:text name="registro.title"></s:text></title>
</head>
<body>	
	<div class="container">
		<%@include file="../layout/header.jsp"%>
		
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
			<table>
				<tr>
					<td>						
						<small>									
							<strong>
								<s:text name="paso2.1.2" />									
								<s:text name="funcionalidades" /> -										
							</strong>
							<s:text name="tab.salida"/>
						</small>
					</td>							
				</tr>
			</table>
		</div>	
			<h3>
				<s:text name="funcionalidades.title" />
			</h3>
													
			<form action="prepararSalidas" method="POST">
				<s:hidden name="id_servicio_informacion"></s:hidden>
				<s:hidden name="id_funcionalidad"></s:hidden>
				<input type="submit" value="<s:text name="regresar"></s:text>">
			</form>
			
			<hr>
			<ul class="tabs">
				<li><a href="#tab1"><s:text name="tab1.title"></s:text> </a>
				</li>
				<li><a><s:text name="tab.entrada"></s:text>
				</a></li>
				<li  class="active"><a href="#tab3"><s:text name="tab.salida"></s:text> </a>
				</li>
				<li><a href="#tab4"><s:text name="tab.resumen"></s:text> </a>
				</li>
			</ul>
			<div class="tab_container">
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
				<!-- Formulario para registrar o modificar entrada -->
				<form action="<s:property value="#action"></s:property>"
					method="post" name="formES" id="formES">
					<div id="tab2" class="tab_content">
						<h5 class="formulario">
							<s:if test="modificar!=true">
								<s:text name="registro.title"></s:text>
							</s:if>
							<s:else>
								<s:text name="modificar"></s:text>
							</s:else>
						</h5>
						<h6>
							<s:text name="funcionalidad.title">
								<s:param>
									<s:property value="funcionalidad.nombre" />
								</s:param>
							</s:text>
						</h6>
						<hr>
						<!-- Nombre de la salida. -->
						<h5 class="formulario">
							<s:text name="nombre.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>salida.nombre</s:param>
						</s:fielderror>
						<s:textfield name="salida.nombre" id="salida.nombre" />
						<br>
						<!-- Descripción de la salida. -->
						<h5 class="formulario">
							<s:text name="descripcion.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>salida.descripcion</s:param>
						</s:fielderror>
						<s:textarea name="salida.descripcion" id="salida.descripcion" cols="30" rows="5" />
						<br>
						<h5 class="formulario">
							<s:text name="dato.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>tipodato</s:param>
						</s:fielderror>
						<s:select name="salida.id_tipo_dato" id="salida.id_tipo_dato" list="tipoDatos"
							listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
							headerValue="%{getText('dato.select')}"></s:select>
						<br> 
						
						
						<s:if test="complejo!=true">
						<div id="capa_formato" style="visibility: visible; position:relative;">
							<h5 class="formulario">
								<s:text name="form.salida.formato"/>
							</h5>
							<s:fielderror>
								<s:param>formato</s:param>
							</s:fielderror>								
							<select name="salida.id_formato" id="salida.id_formato">
								<s:set var="idF" value="salida.id_formato"/>
								<optgroup label="">
									<option value="-1">
										<s:text name="form.salida.formato.select"></s:text>
									</option>
								</optgroup>
								<s:iterator value="tipoDatos" status="td_status">										
									<s:set var="id_td" value="id_tipo_dato"/>
									<s:if test="hasformatted == true">
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
						<div id="capa_longitud" style="visibility: visible; position:relative;">
							<h5 class="formulario">
								<s:text name="form.salida.longitud"/>
							</h5>
							<s:fielderror>
								<s:param>longitud</s:param>
							</s:fielderror>
							<s:textfield name="salida.longitud" id="salida.longitud" maxlength="10"/>
								<span id="longitud_msj"></span>
						</div>
						
						</s:if>
						
						<br>
						<s:hidden name="id_servicio_informacion"></s:hidden>
						<s:hidden name="id_funcionalidad"></s:hidden>
						<s:hidden name="id_entrada_salida"></s:hidden>
						<s:hidden name="modificar"></s:hidden>
						<s:hidden name="complejo"></s:hidden>
						<input type="submit"
							value="<s:property value="#submit"></s:property>" />
					</div>
				</form>
			</div>
		</div>		
	</div>
	<div style="clear: both"></div>
	<div class="vacio"></div>
	<%@include file="../layout/footer.jsp"%>		
	</body>
</s:i18n>
</html>