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
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/suscripcion.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/tabs.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function() {
		$("#tree").treeTable();
	});
</script>
<title><s:text name="suscripciones"></s:text></title>
	</head>
	<body>
		<!-- Este es el div contenedor del maquetado de la página -->
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
				<s:if test="buscarServicio==true">
<!--	01) 
Lista de servicios encontrados -->
				<table class="results" style="width:800px;">
					<tr>
						<th colspan="4"><s:text name="listaServiciosEncontrados" />
						</th>
					</tr>
					<tr>
						<th><s:text name="argumentoConsultado" /></th>
						<td colspan="3"><s:property value="cadena" /></td>
					</tr>
					<tr>
						<th><s:text name="nombre" /></th>
						<th><s:text name="ente1" /></th>
						<th><s:text name="fecha_creacion" /></th>
						<th><s:text name="suscripcion" /></th>
					</tr>
					<s:if test="servicios.size()>0">
						<s:iterator value="servicios">
							<tr>
								<td><a
									href="Servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
										<s:property value="nombre" /> </a>
								</td>
								<td><s:set name="id_e" value="id_ente" /> <s:iterator
										value="entes">
										<s:if test="id_ente == #id_e">
											<s:property value="siglas" />
										</s:if>
									</s:iterator>
								</td>
								<td align="center">
									<s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
								</td>
								<td align="center">
									<form action="prepararSuscripcion">
										<s:hidden name="id_servicio" value="%{id_servicio_informacion}" />
										<input type="submit" value="<s:text name="solicitar"/>" />
									</form>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="4"><span class="ok_pass"><s:text
										name="sis_null2" /> </span></td>
						</tr>
					</s:else>
				</table>
			</s:if>
			<s:elseif test="examinarServicio == true">
