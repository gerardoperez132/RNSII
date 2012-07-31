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
<script type="text/javascript" src="res/js/tabs.js"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tree").treeTable();
	});
</script>
<title><s:text name="inicio" />
</title>
	</head>
<body>	
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
								<table class="results">
									<tr>
										<td class="alt"><s:text name="n_servicio"/></td>
										<td class="alt2"><s:property
												value="servicio.id_servicio_informacion" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="servicio_nombre" /></td>
										<td class="alt2"><s:property value="servicio.nombre" />
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="descripcion" /></td>
										<td class="alt2"><s:property value="servicio.descripcion" />
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="sector" /></td>
										<td class="alt2"><s:set var="sector"
												value="servicio.id_sector"></s:set> <s:iterator
												value="sectores">
												<s:if test="%{id_sector == #sector}">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="orientado" /></td>
										<td class="alt2"><s:set var="id_si"
												value="servicio.id_servicio_informacion"></s:set> <s:iterator
												value="unionareas">
												<s:if test="%{id_servicio_informacion == #id_si}">
													<s:set var="area" value="id_area"></s:set>
													<s:iterator value="areas">
														<s:if test="%{id_area == #area}">
															<s:property value="nombre" />
														</s:if>
													</s:iterator>
												</s:if>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="estado" /></td>
										<td class="alt2"><s:set var="estado"
												value="servicio.id_estado"></s:set> <s:iterator
												value="estados">
												<s:if test="%{id_estado == #estado}">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="visitas" /></td>
										<td class="alt2"><s:property value="nVisitas" /></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td>
											<form action="prepararModificarServicioInformacion"
												method="POST">
												<s:hidden name="id_servicio_informacion"
													value="%{servicio.id_servicio_informacion}"></s:hidden>
												<input type="submit" value="<s:text name="modificar"/>" />
											</form></td>
										<td>
											<form action="prepararFuncionalidad" method="POST">
												<s:hidden name="id_servicio_informacion"></s:hidden>
												<input type="submit"
													value="<s:text name="funcionalidad.add"/>" />
											</form></td>
									</tr>
								</table>
							</div>
							<!-- Descripción Legal -->
							<div id="tab2" class="tab_content">
								<table class="tb">
									<tr>
										<th class="tb_th"><s:text name="nombre" /></th>
										<th class="tb_th"><s:text name="fecha" /></th>
										<th class="tb_th"><s:text name="descargar" /></th>
									</tr>
									<s:if test="files.size()>0">
										<s:iterator value="files">
											<tr>
												<td class="tb_td"><s:property value="nombre" />
												</td>
												<td class="tb_td"><s:date name="fecha_creado"
														format="d'/'MM'/'yyyy" /></td>
												<td class="tb_td"><a
													href="..<s:property value='url' />"><s:text
															name="descargar" /> </a></td>
											</tr>
										</s:iterator>
									</s:if>
									<s:else>
										<tr>
											<td colspan="3"><s:text name="files.empty" /></td>
										</tr>
									</s:else>
								</table>
							</div>
							<!-- Descripción Técnica -->
							<div id="tab3" class="tab_content">

								<table class="results">
									<tr>
										<td class="alt"><s:text name="seguridad" /></td>
										<td class="alt2"><s:set var="seguridad"
												value="servicio.id_seguridad"></s:set> <s:iterator
												value="niveles">
												<s:if test="%{id_seguridad == #seguridad}">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="arquitectura" /></td>
										<td class="alt2"><s:set var="id_si"
												value="servicio.id_servicio_informacion"></s:set> <s:iterator
												value="unionarquitecturas">
												<s:if test="%{id_servicio_informacion == #id_si}">
													<s:set var="arquitectura" value="id_arquitectura"></s:set>
													<s:iterator value="arquitecturas">
														<s:if test="%{id_arquitectura == #arquitectura}">
															<s:property value="nombre" />
														</s:if>
													</s:iterator>
												</s:if>
											</s:iterator>
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="version" /></td>
										<td class="alt2"><s:property value="servicio.version" />
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="intercambio" /></td>
										<td class="alt2"><s:set var="intercambio"
												value="servicio.id_intercambio"></s:set> <s:iterator
												value="children">
												<s:if test="%{id_intercambio == #intercambio}">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
									</tr>
								</table>
								<hr>
								<!-- Tabla en árbol. -->
								<table id="tree" class="treeTable">
									<thead>
										<tr>
											<th colspan="4" style="text-align: center;"><s:text
													name="funcionalidades_&_datos" /></th>
										</tr>
										<tr>
											<th><s:text name="nombre"></s:text>
											</th>
											<th><s:text name="descripcion"></s:text>
											</th>
											<th><s:text name="fecha"></s:text>
											</th>
											<th><s:text name="tipo" />
											</th>
										</tr>
									</thead>
									<tbody>
										<s:set name="index_100" value="%{0}" />
										<s:set name="index_1000" value="%{0}" />
										<s:if test="funcionalidades.size() > 0">
											<s:iterator value="funcionalidades" status="result_Status">
												<tr
													id="node-<s:property value="%{1 + #result_Status.index}" />">
													<td><s:property value="nombre" />
													</td>
													<td><s:property value="descripcion" />
													</td>
													<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
													</td>
													<td><s:text name="funcion" />
													</td>
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
															<s:if
																test="id_padre == 0 && tipo == 0 && #id_funcion == id_funcionalidad">
																<s:set name="index_100" value="%{#index_100 + 1}" />
																<tr
																	id="node-<s:property value="%{((100) +  (#index_100))}" />"
																	class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
																	<td><s:property value="nombre" />
																	</td>
																	<td><s:property value="descripcion" />
																	</td>
																	<td><s:date name="fecha_creado"
																			format="d'/'MM'/'yyyy" />
																	</td>
																	<td><s:text name="dato_entrada" />
																	</td>
																</tr>
															</s:if>
															<s:elseif
																test="id_padre > 0 && tipo == 0 && #id_funcion == id_funcionalidad">
																<s:set var="padre" value="id_padre"></s:set>
																<s:iterator value="hijos" status="hijos_Status">
																	<s:if
																		test="%{id_padre > 0 && id_padre == #padre && tipo == 0 && #id_funcion == id_funcionalidad}">
																		<s:set name="index_1000" value="%{#index_1000 + 1}" />
																		<tr
																			id="node-<s:property value="%{((1000) +  (#index_1000))}" />"
																			class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																			<td><s:property value="nombre" />
																			</td>
																			<td><s:property value="descripcion" />
																			</td>
																			<td><s:date name="fecha_creado"
																					format="d'/'MM'/'yyyy" />
																			</td>
																			<td><s:text name="dato_entrada" />
																			</td>
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
															<s:if
																test="id_padre == 0 && tipo == 1 && #id_funcion == id_funcionalidad">
																<s:set name="index_100" value="%{#index_100 + 1}" />
																<tr
																	id="node-<s:property value="%{((100) +  (#index_100))}" />"
																	class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
																	<td><s:property value="nombre" />
																	</td>
																	<td><s:property value="descripcion" />
																	</td>
																	<td><s:date name="fecha_creado"
																			format="d'/'MM'/'yyyy" />
																	</td>
																	<td><s:text name="dato_salida" />
																	</td>
																</tr>
															</s:if>
															<s:elseif
																test="id_padre > 0 && tipo == 1 && #id_funcion == id_funcionalidad">
																<s:set var="padre" value="id_padre"></s:set>
																<s:iterator value="hijos" status="hijos_Status">
																	<s:if
																		test="%{id_padre > 0 && id_padre == #padre && tipo == 1 && #id_funcion == id_funcionalidad}">
																		<s:set name="index_1000" value="%{#index_1000 + 1}" />
																		<tr
																			id="node-<s:property value="%{((1000) +  (#index_1000))}" />"
																			class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																			<td><s:property value="nombre" />
																			</td>
																			<td><s:property value="descripcion" />
																			</td>
																			<td><s:date name="fecha_creado"
																					format="d'/'MM'/'yyyy" />
																			</td>
																			<td><s:text name="dato_salida" />
																			</td>
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
												<th colspan="4"><s:text name="funcionalidades.error" />
												</th>
											</tr>
										</s:else>
									</tbody>
								</table>
							</div>
							<div id="tab4" class="tab_content">
								<table class="results">
									<tr>
										<td class="alt"><s:text name="responsable" /></td>
										<td class="alt2"><s:property value="servicio.responsable" />
										</td>
									</tr>
									<tr>
										<td class="alt"><s:text name="telefono" /></td>
										<td class="alt2"><s:property value="telefono" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="correo" /></td>
										<td class="alt2"><s:property value="correo" /></td>
									</tr>
								</table>
							</div>
						</div>
				</div>
			</div>
		<div style="clear: both"></div>
	<div class="vacio"></div>
	<%@include file="../layout/footer_ge.jsp"%>
	</body>
</s:i18n>
</html>


							