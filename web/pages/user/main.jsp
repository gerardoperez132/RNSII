<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/messages"><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/SRSI/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js"	charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"	charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<title><s:text name="inicio" /></title>
	</head>
	<body class="bg clearfix">
		<div class="container">
			
			<!-- Esta es la barra lateral -->
			<%@include file="../layout/sidebar.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">				
				<%@include file="../layout/bienvenido.jsp"%>
				<s:if test="peticionesNoLeidas > 0 || peticionesPendientes > 0">
					<!-- Peticiones de suscripción no leídas y pendientes   -->
					<s:if test="peticionesNoLeidas > 0">
						<span style="color: blue;">
							<s:text name="solicitudes_no_leidas">
								<s:param>
									<s:property value="peticionesNoLeidas" />
								</s:param>
							</s:text>
						</span>
						<br>
					</s:if>
					<s:if test="peticionesPendientes > 0">
						<span style="color: blue;">
							<s:text name="solicitudes_pendientes">
								<s:param>
									<s:property value="peticionesPendientes" />
								</s:param>
							</s:text>
						</span>
					</s:if>
					<form action="ListarSuscricionesPendientes" method="post">
						<table align="center">
							<tr>
							<!-- ADVERTENCIA => CONTENIDO NO INTERNACIONALIZADO. -->
								<td><input type="submit" value="Ir a solicitudes de suscripción"></td>
							</tr>
						</table>
					</form>
					<hr>
				</s:if>
				<s:if test="solicitudesAceptadasRechazadas > 0">
					<!-- Número de solicitudes aceptadas no leídas -->
					<form action="listarSolicitudesAceptadasRechazadas" method="post">
						<table>
							<tr>
								<td><s:if test="solicitudesAceptadasRechazadas == 1">
								<!-- ADVERTENCIA => CONTENIDO NO INTERNACIONALIZADO. -->
										<span style="color: blue;">Hay una respuesta a una
											solicitud de suscripción</span>
									</s:if>
									<s:else>
										<span style="color: blue;">Hay <s:property
												value="solicitudesAceptadasRechazadas" /> respuestas a
											solicitudes de suscripción
										</span>
									</s:else>
								</td>
								<!-- ADVERTENCIA => CONTENIDO NO INTERNACIONALIZADO. -->
								<td><input type="submit" value="ver detalles"></td>
							</tr>
						</table>
					</form>
					<hr>
				</s:if>
				<!-- Tabla en árbol. -->
				<table class="results_width_user results">
					<tbody>
						<tr>
						<!-- ADVERTENCIA => CONTENIDO NO INTERNACIONALIZADO -->
							<th colspan="5" style="text-align: center;">Servicios de Información</th>
						</tr>
						<tr>
							<th><s:text name="id"/></th>
							<th><s:text name="nombre"/></th>
							<th><s:text name="estado"/></th>
							<th><s:text name="fecha"/></th>
							<th><s:text name="acciones"/></th>
						</tr>
						<s:if test="ListaServicios.size() > 0">
							<s:iterator value="ListaServicios" status="result_datos">
								<tr id="node-<s:property value="#result_datos.index"/>">
									<th><s:property value="servicio.id_servicio_informacion" /></th>
									<td><s:property value="servicio.nombre" /></td>
									<td><s:set name="estado" value="servicio.id_estado"/>
										<s:iterator value="estados">
											<s:if test="#estado == id_estado">
												<s:property value="nombre" />
											</s:if>
										</s:iterator>
									</td>
									<td><s:date name="servicio.fecha_creado" format="d'/'MM'/'yyyy" /></td>
									<td>
										<table style="margin: 0; padding: 0;">
											<tr style="margin: 0; padding: 0;">
												<td style="margin: 0; padding: 0;">
													<s:set var="i" value="#result_datos.index"/>
													<form action="examinarServicioInformacion" method="POST">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
														<input type="submit" value="<s:text name="detalles"/>" style="font-size: 0.9em;" />
													</form>
												</td>
												<td style="margin: 0; padding: 0;">
													<form action="prepararModificarServicioInformacion" method="POST">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
														<input type="submit" value="<s:text name="modificar"/>" style="font-size: 0.9em;" />
													</form>
												</td>
												<td style="margin: 0; padding: 0;">
													<form action="eliminarServicioInformacion" method="POST" id="id_<s:property value="#result_datos.index" />" onsubmit="return false;">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
														<input type="submit" value="<s:text name="eliminar" />" style="font-size: 0.9em;" onclick="eliminar_SI(<s:property value="#i"/>,'<s:property value="servicio.nombre" />');" />
													</form>
												</td>
												<s:if test="publicable">
													<td style="margin: 0; padding: 0;">
														<s:if test="!servicio.publicado">
															<!-- <form action="publicarServicioInformacion" method="POST" id="id_pub_<s:property value="#result_datos.index"/>" onsubmit="return false;">
																<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
																<input type="submit" style="font-size: 0.9em;" value="<s:text name="publicar"/>" onclick="publicar_SI(<s:property value="#i"/>,'<s:property value="servicio.nombre" />');" />
															</form>-->
														</s:if>
														<s:else>
															<!-- <form action="despublicarServicioInformacion" method="POST" id="id_despub_<s:property value="#result_datos.index"/>" onsubmit="return false;">
																<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
																<input type="submit" value="<s:text name="despublicar" />" style="font-size: 0.9em;" onclick="despublicar_SI(<s:property value="#i"/>,'<s:property value="servicio.nombre" />');" />
															</form>-->
														</s:else>
													</td>
												</s:if>
												<s:else>
													<s:set name="action" value="%{'prepararModificarServicioInformacion'}"/>
													<s:if test="incompletos.size()==1">
														<s:i18n name="ve/gob/cnti/srsi/i18n/errors">
															<s:iterator value="incompletos">
																<s:set name="error">
																	<s:property />
																</s:set>
																<s:set name="i18n_funcionalidades" value="%{getText('error.servicio.incomplete.funcionalidades')}"/>
																<s:set name="i18n_salidas" value="%{getText('error.servicio.incomplete.salidas')}"/>
																<s:if test="%{(#error == #i18n_salidas) || (#error == #i18n_funcionalidades)}">
																	<s:set name="action" value="%{'prepararFuncionalidades'}"/>
																</s:if>
															</s:iterator>
														</s:i18n>
													</s:if>
													<s:else>
														<s:set name="action" value="%{'prepararModificarServicioInformacion'}"/>
													</s:else>
													<td style="margin: 0; padding: 0;">
														<form action='<s:property value="#action" />' method="POST">
															<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"/>
															<input type="submit" value="<s:text name="continuar_registro" />" style="font-size: 0.9em;" />
														</form>
													</td>
													<td style="margin: 0; padding: 0;">
													<!-- ADVERTENCIA => CONTENIDO NO INTERNACIONALIZADO. -->
														<img src="res/img/important.png" id="h<s:property value='#result_datos.index'/>" alt="ayuda" onmouseover="tip(this);" name="h<s:property value='#result_datos.index'/>" height="25" width="30" />
														<div class="h<s:property value='#result_datos.index'/>" style="visibility: hidden; display: none;">
															<s:iterator value="incompletos">
																<p>
																	<s:property />
																</p>
															</s:iterator>
														</div>
													</td>
												</s:else>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<th colspan="5"><s:text name="sis_null3" /></th>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>
			<div class="n" style="visibility: hidden; display: none;">
				<s:property value="ListaServicios.size()" />
			</div>
		</div>		
	</body>
</s:i18n>
</html>
