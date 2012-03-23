<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/userlogin">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<s:if test="%{#session.logueado != true}">
 	<META HTTP-EQUIV="Refresh" CONTENT="0;URL=../index.action">
</s:if>
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#tree").treeTable();
	});
</script>
<title><s:text name="inicio" /></title>
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
				<div id="menuv">
					<ul>
					  <li class="nivel1 primera">
					    <a href="<s:url action="home"/>" class="nivel1">
					    	<s:text name="inicio" />
					    </a>
					  </li>
					  <li class="nivel1">
					  	<a class="nivel1">
					  		<s:text name="servicios" />
					  	</a>					
						<ul class="nivel2">
							<li>
								<a href="<s:url action="prepararServicioInformacion"/>">
									<s:text name="registro" />
								</a>								
							</li>														
						</ul>					
					</li>
					<li class="nivel1">
						<a href="#" class="nivel1">
						<s:text name="configuración" />						
						</a>	
					</li>					  
					<li class="nivel1">
					   <a href="<s:url action="salir"/>" class="nivel1">
							<s:text name="salir" />
						</a>
					</li>
				  </ul>
				</div>
			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">
			<table class="main">
			<tr>
				<td style="width: 350px;">
					<h4 style="margin: 0;"><s:text name="catalogo" /></h4>
				</td>	
				<td style="width: 350px;">
					<div style="text-align:right;">
						<s:text name="bienvenido" /> 
						<s:property value="%{#session.usuario.nombre}"/>
					</div>
				</td>							
			</tr>
			<tr>
				<td colspan="2">
					<s:text name="ente" />
					<s:property value="ente.nombre"/>
				</td>
			</tr>						
			</table>
			<hr>
			<h3 style="margin: 0;">Descripcion general</h3>
			<br>
			<h5 style="margin: 0;">ID: <s:property value="servicio.id_servicio_informacion"/></h5>
			<h5 style="margin: 0;">Servicio: "<s:property value="servicio.nombre"/>"</h5>
			<h5 style="margin: 0;">Sector: "				
				<s:set  var="sector" value="servicio.id_sector"></s:set>				
				<s:iterator value="sectores">
					<s:if test="%{id_sector == #sector}"><s:property value="nombre"/></s:if>					
				</s:iterator>
			"</h5>
			<h5 style="margin: 0;">Dirigido a: "
			<s:set  var="id_si" value="servicio.id_servicio_informacion"></s:set>				
				<s:iterator value="unionareas">
					<s:if test="%{id_servicio_informacion == #id_si}">
					<s:set  var="area" value="id_area"></s:set>
					<s:iterator value="areas">
						<s:if test="%{id_area == #area}">
							<s:property value="nombre"/>
						</s:if>
						<s:else><s:property value="nombre"/></s:else>
					</s:iterator>
					</s:if>					
				</s:iterator>
			"</h5>
			<h5 style="margin: 0;">Estado del servicio: "
				<s:set  var="estado" value="servicio.id_estado"></s:set>				
				<s:iterator value="estados">
					<s:if test="%{id_estado == #estado}"><s:property value="nombre"/></s:if>					
				</s:iterator>
			"</h5>
			<hr>			
			
			<!-- Tabla en árbol. -->
			<table id="tree" class="treeTable">
				<thead>
					<tr>
						<th colspan="4" style="text-align: center;" >Funcionalidades y datos</th>						
					</tr>
					<tr>						
						<th><s:text name="nombre"></s:text></th>
						<th><s:text name="descipción"></s:text></th>
						<th><s:text name="fecha"></s:text></th>
						<th>Tipo</th>						
					</tr>
				</thead>
				<tbody>
					<s:if test="funcionalidades.size() > 0">
						<s:iterator value="funcionalidades" status="result_Status">
							<tr id="node-<s:property value="%{1 + #result_Status.index}" />">								
								<td><s:property value="nombre" /></td>
								<td><s:property value="descripcion" /></td>
								<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
								<td>Función</td>								
							</tr>							
							<s:set name="id_funcion" value="id_funcionalidad" />
							<s:if test="ios.size() > 0">
								<s:iterator value="ios" status="ios_Status">
									<s:append var="hijos">
										<s:param value="%{ios}" />
									</s:append>									
									<s:if test="id_padre == 0 && tipo == 0">
									<tr id="node-<s:property value="%{((100) + (#ios_Status.index))}" />"
									class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">										
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
										<td>Dato de entrada</td>																		
									</tr>
									</s:if>
									<s:elseif test="%{id_padre > 0 && tipo == 0}">	
										<s:set var="padre" value="id_padre"></s:set>										
										<s:iterator value="hijos" status="hijos_Status">
											<s:if test="%{id_padre == #padre && tipo == 0}">
												<tr id="node-<s:property value="%{( (1000) + (#hijos_Status.index) )}"/>"
													class="child-of-node-<s:property value="%{(100 + #ios_Status.index)-1}"/>">													
													<td><s:property value="nombre" /></td>
													<td><s:property value="descripcion" /></td>
													<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
													<td>Dato de entrada</td>
												</tr>	
											</s:if>
										</s:iterator>										
									</s:elseif>									
								</s:iterator>								
							</s:if>
							<s:if test="ios.size() > 0">
								<s:iterator value="ios" status="ios_Status">
									<s:append var="hijos">
										<s:param value="%{ios}" />
									</s:append>									
									<s:if test="id_padre == 0 && tipo == 1">
									<tr id="node-<s:property value="%{((100) + (#ios_Status.index))}" />"
									class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">										
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
										<td>Dato de salida</td>																		
									</tr>
									</s:if>
									<s:elseif test="%{id_padre > 0 && tipo == 1}">		
										<s:set var="padre" value="id_padre"></s:set>										
										<s:iterator value="hijos" status="hijos_Status">
											<s:if test="%{id_padre == #padre && tipo == 1}">
												<tr id="node-<s:property value="%{( (1000) + (#hijos_Status.index) )}"/>"
													class="child-of-node-<s:property value="%{(100 + #ios_Status.index)-1}"/>">													
													<td><s:property value="nombre" /></td>
													<td><s:property value="descripcion" /></td>
													<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
													<td>Dato de salida</td>
												</tr>	
											</s:if>
										</s:iterator>										
									</s:elseif>									
								</s:iterator>								
							</s:if>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<th colspan="4"><s:text name="servicios.error" /></th>
						</tr>
					</s:else>
				</tbody>
			</table>
			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</s:i18n>
</html>