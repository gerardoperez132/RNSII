<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/userlogin">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
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
				
				<%@include file="../layout/header.jsp"%>
			
				<!-- Esta es la barra lateral -->
				<div id="sidebar">
					<%@include file="../layout/sidebar.jsp"%>
				</div>


				<!-- Este es el div de contenidos -->
				<div id="content">
					
					<table class="main">
						<tr>
							<td style="width: 350px;">
								<h4 style="margin: 0;">
									<s:text name="catalogo" />
								</h4>
							</td>
							<td style="width: 350px;">
								<div style="text-align: right;">
									<s:text name="bienvenido" />
									<s:property value="%{#session.usuario.nombre}" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2"><s:text name="ente" /> <s:property
									value="ente.nombre" /></td>
						</tr>
					</table>
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
					
					<div class="tab_container">
						<!-- Descripción general -->
						<div id="tab1" class="tab_content">							
							
							<h5 style="margin: 0;">
								ID:
								<s:property value="servicio.id_servicio_informacion" />
							</h5>
							<h5 style="margin: 0;">
								Servicio: "
								<s:property value="servicio.nombre" />
								"
							</h5>
							<h5 style="margin: 0;">
								Sector: "
								<s:set var="sector" value="servicio.id_sector"></s:set>
								<s:iterator value="sectores">
									<s:if test="%{id_sector == #sector}">
										<s:property value="nombre" />
									</s:if>
								</s:iterator>
								"
							</h5>
							<h5 style="margin: 0;">
								Dirigido a:
								<s:set var="id_si" value="servicio.id_servicio_informacion"></s:set>
								<s:iterator value="unionareas">
									<s:if test="%{id_servicio_informacion == #id_si}">
										<s:set var="area" value="id_area"></s:set>
										<s:iterator value="areas">
											<s:if test="%{id_area == #area}">
												"<s:property value="nombre" />"
											</s:if>
										</s:iterator>
									</s:if>
								</s:iterator>
								
							</h5>
							<h5 style="margin: 0;">
								Estado del servicio: "
								<s:set var="estado" value="servicio.id_estado"></s:set>
								<s:iterator value="estados">
									<s:if test="%{id_estado == #estado}">
										<s:property value="nombre" />
									</s:if>
								</s:iterator>
								"
							</h5>
							<hr>
						
						</div>
						<!-- Descripción Legal -->
						<div id="tab2" class="tab_content">							
							<table border="1">
								<s:iterator value="files">
									<tr>
										<td><s:property value="nombre" /></td>											
										<td><s:date name="fecha_creado" format="d 'de' MMMM 'del' yyyy" />
										</td>
										<td><a href="..<s:property value='url' />">Descargar</a>
										</td>											
									</tr>
								</s:iterator>
							</table>							
						</div>
						<!-- Descripción Técnica -->
						<div id="tab3" class="tab_content">
							<h5 style="margin: 0;">
								Nivel de Seguridad: "
								<s:set var="seguridad" value="servicio.id_seguridad"></s:set>
								<s:iterator value="niveles">
									<s:if test="%{id_seguridad == #seguridad}">
										<s:property value="nombre" />
									</s:if>
								</s:iterator>
								"
							</h5>
							<h5 style="margin: 0;">
								Arquitectura: 
								<s:set var="id_si" value="servicio.id_servicio_informacion"></s:set>														
								<s:iterator value="unionarquitecturas">									
									<s:if test="%{id_servicio_informacion == #id_si}">
										<s:set var="arquitectura" value="id_arquitectura"></s:set>
										<s:iterator value="arquitecturas">
											<s:if test="%{id_arquitectura == #arquitectura}">
												"<s:property value="nombre" />"  
											</s:if>
										</s:iterator>
									</s:if>
								</s:iterator>
								
							</h5>
							<h5 style="margin: 0;">
								Versión: "
								<s:property value="servicio.version" />
								"
							</h5>
							<h5 style="margin: 0;">
								Nivel de Seguridad: "
								<s:set var="intercambio" value="servicio.id_intercambio"></s:set>
								<s:iterator value="children">
									<s:if test="%{id_intercambio == #intercambio}">
										<s:property value="nombre" />
									</s:if>
								</s:iterator>
								"
							</h5>
						</div>
						<div id="tab4" class="tab_content">
							<h5 style="margin: 0;">
								Responsable del soporte Técnico: "
								<s:property value="servicio.responsable" />
								"
							</h5>
							<h5 style="margin: 0;">
								Teléfono: "
								<s:property value="telefono" />
								"
							</h5>
							<h5 style="margin: 0;">
								Correo: "
								<s:property value="correo" />
								"
							</h5>
						</div>
					</div>
					
					
					
					
					

					<!-- Tabla en árbol. -->
					<table id="tree" class="treeTable">
						<thead>
							<tr>
								<th colspan="4" style="text-align: center;">Funcionalidades
									y datos</th>
							</tr>
							<tr>
								<th><s:text name="nombre"></s:text></th>
								<th><s:text name="descipción"></s:text></th>
								<th><s:text name="fecha"></s:text></th>
								<th>Tipo</th>
							</tr>
						</thead>
						<tbody>
							<s:set name="index_100" value="%{0}" />
							<s:set name="index_1000" value="%{0}" />
							<s:if test="funcionalidades.size() > 0">
								<s:iterator value="funcionalidades" status="result_Status">
									<tr
										id="node-<s:property value="%{1 + #result_Status.index}" />">
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
										<td>Función</td>
									</tr>
									<s:set name="id_funcion" value="id_funcionalidad" />									
									<s:if test="ios.size() > 0">
										<s:iterator value="ios" status="ios_Status">
											<s:append var="list">
													<s:param value="%{ios.get(#ios_Status.index)}" />
											</s:append>
											<s:append var="hijos">
													<s:param value="%{ios.get(#ios_Status.index)}" />
											</s:append>
											
											<s:iterator value="list" status="list_Status">												
												<s:if test="id_padre == 0 && tipo == 0 && #id_funcion == id_funcionalidad">
													<s:set name="index_100" value="%{#index_100 + 1}" />
													<tr
														id="node-<s:property value="%{((100) +  (#index_100))}" />"
														class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
														<td><s:property value="nombre" /> e:<s:property value="#index_100"/></td>
														<td><s:property value="descripcion" /></td>
														<td><s:date name="fecha_creado"
																format="d'/'MM'/'yyyy" /></td>
														<td>Dato de entrada</td>
													</tr>
												</s:if>
												<s:elseif test="id_padre > 0 && tipo == 0 && #id_funcion == id_funcionalidad">
													<s:set var="padre" value="id_padre"></s:set>
													<s:iterator value="hijos" status="hijos_Status">
														<s:if test="%{id_padre > 0 && id_padre == #padre && tipo == 0 && #id_funcion == id_funcionalidad}">
															<s:set name="index_1000" value="%{#index_1000 + 1}" />
															<tr
																id="node-<s:property value="%{((1000) +  (#index_1000))}" />"
																class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																<td><s:property value="nombre" /></td>
																<td><s:property value="descripcion" /></td>
																<td><s:date name="fecha_creado"
																		format="d'/'MM'/'yyyy" /></td>
																<td>Dato de entrada</td>
															</tr>
														</s:if>
													</s:iterator>
												</s:elseif>
											</s:iterator>
										</s:iterator>
									</s:if>
									<s:if test="ios.size() > 0">
										<s:iterator value="ios" status="ios_Status">
											<s:append var="list">
													<s:param value="%{ios.get(#ios_Status.index)}" />
											</s:append>
											<s:append var="hijos">
													<s:param value="%{ios.get(#ios_Status.index)}" />
											</s:append>
											<s:iterator value="list" status="list_Status">												
												<s:if test="id_padre == 0 && tipo == 1 && #id_funcion == id_funcionalidad">
													<s:set name="index_100" value="%{#index_100 + 1}" />
													<tr
														id="node-<s:property value="%{((100) +  (#index_100))}" />"
														class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
														<td><s:property value="nombre" /></td>
														<td><s:property value="descripcion" /></td>
														<td><s:date name="fecha_creado"
																format="d'/'MM'/'yyyy" /></td>
														<td>Dato de salida</td>
													</tr>
												</s:if>
												<s:elseif test="id_padre > 0 && tipo == 1 && #id_funcion == id_funcionalidad">
													<s:set var="padre" value="id_padre"></s:set>
													<s:iterator value="hijos" status="hijos_Status">
														<s:if test="%{id_padre > 0 && id_padre == #padre && tipo == 1 && #id_funcion == id_funcionalidad}">
															<s:set name="index_1000" value="%{#index_1000 + 1}" />
															<tr
																id="node-<s:property value="%{((1000) +  (#index_1000))}" />"
																class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																<td><s:property value="nombre" /></td>
																<td><s:property value="descripcion" /></td>
																<td><s:date name="fecha_creado"
																		format="d'/'MM'/'yyyy" /></td>
																<td>Dato de salida</td>
															</tr>
														</s:if>
													</s:iterator>
												</s:elseif>
											</s:iterator>
										</s:iterator>
									</s:if>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<th colspan="4"><s:text name="funcionalidades.error" /></th>
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