<!--  02)
Detalles de un servicio de información -->
				<form action="prepararSuscripcion">
					<s:hidden name="id_servicio"
						value="%{servicio.id_servicio_informacion}" />
					<input type="submit" value="<s:text name="solicitud2"/>" />
				</form>
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
						<table class="results" >
							<tr>
								<td class="alt"><s:text name="n_servicio" /></td>
								<td class="alt2"><s:property
										value="servicio.id_servicio_informacion" /></td>
							</tr>
							<tr>
								<td class="alt"><s:text name="ente1" />
								</td>
								<td class="alt2"><s:property value="ente.nombre" />
								</td>
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
										value="servicio.id_servicio_informacion"></s:set> 
										<s:iterator	value="unionarquitecturas">
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
													<s:elseif test='id_padre > 0 && tipo == 0 && #id_funcion == id_funcionalidad'>
														<s:set var="padre" value="id_padre"></s:set>
														<s:iterator value="hijos" status="hijos_Status">
															<s:if test='%{id_padre > 0 && id_padre == #padre && tipo == 0 && #id_funcion == id_funcionalidad}'>
																<s:set name="index_1000" value="%{#index_1000 + 1}" />
																<tr	id="node-<s:property value="%{((1000) +  (#index_1000))}" />"
																	class="child-of-node-<s:property value="%{(100 + #index_100)}"/>">
																	<td>
																		<s:property value="nombre" />
																	</td>
																	<td>
																		<s:property value="descripcion" />
																	</td>
																	<td>
																		<s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
																	</td>
																	<td>
																		<s:text name="dato_entrada" />
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
			<s:elseif test="suscripcion_form">
<!--	03)
Formulario para solicitar una suscripción a un servicio de información -->
				<table class="results">
					<tr>
						<th colspan="2"><s:text name="solicitud3" /></th>
					</tr>
					<tr>
						<th><s:text name="servicio_nombre" /></th>
						<td><s:property value="servicio.nombre" /></td>
					</tr>
					<tr>
						<th><s:text name="ente1" /></th>
						<td><s:property value="ente.nombre" /></td>
					</tr>
				</table>
				<hr>
				<s:fielderror>
					<s:param>error</s:param>
				</s:fielderror>
				<s:if test="!requested">
					<form action="solicitarSuscripcion" method="post"
						name="suscripcionForm">
						<table>
							<tr>
								<td colspan="3">
									<h5 class="requerido">
										<s:text name="requerido" />
									</h5>
								</td>
							</tr>
							<tr>
								<td align="right"><s:text name="nombre_solicitante" /></td>
								<td><s:textfield name="solicitud.solicitante" id="nombre" /></td>
								<td><h5 id="m_nombre" class="requerido">
										*
										<s:fielderror>
											<s:param>solicitante</s:param>
										</s:fielderror>
									</h5></td>
							</tr>
							<tr>
								<td align="right"><s:text name="cargo_solicitante" /></td>
								<td><s:textfield name="solicitud.cargo" id="cargo" /></td>
								<td><h5 id="m_cargo" class="requerido">
										*
										<s:fielderror>
											<s:param>cargo</s:param>
										</s:fielderror>
									</h5></td>
							</tr>
							<tr>
								<td align="right"><s:text name="telefono" /></td>
								<td><s:select name="codigo" list="codigos" /> <s:textfield
										name="solicitud.telefono" id="telefono" size="14"
										maxlength="7" /></td>
								<td><h5 id="m_telefono" class="requerido">
										*
										<s:fielderror>
											<s:param>telefono</s:param>
										</s:fielderror>
									</h5></td>
							</tr>
							<tr>
								<td align="right"><s:text name="correo" /></td>
								<td><s:textfield name="solicitud.correo" id="correo" /></td>
								<td><h5 id="m_correo" class="requerido">
										*
										<s:fielderror>
											<s:param>correo</s:param>
										</s:fielderror>
									</h5></td>
							</tr>
							<tr>
								<td align="right"><s:text name="motivo_solicitud" /></td>
								<td><s:textarea name="solicitud.motivo_solicitante"
										rows="6" cols="19" id="motivo" /></td>
								<td><h5 id="m_motivo" class="requerido">
										*
										<s:fielderror>
											<s:param>motivo</s:param>
										</s:fielderror>
									</h5></td>
							</tr>
							<tr>
								<td colspan="3"><input type="submit"
									value="<s:text name="enviar_solicitud_suscripcion"/>"
									id="enviar_solicitud" /></td>
							</tr>
						</table>
						<s:hidden name="id_servicio"
							value="%{servicio.id_servicio_informacion}" />
					</form>
				</s:if>
				<s:else>							
					<form action="examinarSolicitud" method="post">
						<s:hidden value="%{id_solicitud_suscripcion}" name="id_solicitud_suscripcion"/>
						<s:hidden value="%{true}" name="detalles_respuesta"/>
						<input type="submit" value="<s:text name="detalles"/>">
					</form>						
				</s:else>
			</s:elseif>
			<s:elseif test="ListarSuscricionesPendientes == true">
<!--	04)
Lista de suscriciones pendientes -->
					
					<table class="results" style="width:800px;">
						<tr>
							<th colspan="5">
								<s:text name="listaSolicitudesSuscripcion"/>
							</th>
						</tr>								
						<tr>
							<th><s:text name="servicio_solicitado" /></th>
							<th><s:text name="solicitante" /></th>
							<th><s:text name="fecha_creacion" /></th>
							<th><s:text name="detalles" /></th>
							<th><s:text name="dictamen" /></th>
						</tr>
						<s:if test="solicitudes.size()>0">
						<s:iterator value="solicitudes">
						<tr>
							<td>
								<s:property value="servicio"/>
							</td>
							<td>
								<s:property value="ente"/>
							</td>
							<td align="center">
								<s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
							</td>
							<td>
								<form action="examinarSolicitud" method="post">
									<s:hidden value="%{id_suscripcion}" name="id_solicitud_suscripcion"/>
									<input type="submit" value="<s:text name="detalles"/>">
								</form>										
							</td>
							<td>
								<s:if test="sentencia==0">
									<form action="preparar_AprobarRechasarSuscripcion" method="post">
										<s:hidden value="%{id_suscripcion}" name="id_solicitud_suscripcion"/>
										<input type="submit" value="<s:text name="sentenciar"/>">
									</form>	
								</s:if>
								<s:else>
									<s:if test="sentencia==1"><s:text name="aprobado" /></s:if>
									<s:if test="sentencia==2"><s:text name="rechazado" /></s:if>
								</s:else>																			
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="5">
								<s:text name="solicitudesNull"/>
							</td>
						</tr>
						</s:else>
					</table>							
				</s:elseif>			
				<s:elseif test="detalles_solicitud == true">
<!--	05)
Muestra los detalles de una solicitud de suscripción aprobarRechasar-->
					<table class="results" style="width:800px;">
						<tr>
							<th colspan="2">
								<s:text name="peticion_suscripcion"/>
							</th>
						</tr>
						<tr>
							<td class="alt"><s:text name="n_servicio" /></td>
							<td class="alt2"><s:property value="servicio.id_servicio_informacion" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="servicio_solicitado" /></td>
							<td class="alt2"><s:property value="servicio.nombre" /></td>
						</tr>								
						<tr>
							<th colspan="2">
								<s:text name="datos_solicitud"/>
							</th>
						</tr>
						<tr>
							<td class="alt"><s:text name="ente2" /></td>
							<td class="alt2"><s:property value="ente.nombre" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="nombre_solicitante"/></td>
							<td class="alt2"><s:property value="solicitud.solicitante" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="cargo_solicitante"/></td>
							<td class="alt2"><s:property value="solicitud.cargo" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="telefono"/></td>
							<td class="alt2"><s:property value="solicitud.telefono" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="correo"/></td>
							<td class="alt2"><s:property value="solicitud.correo" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="motivo_solicitud"/></td>
							<td class="alt2"><s:property value="solicitud.motivo_solicitante" /></td>
						</tr>	
						<s:if test="solicitud.sentencia==0">		
						<tr>
							<th colspan="2" >
								<form action="preparar_AprobarRechasarSuscripcion" method="post">
									<s:hidden value="%{solicitud.id_solicitud_suscripcion}" name="id_solicitud_suscripcion"/>
									<input type="submit" value="<s:text name="aceptarRechazarSuscripcion"/>">
								</form>
							</th>
						</tr>
						</s:if>	
						<s:else>
						<tr>
							<th colspan="2">										
								<s:text name="veredicto_solicitud"/>									
							</th>
						</tr>
						<tr>
							<td class="alt"><s:text name="sentencia"/></td>
							<td class="alt2">
								<s:if test="solicitud.sentencia==1"><s:text name="aprobado" /></s:if>
								<s:if test="solicitud.sentencia==2"><s:text name="rechazado" /></s:if>										
							</td>
						</tr>
						<tr>
							<td class="alt"><s:text name="motivo_proveedor"/></td>
							<td class="alt2"><s:property value="solicitud.motivo_proveedor" /></td>
						</tr>								
						<tr>
							<td class="alt"><s:text name="fecha"/></td>
							<td class="alt2"><s:date name="solicitud.fecha_creado" format="d'/'MM'/'yyyy" /></td>
						</tr>								
						</s:else>				
					</table>
					<br>						
				</s:elseif>	
				<s:elseif test="aprobarRechasar == true">
<!--	06)
Formulario para dar el dictamen de una solicitud de suscripción -->
					<form action="AprobarRechasarSuscripcion" method="post" name="dictamenForm">
					<table class="results" style="width:800px;">
						<tr>
							<th colspan="2">
								<s:text name="peticion_suscripcion"/>
							</th>
						</tr>
						<tr>
							<td class="alt"><s:text name="servicio_solicitado" /></td>
							<td class="alt2"><s:property value="servicio.nombre" /></td>
						</tr>							
						<tr>
							<td class="alt"><s:text name="ente2" /></td>
							<td class="alt2"><s:property value="ente.nombre" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="nombre_solicitante"/></td>
							<td class="alt2"><s:property value="solicitud.solicitante" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="motivo_solicitud"/></td>
							<td class="alt2"><s:property value="solicitud.motivo_solicitante" /></td>
						</tr>
					</table>
					<table class="results">
						<tr>
							<th colspan="3">										
								<s:text name="veredicto_solicitud"/>										
							</th>
						</tr>
						<tr>
							<th colspan="3">							
								<h5 class="requerido"><s:text name="requerido"/></h5>
							</th>
						</tr>
						<tr>
							<td class="alt">										
								<s:text name="dictamen"/>
							</td>
							<td class="alt2">
								<input type="radio" name="solicitud.sentencia" value="1"
								<s:if test="solicitud.sentencia == 1">checked</s:if> id="ops1"/>
								<s:property value="sentencia[0]"/>
								<input type="radio" name="solicitud.sentencia" value="2"
								<s:if test="solicitud.sentencia == 2">checked</s:if> id="ops2"/>
								<s:property value="sentencia[1]"/>										
							</td>
							<td class="alt2"><h5 id="m_sentencia" class="requerido">*<s:fielderror><s:param>sentencia</s:param></s:fielderror></h5></td>
						</tr>	
						<tr>
							<td class="alt">										
								<s:text name="motivo_proveedor"/>
							</td>
							<td class="alt2">
								<s:textarea name="solicitud.motivo_proveedor" rows="6" cols="19" id="motivo"/>										
							</td>
							<td class="alt2"><h5 id="m_motivo" class="requerido">*<s:fielderror><s:param>motivo_proveedor</s:param></s:fielderror></h5></td>
						</tr>											
						<tr>
							<th colspan="3">
								<input type="submit" value="<s:text name='enviar'/>" id="sentenciar">
							</th>
						</tr>	
					</table>
					<s:hidden value="%{true}" name="aprobarRechasar"/>
					<s:hidden value="%{solicitud.id_solicitud_suscripcion}" name="id_solicitud_suscripcion"/>
					<s:token name="token" />								
					</form>						
				</s:elseif>	
				<s:elseif test="ListarSuscricionesAceptadasRechazadas == true">
<!--	07)
Lista con las respuestas a las solicitudes de suscripción a servicios de información de otros entes -->
					<table class="results" style="width:800px;">
						<tr>
							<th colspan="5">
								<s:text name="listaSolicitudesRespuesta"/>
							</th>
						</tr>								
						<tr>
							<th><s:text name="servicio_solicitado" /></th>
							<th><s:text name="ente3" /></th>
							<th><s:text name="fecha_creacion" /></th>
							<th><s:text name="sentencia" /></th>									
							<th><s:text name="detalles" /></th>
						</tr>
						<s:if test="solicitudesRespondidas.size() > 0">
						<s:iterator value="solicitudesRespondidas">
						<tr>
							<td <s:if test="leido==false">style="text-transform: uppercase;font-weight: bold;"</s:if>>
								<s:property value="servicio"/>
							</td>
							<td <s:if test="leido==false">style="text-transform: uppercase;font-weight: bold;"</s:if>>
								<s:property value="ente"/>
							</td>
							<td align="center" <s:if test="leido==false">style="text-transform: uppercase;font-weight: bold;"</s:if>>
								<s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
							</td>
							<td <s:if test="leido==false">style="text-transform: uppercase;font-weight: bold;"</s:if>>
								<s:if test="sentencia==1"><s:text name="aprobado" /></s:if>
								<s:if test="sentencia==2"><s:text name="rechazado" /></s:if>																			
							</td>									
							<td <s:if test="leido==false">style="text-transform: uppercase;font-weight: bold;"</s:if>>
								<form action="examinarSolicitud" method="post">
									<s:hidden value="%{id_suscripcion}" name="id_solicitud_suscripcion"/>
									<s:hidden value="%{true}" name="detalles_respuesta"/>
									<input type="submit" value="<s:text name="detalles"/>">
								</form>	
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr>
							<td colspan="5">
								<s:text name="solicitudesRespondidasNull"></s:text>
							</td>
						</tr>	
						</s:else>
					</table>						
				</s:elseif>	
				<s:elseif test="detalles_respuesta == true">
