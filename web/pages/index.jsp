<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/I18">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css"
	href="/SRSI/pages/res/css/styles.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
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
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="layout/header.jsp"%>
				<%@include file="layout/sidebar_user_final.jsp"%>
				<!-- Este es el div de contenidos -->
				<div id="content">
					<form action="buscar_servicio" method="post">
						<table align="center">
							<tr>
								<td><s:textfield name="cadena" size="50" /></td>
								<td><input type="submit"
									value="<s:text name="buscarServicio"/>"></td>
							</tr>
						</table>
					</form>
					<br>
					<s:if test="consulta_SIxSector == true">
						<!-- Lista de servicios de información por sector -->
						<table class="results">
							<tr>
								<th colspan="3"><s:text name="listarSector" /><span
									style="color: blue;">"<s:property value="sector.nombre" />"</span>
								</th>
							</tr>
							<tr>
								<th><s:text name="nombre" /></th>
								<th><s:text name="ente1" /></th>
								<th><s:text name="fecha_creacion" /></th>
							</tr>

							<s:if test="servicios.size() > 0">
								<s:iterator value="servicios">
									<tr>
										<td><a
											href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
												<s:property value="nombre" /> </a>
										</td>
										<td><s:set name="id_e" value="id_ente" /> <s:iterator
												value="entes">
												<s:if test="id_ente == #id_e">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="3"><s:text name="sis_null" /></td>
								</tr>

							</s:else>
						</table>
					</s:if>
					<s:elseif test="consulta_listarSectores">
						<!-- Lista de sectores con la cantidad servicios publicados -->
						<table class="results">
							<tr>
								<th colspan="2"><s:text name="listarSectores" />
								</th>
							</tr>
							<tr>
								<th><s:text name="nombre" />
								</th>
								<th><s:text name="numero_si" />
								</th>
							</tr>
							<s:iterator value="listaSectores2" status="index">
								<tr>
									<td><a
										href="listarSector?id_sector=<s:property value="id_sector"/>">
											<s:property value="nombre" /> </a>
									</td>
									<td align="center"><s:property value="n" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</s:elseif>
					<s:elseif test="examinarServicio == true">
						<!-- Detalles de un servicio de información -->
						<hr>
						<ul class="tabs">
							<li><a href="#tab1"><s:text name="tab1.title"></s:text>
							</a>
							</li>
							<li><a href="#tab2"><s:text name="tab2.title"></s:text>
							</a>
							</li>
							<li><a href="#tab3"><s:text name="tab3.title"></s:text>
							</a>
							</li>
							<li><a href="#tab4"><s:text name="tab4.title"></s:text>
							</a>
							</li>
						</ul>
						<div class="tab_container">
							<!-- Descripción general -->
							<div id="tab1" class="tab_content">
								<table class="results">
									<tr>
										<td class="alt"><s:text name="n_servicio" /></td>
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
											<th colspan="3"><s:text name="files.empty" /></th>
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
					</s:elseif>
					<s:elseif test="consulta_listarServicios == true">
						<!-- Lista completa de todos los servicios públicados -->
						<table class="results">
							<tr>
								<th colspan="4"><s:text name="listaServicios" /></th>
							</tr>
							<tr>
								<th><s:text name="n_servicio" /></th>
								<th><s:text name="nombre" /></th>
								<th><s:text name="ente1" /></th>
								<th><s:text name="fecha_creacion" /></th>
							</tr>
							<s:if test="servicios.size()>0">
								<s:iterator value="servicios">
									<tr>
										<td align="center"><a
											href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
												<s:property value="id_servicio_informacion" /> </a>
										</td>
										<td><a
											href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
												<s:property value="nombre" /> </a>
										</td>
										<td><s:set name="id_e" value="id_ente" /> <s:iterator
												value="entes">
												<s:if test="id_ente == #id_e">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="4"><s:text name="sis_null" /></td>
								</tr>
							</s:else>
						</table>
					</s:elseif>
					<s:elseif test="buscarServicio == true">
						<!-- Lista de servicios encontrados -->
						<table class="results">
							<tr>
								<th colspan="3"><s:text name="listaServiciosEncontrados" />
								</th>
							</tr>
							<tr>
								<th><s:text name="argumentoConsultado" /></th>
								<td colspan="2"><s:property value="cadena" /></td>
							</tr>
							<tr>
								<th><s:text name="nombre" /></th>
								<th><s:text name="ente1" /></th>
								<th><s:text name="fecha_creado" /></th>
							</tr>
							<s:if test="servicios.size()>0">
								<s:iterator value="servicios">
									<tr>
										<td><a
											href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
												<s:property value="nombre" /> </a>
										</td>
										<td><s:set name="id_e" value="id_ente" /> <s:iterator
												value="entes">
												<s:if test="id_ente == #id_e">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="3"><span class="ok_pass"><s:text
												name="sis_null2" /> </span></td>
								</tr>
							</s:else>
						</table>
					</s:elseif>
					<s:else>
						<s:fielderror>
							<s:param>error</s:param>
						</s:fielderror>
					</s:else>

				</div>
				<%@include file="layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>