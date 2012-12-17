<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/messages">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!-- CSS (required) -->
		<link rel="stylesheet" type="text/css" href="res/css/style2.css">
		<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
		<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
		<link rel="stylesheet" type="text/css" href="res/css/table2.css">
		<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
		<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
		<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
		<!-- JS (required) -->
		<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/tabs.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/funciones_ge.js" charset="UTF-8"></script>
		
		<%@include file="layout/header_joomla.jsp"%>
		
		<title><s:text name="inicio" /></title>
	</head>
	<body class="bg clearfix">
		<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="layout/menus.jsp"%>
		<!-- Este es el div de contenidos -->
		<div id="content">
			<div class="main">
					<h1><a><s:text name="titulo2" /></a></h1>
					<s:if test="consulta_SIxSector">
						<!-- Lista de servicios de información por sector -->
						<div id="adminForm">
						<table class="category">
							<thead>
								<tr>
									<th class="list-title" colspan="3" id="tableOrdering1"><s:text name="listarSector" />
										<span style="color: #A1C7D0;">"<s:property value="sector.nombre" />"</span>
									</th>
								</tr>
								<tr>
									<th class="list-title" id="tableOrdering"><s:text name="nombre" /></th>
									<th class="list-title" id="tableOrdering"><s:text name="ente1" /></th>
									<th class="list-title" id="tableOrdering"><s:text name="fecha" /></th>
								</tr>
							</thead>
							<s:if test="servicios.size() > 0">
								<s:iterator value="servicios">
								<tbody>
									<tr class="cat-list-row0">
										<td class="list-title">
											<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
												<s:property value="nombre" /></a>
										</td>
										<td class="list-title">
											<s:set name="id_e" value="id_ente" />
											<s:iterator value="entes">
												<s:if test="id_ente == #id_e">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td class="list-title">
											<s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
										</td>
									</tr>
								</tbody>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="3"><s:text name="sis_null" /></td>
								</tr>
							</s:else>
						</table>
						</div>
					</s:if>
					<s:elseif test="consulta_listarSectores">
						<!-- Lista de sectores con la cantidad servicios publicados -->
						<div id="adminForm">
						<table class="category">
							<thead>
								<tr>
									<th colspan="2" class="list-title" id="tableOrdering1"><s:text name="listarSectores" /></th>
								</tr>
								<tr>
									<th class="list-title" id="tableOrdering"><s:text name="nombre" /></th>
									<th class="list-title" id="tableOrdering4"><s:text name="numero_si" /></th>
								</tr>
							</thead>
							<s:iterator value="listaSectores2" status="index">
								<tbody>
									<tr class="cat-list-row0">
										<td class="list-title"><a
											href="listarSector?id_sector=<s:property value="id_sector"/>">
												<s:property value="nombre" />
										</a></td>
										<td class="list-title"><s:property value="n" /></td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						</div>
					</s:elseif>
					<s:elseif test="examinarServicio">
						<!-- Detalles de un servicio de información -->
						<ul class="tabs">
							<li><a style="font-size: 1em;" href="#tab1"><s:text name="tab1.title"/></a></li>
							<li><a style="font-size: 1em;" href="#tab2"><s:text name="tab2.title"/></a></li>
							<li><a style="font-size: 1em;" href="#tab3"><s:text name="tab3.title"/></a></li>
							<li id="t4"><a style="font-size: 1em;" href="#tab4"><s:text name="tab4.title"/></a></li>
						</ul>
						<div class="tab_container">
							<!-- Descripción general -->
							<div id="tab1" class="tab_content">
								<table class="results">
									<tr>
										<td class="alt"><s:text name="n_servicio" /></td>
										<td class="alt2"><s:property value="servicio.id_servicio_informacion" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="ente1" /></td>
										<td class="alt2"><s:property value="ente.nombre" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="servicio_nombre" /></td>
										<td class="alt2"><s:property value="servicio.nombre" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="descripcion" /></td>
										<td class="alt2"><s:property value="servicio.descripcion" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="sector" /></td>
										<td class="alt2"><s:set var="sector" value="servicio.id_sector"></s:set>
										<s:iterator value="sectores">
											<s:if test="%{id_sector == #sector}">
												<s:property value="nombre" />
											</s:if>
										</s:iterator></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="orientado" /></td>
										<td class="alt2"><s:set var="id_si" value="servicio.id_servicio_informacion"></s:set>
										<s:iterator	value="unionareas">
											<s:if test="%{id_servicio_informacion == #id_si}">
												<s:set var="area" value="id_area"/>
												<s:iterator value="areas">
													<s:if test="%{id_area == #area}">
														<s:property value="nombre" />
													</s:if>
												</s:iterator>
											</s:if>
										</s:iterator></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="estado" /></td>
										<td class="alt2"><s:set var="estado" value="servicio.id_estado"></s:set>
										<s:iterator	value="estados">
											<s:if test="%{id_estado == #estado}">
												<s:property value="nombre" />
											</s:if>
										</s:iterator></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="visitas" /></td>
										<td class="alt2"><s:property value="nVisitas" /></td>
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
												<td class="tb_td"><s:property value="nombre" /></td>
												<td class="tb_td"><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
												<td class="tb_td"><a href="..<s:property value='url' />"><s:text name="descargar" /></a></td>
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
										<td class="alt2"><s:set var="seguridad" value="servicio.id_seguridad"/>
										<s:iterator	value="niveles">
											<s:if test="%{id_seguridad == #seguridad}">
												<s:property value="nombre" />
											</s:if>
										</s:iterator></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="arquitectura" /></td>
										<td class="alt2"><s:set var="id_si"	value="servicio.id_servicio_informacion"/>
										<s:iterator	value="unionarquitecturas">
											<s:if test="%{id_servicio_informacion == #id_si}">
												<s:set var="arquitectura" value="id_arquitectura"/>
												<s:iterator value="arquitecturas">
													<s:if test="%{id_arquitectura == #arquitectura}">
														<s:property value="nombre" />
													</s:if>
												</s:iterator>
											</s:if>
										</s:iterator></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="version" /></td>
										<td class="alt2"><s:property value="servicio.version" /></td>
									</tr>
									<tr>
										<td class="alt"><s:text name="intercambio" /></td>
										<td class="alt2"><s:set var="intercambio" value="servicio.id_intercambio"/>
										<s:iterator	value="children">
											<s:if test="%{id_intercambio == #intercambio}">
												<s:property value="nombre" />
											</s:if>
										</s:iterator></td>
									</tr>
								</table>
								<hr>
								<!-- Tabla en árbol. -->
								<table id="tree" class="treeTable results">
									<thead>
										<tr>
											<th colspan="4" style="text-align: center;">
											<s:text name="funcionalidades_&_datos" /></th>
										</tr>
										<tr>
											<th><s:text name="nombre"/></th>
											<th><s:text name="descripcion"/></th>
											<th><s:text name="fecha"/></th>
											<th><s:text name="tipo" /></th>
										</tr>
									</thead>
									<tbody>
										<s:set name="index_100" value="%{0}" />
										<s:set name="index_1000" value="%{0}" />
										<s:if test="funcionalidades.size() > 0">
											<s:iterator value="funcionalidades" status="result_Status">
												<tr	id="node-<s:property value="%{1 + #result_Status.index}" />">
													<td><s:property value="nombre" /></td>
													<td><s:property value="descripcion" /></td>
													<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
													<td><s:text name="funcionalidad" /></td>
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
																<tr id="node-<s:property value="%{((100) +  (#index_100))}" />" class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
																	<td><s:property value="nombre" /></td>
																	<td><s:property value="descripcion" /></td>
																	<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
																	<td><s:text name="dato_entrada" /></td>
																</tr>
															</s:if>
															<s:elseif test='id_padre > 0 && tipo == 0 && #id_funcion == id_funcionalidad'>
																<s:set var="padre" value="id_padre"/>
																<s:iterator value="hijos" status="hijos_Status">
																	<s:if test="%{id_padre > 0 && id_padre == #padre && tipo == 0 && #id_funcion == id_funcionalidad}">
																		<s:set name="index_1000" value="%{#index_1000 + 1}" />
																		<tr id="node-<s:property value="%{((1000) +  (#index_1000))}" />" class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																			<td><s:property value="nombre" /></td>
																			<td><s:property value="descripcion" /></td>
																			<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
																			<td><s:text name="dato_entrada" /></td>
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
																<tr id="node-<s:property value="%{((100) +  (#index_100))}" />" class="child-of-node-<s:property value="%{1 + #result_Status.index}" />">
																	<td><s:property value="nombre" /></td>
																	<td><s:property value="descripcion" /></td>
																	<td><s:date name="fecha_creado"	format="d'/'MM'/'yyyy" /></td>
																	<td><s:text name="dato_salida" /></td>
																</tr>
															</s:if>
															<s:elseif test="id_padre > 0 && tipo == 1 && #id_funcion == id_funcionalidad">
																<s:set var="padre" value="id_padre"/>
																<s:iterator value="hijos" status="hijos_Status">
																	<s:if test='%{id_padre > 0 && id_padre == #padre && tipo == 1 && #id_funcion == id_funcionalidad}'>
																		<s:set name="index_1000" value="%{#index_1000 + 1}" />
																		<tr id="node-<s:property value="%{((1000) +  (#index_1000))}" />" class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																			<td><s:property value="nombre" /></td>
																			<td><s:property value="descripcion" /></td>
																			<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
																			<td><s:text name="dato_salida" /></td>
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
												<td colspan="4"><s:text name="funcionalidades.error" /></td>
											</tr>
										</s:else>
									</tbody>
								</table>
							</div>
							<!-- FORMULARIO DE CONTACTO -->
							<div id="tab4" class="tab_content">
								<div style="display: none;" id="form_tab4_errors"><s:property value="%{#session.errors.size()}"/></div>
								<form action="contact" method="POST">
								<table>
									<tr>
										<td class="width_230">
											<h5 class="formulario">
												<s:text name="nombre"/>
											</h5>
											<div class="error">
												<s:property value="#session.errors.get(0)"/>
											</div>
											<s:textfield name="name" value="%{#session.name}"/>
										</td>
										<td class="width_230">
											<h5 class="formulario">
												<s:text name="contact.email"/>
											</h5>
											<div class="error">
												<s:property value="#session.errors.get(1)"/>
											</div>
											<s:textfield name="email" value="%{#session.email}"/>										
										</td>
									</tr>
									<tr>
										<td class="width_230">
											<h5 class="formulario">
												<s:text name="contact.subject"/>
											</h5>
											<div class="error">
												<s:property value="#session.errors.get(2)"/>
											</div>
											<s:textfield name="subject" value="%{#session.subject}"/></td>
										<td class="width_230" rowspan="2">
											<h5 class="formulario">
												<s:text name="contact.message"/>
											</h5>
											<div class="error">
												<s:property value="#session.errors.get(3)"/>
											</div>
											<s:textarea name="message" value="%{#session.message}" cols="30" rows="5"/>
										</td>
									</tr>
									<tr>
										<td class="width_230">
											<h5 class="formulario">
												<s:text name="captcha"/>
											</h5>
											<div class="error">
											<s:property value="#session.errors.get(4)"/>
											</div>
											<input type="text" name="captcha">
										</td>																				
									</tr>
									<tr>
										<td class="width_230">
											<img src="getCaptcha" id="captcha">											
										</td>
										<td class="width_230">
											<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
											<input type="submit">
										</td>
									</tr>									
								</table>
								</form>
							</div>
						</div>
					</s:elseif>
					<s:elseif test="consulta_listarServicios">
						<!-- Lista completa de todos los servicios publicados -->
						<div id="adminForm">
						<table class="category">
							<thead>
							<tr>
								<th colspan="4" class="list-title" id="tableOrdering1"><s:text name="listaServicios" /></th>
							</tr>
							<tr>
								<th class="list-title" id="tableOrdering"><s:text name="n_servicio" /></th>
								<th class="list-title" id="tableOrdering"><s:text name="nombre" /></th>
								<th class="list-title" id="tableOrdering"><s:text name="ente1" /></th>
								<th class="list-title" id="tableOrdering"><s:text name="fecha" /></th>
							</tr>
							</thead>
							<s:if test="servicios.size()>0">
								<s:iterator value="servicios">
								<tbody>
									<tr class="cat-list-row0">
										<td align="center" class="list-title">
											<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>"><s:property value="id_servicio_informacion" /></a>
										</td>
										<td class="list-title">
											<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>"><s:property value="nombre" /></a>
										</td>
										<td class="list-hits"><s:set name="id_e" value="id_ente" />
										<s:iterator value="entes">
											<s:if test="id_ente == #id_e">
												<s:property value="nombre" />
											</s:if>
										</s:iterator></td>
										<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
										</td>
									</tr>
								</tbody>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="4"><s:text name="sis_null" /></td>
								</tr>
							</s:else>
						</table>
						</div>
					</s:elseif>
					<s:elseif test="buscarServicio">
						<!-- Lista de servicios encontrados -->
						<div id="adminForm">
						<table class="category">
							<thead>
								<tr>
									<th colspan="3" class="list-title" id="tableOrdering1"><s:text name="listaServiciosEncontrados" /></th>
								</tr>
								<tr>
									<th class="list-title" id="tableOrdering2"><s:text name="argumentoConsultado" /></th>
									<td colspan="2"><span style="color: #A1C7D0">"<s:property value="cadena" />"</span></td>
								</tr>
								<tr>
									<th class="list-title" id="tableOrdering"><s:text name="nombre" /></th>
									<th class="list-title" id="tableOrdering"><s:text name="ente1" /></th>
									<th class="list-title" id="tableOrdering"><s:text name="fecha" /></th>
								</tr>
							</thead>
							<s:if test="servicios.size()>0">
								<s:iterator value="servicios">
								<tbody>
									<tr class="cat-list-row0">
										<td class="list-title">
											<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>"><s:property value="nombre" /></a>
										</td>
										<td class="list-title">
											<s:set name="id_e" value="id_ente" />
											<s:iterator value="entes">
												<s:if test="id_ente == #id_e">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td class="list-title"><s:date name="fecha_creado" format="d'/'MM'/'yyyy" /></td>
									</tr>
								</tbody>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="3"><span class="ok_pass"><s:text name="sis_null2" /></span></td>
								</tr>
							</s:else>
						</table>
						</div>
					</s:elseif>
					<s:elseif test="error404">
						<h1 style="color: red">
							<s:text name="404"/>
						</h1>
						<h4>
							<s:text name="pageNotFound"/>
						</h4>
					</s:elseif>
					<s:else>						
						<div id="down">
							<div class="tab01">
								<div class="tab02">
									<span class="tab03"><s:text name="sectores" /></span>
								</div>
							</div>
							<div class="middleContent">
								<ul class='thematics'>
									<s:iterator value="listaSectores2">
										<li><s:set name="url" value="%{'res/img/sector/logo_sector'+id_sector+'.png'}" />
											<a style="background:url(<s:property value="#url"/>);background-repeat: no-repeat;" href="listarSector?id_sector=<s:property value="id_sector"/>">
												<s:property value="nombre" /> (<s:property value="n" />)
											</a>
										</li>
									</s:iterator>
								</ul>
								<%@include file="pagination.jsp" %>
							</div>
						</div>
					</s:else>
				</div>
			</div>
		<%@include file="layout/sidebar_user_final.jsp"%>		
		<div class="clr"></div>
		<%@include file="layout/breadcrumbs.jsp"%>	
		<%@include file="layout/links_over_footer.jsp"%>	
		</div>		
		</div>		
		<!-- Footer -->	
		<%@include file="layout/footer.jsp"%>
	</body>
</s:i18n>
</html>