<!--	08)
Muestra los detalles de una respuesta a una solicitud de suscripción-->
					<table class="results" style="width:800px;">
						<tr>
							<th colspan="2">
								<s:text name="detallesRespuestaSolicitud"/>
							</th>
						</tr>
						<tr>
							<td class="alt"><s:text name="n_servicio" /></td>
							<td class="alt2"><s:property value="servicio.id_servicio_informacion" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="servicio_solicitado" /></td>
							<td class="alt2"><s:property value="servicio.nombre" /></td>
						</tr>								
						<tr>
							<td class="alt"><s:text name="ente3" /></td>
							<td class="alt2"><s:property value="ente.nombre" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="fecha_respuesta" /></td>
							<td class="alt2"><s:date name="solicitud.fecha_modificado" format="d'/'MM'/'yyyy" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="dictamen" /></td>
							<td class="alt2">
								<s:if test="solicitud.sentencia==1"><s:text name="aprobado" /></s:if>
								<s:if test="solicitud.sentencia==2"><s:text name="rechazado" /></s:if>
							</td>
						</tr>								
						<tr>
							<td class="alt"><s:text name="motivo_proveedor"/></td>
							<td class="alt2"><s:property value="solicitud.motivo_proveedor" /></td>
						</tr>
						<tr>
							<th colspan="2">
								<s:text name="datos_solicitud"/>
							</th>
						</tr>								
						<tr>
							<td class="alt"><s:text name="nombre_solicitante"/></td>
							<td class="alt2"><s:property value="solicitud.solicitante" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="cargo_solicitante"/></td>
							<td class="alt2"><s:property value="solicitud.cargo" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="fecha_creacion"/></td>
							<td class="alt2"><s:date name="solicitud.fecha_creado" format="d'/'MM'/'yyyy" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="telefono"/></td>
							<td class="alt2"><s:property value="solicitud.telefono" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="correo"/></td>
							<td class="alt2"><s:property value="solicitud.correo" /></td>
						</tr>
						<tr>
							<td class="alt"><s:text name="motivo_solicitud"/></td>
							<td class="alt2"><s:property value="solicitud.motivo_solicitante" /></td>
						</tr>													
					</table>
					<br>						
				</s:elseif>
				<s:elseif test="solicitarSuscripcion == true">
<!--	09)
Muestra las opciones de busqueda para encontrar servicios de información-->
					<span class="ok_pass">
						<s:text name="solicitud4"/>	
					</span>						
				</s:elseif>
				<s:else>
					<s:fielderror>
						<s:param>error</s:param>
					</s:fielderror>
					<span class="ok_pass">
						<s:actionmessage/>
					</span>
				</s:else>
			</div>
			</div>
			<div style="clear: both"></div>
			<div class="vacio"></div>
			<%@include file="../layout/footer_ge.jsp"%>	
	</body>
</s:i18n>
</html>