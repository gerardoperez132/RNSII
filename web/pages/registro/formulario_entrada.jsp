<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/messages">
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
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script src="res/js/messages_es.js" type="text/javascript" charset="UTF-8"></script>
<script src="res/js/registro/formulario_entrada.js" type="text/javascript" charset="UTF-8"></script>
<title><s:text name="form.entrada.registro.title"></s:text></title>
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
			<table><tr><td>
				<span style="font-weight: bolder;">
					<s:text name="registro.title"/>							
				</span></td>
			<td></tr></table>				
		</div>			
				
		<ul class="tabs">					
			<li>
				<a href="prepararDescripcionGeneral"><s:text name="tab1.title"></s:text></a>
			</li>
			<li>
				<a href="prepararAspectosLegales"><s:text name="tab2.title"></s:text></a>
			</li>
			<li>
				<a href="prepararDescripcionTecnica"><s:text name="tab3.title"></s:text></a>
			</li>
			<li>
				<a href="prepararDescripcionSoporte"><s:text name="tab4.title"></s:text></a>
			</li>
			<li class="active">
				<a href="prepararFuncionalidades"><s:text name="funcionalidades"></s:text></a>
			</li>										
		</ul>

		<div class="tab_container" style="height: 450px;">					
			<div class="tab_content">			
			
			<h3 class="formulario">
				<s:text name="funcionalidad.registro" />
			</h3>
			<small><s:text name="funcionalidad.registro.description"></s:text></small>
			<hr>
				
			<ul class="tabs">
				<li><a href="#tab1"><s:text name="tab1.title"></s:text> </a>
				</li>
				<li class="active"><a><s:text name="tab.entrada"></s:text>
				</a></li>
				<li><a href="#tab3"><s:text name="tab.salida"></s:text> </a>
				</li>
				<li><a href="#tab4"><s:text name="tab.resumen"></s:text> </a>
				</li>
			</ul>
			<div class="tab_container">			
			
				<s:if test="!modificar">
					<s:set name="action" var="action">registrarEntrada</s:set>
					<s:set name="modificar" value="%{false}" />
					<s:set name="submit" var="submit">
						<s:text name="form.entrada.guardar"/>
					</s:set>
				</s:if>
				<s:else>
					<s:set name="action" var="action">modificarEntrada</s:set>
					<s:set name="modificar" value="%{true}" />
					<s:set name="submit" var="submit">
						<s:text name="form.entrada.modificar"/>
					</s:set>
				</s:else>
				<!-- Formulario para registrar o modificar entrada -->
				<div id="tab2" class="tab_content">				
				<table>
					<tr>
						<td>
						<h5 class="formulario">
							<s:if test="modificar!=true">
								<s:text name="form.entrada.registro.title"></s:text>
							</s:if>
							<s:else>
								<s:text name="form.entrada.modificar.title"></s:text>
							</s:else>
						</h5>
						</td>						
						<td align="right">
						<form action="prepararEntradas" method="POST">
							<s:hidden name="id_servicio_informacion"></s:hidden>
							<s:hidden name="id_funcionalidad"></s:hidden>
							<input type="submit" value="<s:text name="regresar"></s:text>">
						</form>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<h6 class="formulario">
							<s:text name="form.entrada.funcionalidad.title">
								<s:param>
									<s:property value="funcionalidad.nombre" />
								</s:param>
							</s:text>
						</h6>
						</td>
					</tr>			
				</table>
				
				<hr>
				<form action="<s:property value="#action"></s:property>"
					method="post" name="formES" id="formES">						
											
						<!-- Nombre de la entrada. -->
						<h5 class="formulario">
							<s:text name="form.entrada.nombre.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>entrada.nombre</s:param>
						</s:fielderror>
						<s:textfield name="entrada.nombre" id="entrada.nombre"/>
						<br>
						<!-- Descripción de la entrada. -->
						<h5 class="formulario">
							<s:text name="form.entrada.descripcion.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>entrada.descripcion</s:param>
						</s:fielderror>
						<s:textarea name="entrada.descripcion" id="entrada.descripcion" cols="30" rows="5" />
						<br>
						<h5 class="formulario">
							<s:text name="form.entrada.dato.title"></s:text>
						</h5>
						<s:fielderror>
							<s:param>tipodato</s:param>
						</s:fielderror>
						<s:select name="entrada.id_tipo_dato" id="entrada.id_tipo_dato" list="tipoDatos"
							listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
							headerValue="%{getText('form.entrada.dato.select')}" >
						</s:select>
						<br>								
						 
						<s:if test="!complejo"> 
						<div id="capa_formato" style="visibility: visible; position:relative;">
							<h5 class="formulario">
								<s:text name="form.entrada.formato"/>
							</h5>
							<s:fielderror>
								<s:param>formato</s:param>
							</s:fielderror>								
							<select name="entrada.id_formato" id="entrada.id_formato" class="<s:property value='entrada.id_formato'/>">
								<s:set var="idF" value="entrada.id_formato" />
								<optgroup label="">
									<option value="-1">
										<s:text name="form.entrada.formato.select"></s:text>
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
						<div id="capa_longitud" style="visibility: visible; position:relative;">
							<h5 class="formulario">
								<s:text name="form.entrada.longitud"/>
							</h5>
							<s:fielderror>
								<s:param>longitud</s:param>
							</s:fielderror>
								<s:textfield name="entrada.longitud" maxlength="10" id="entrada.longitud"/>
								<span id="longitud_msj"></span>
						</div>
						</s:if>
						<br>
						<s:hidden name="id_servicio_informacion"/>
						<s:hidden name="id_funcionalidad"/>
						<s:hidden name="id_entrada_salida"/>
						<s:hidden name="id_entrada_padre"/>
						<s:hidden name="modificar"/>
						<s:hidden name="complejo"/>
						<input type="submit"
							value="<s:property value="#submit"></s:property>" />
													
				</form>
				</div>
			</div>
		</div>
		</div>
		</div>		
	</div>	
	<div style="clear: both"></div>
	<div class="vacio"></div>
	<%@include file="../layout/footer.jsp"%>	
	</body>
</s:i18n>
</html>