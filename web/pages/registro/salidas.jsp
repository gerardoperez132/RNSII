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
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/salidas.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<title><s:text name="salida.salidas"></s:text></title>
	</head>
	<body>
		<div class="container">
			<%@include file="../layout/header.jsp"%>
			<!-- Esta es la barra lateral -->
			<%@include file="../layout/sidebar.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">
				<%@include file="../layout/bienvenido.jsp"%>				
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
					<li>
						<form action="prepararFuncionalidad" method="POST">
							<s:hidden name="id_servicio_informacion"/>
							<s:hidden name="id_funcionalidad"/>
							<s:hidden name="modificar"/>
							<input type="submit" value="<s:text name="salida.tab1.title"/>"
								style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
						</form>
					</li>
					<li>
						<form action="prepararEntradas" method="POST">
							<s:hidden name="id_servicio_informacion"/>
							<s:hidden name="id_funcionalidad"/>
							<s:hidden name="modificar"/>
							<input type="submit" value="<s:text name="entrada.tab2.title"/>" style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
						</form>
					</li>
					<li class="active"><a><s:text name="salida.tab3.title"/></a></li>
					<li>
						<form action="prepararResumen" method="POST">
							<s:hidden name="id_servicio_informacion"/>
							<s:hidden name="id_funcionalidad"/>
							<s:hidden name="modificar"/>
							<input type="submit" value="<s:text name="salida.tab4.title" />" style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
						</form>
					</li>
				</ul>
				<div class="tab_container_height tab_container">
					<div id="tab2" class="tab_content">
						<h5 class="formulario">
							<s:text name="salida.salidas.title">
								<s:param>
									<s:property value="funcionalidad.nombre" />
								</s:param>
							</s:text>
						</h5>
						<hr>
						<table>
							<tr class="nohover">
								<td>
									<form action="prepararRegistroSalida" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<input type="submit" value="<s:text name="salida.registro"/>" />
									</form>
								</td>
							</tr>
						</table>
						<!-- Tabla en �rbol. -->
						<table id="tree" class="results_width_user_datos results">
							<!-- Validaci�n de lista vac�a. -->
							<tbody>
								<tr>
									<th><s:text name="salida.nombre" /></th>
									<th><s:text name="salida.descripcion" /></th>
									<th><s:text name="salida.tipo" /></th>
									<th><s:text name="salida.acciones" /></th>
								</tr>
								<s:if test="salidas.size > 0">
									<s:set name="contador" value="%{0}"/>
									<!-- Iterador con todas las salidas cargadas. -->
									<s:iterator value="salidas" status="result_entradas">
										<!-- Condici�n que asegura que s�lo se impriman datos sin padres. -->
										<s:if test="id_padre == 0">
											<!-- Creaci�n de fila con su nodo sacado del index del iterador. -->
											<tr id="node-<s:property value="#result_entradas.index"/>">
												<td><s:property value="nombre" /></td>
												<td><s:property value="descripcion" /></td>
												<!-- Impresi�n del tipo dato. -->
												<td>
													<!-- Creaci�n de una variable con el id_entrada_salida para identificar a los datos complejos. -->
													<s:set name="id" value="id_entrada_salida" /> <!-- Creaci�n de una variable con el id_tipo_de_dato. -->
													<s:set name="id_d" value="id_tipo_dato" />
													<s:set name="salida.nombre" value="nombre" />
													<s:iterator value="tipoDatos">
														<!-- Impresi�n del tipo de dato de acuerdo a su id. -->
														<s:if test="%{id_tipo_dato == #id_d}">
															<s:property value="nombre" />
														</s:if>
													</s:iterator>
												</td>
												<td>
												<s:set name="contador_hijos" value="%{0}" />
													<!-- Bloque que muestra un bot�n si el dato es una lista. -->
													<s:iterator value="tipoDatos">
														<s:if test="%{id_tipo_dato == #id_d}">
															<s:if test="%{tipo == 0}">
																<s:set name="padre" value="#id"/>
																<s:append var="hijos">
																	<s:param value="%{salidas}" />
																</s:append>
																<table style="margin: 0; padding: 0;">
																	<tr style="margin: 0; padding: 0;">
																		<td style="margin: 0; padding: 0;">
																			<form action="prepararSalidaSimple" method="POST">
																				<s:hidden name="id_servicio_informacion"/>
																				<s:hidden name="id_funcionalidad"/>
																				<s:hidden name="id_entrada_salida"/>
																				<s:hidden name="id_salida_padre" value="%{id_entrada_salida}"/>
																				<input type="submit" value="<s:text name="salida.simple.add"/>" style="font-size: 1em;" />
																			</form>
																		</td>
																		<td style="margin: 0; padding: 0;">
																			<form action="prepararModificarSalidaCompleja" method="POST">
																				<s:hidden name="id_servicio_informacion"/>
																				<s:hidden name="id_funcionalidad"/>
																				<s:hidden name="id_entrada_salida" value="%{#id}"/>
																				<s:hidden name="modificar" value="%{true}"/>
																				<s:hidden name="complejo" value="%{true}"/>
																				<input type="submit" value="<s:text name="salida.modificar"/>" style="font-size: 1em;" />
																			</form>
																		</td>
																		<td style="margin: 0; padding: 0;">
																			<form action="eliminarSalidaCompleja" method="POST"
																				id="id_elim_<s:property value="id_entrada_salida"/>" onsubmit="return false;">
																				<s:hidden name="id_servicio_informacion"/>
																				<s:hidden name="id_funcionalidad"/>
																				<s:hidden name="id_entrada_salida" value="%{#id}"/>
																				<s:hidden name="modificar" value="%{true}"/>
																				<input type="submit" value="<s:text name="salida.eliminar"/>" style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Salida(<s:property value="id_entrada_salida"/>,'<s:property value="salida.nombre" />');" />
																			</form>
																			<s:iterator value="hijos" status="status">
																				<s:if test="%{id_padre == #padre}">
																					<s:set name="contador_hijos" value="%{#contador_hijos + 1}"/>
																				</s:if>
																			</s:iterator>
																			<s:append var="hijos">
																				<s:param value="%{salidas}" />
																			</s:append>
																			<s:if test="%{#contador_hijos==0}">
																				<s:set name="contador" value="%{#contador +1}"/>
																				<s:i18n name="ve/gob/cnti/srsi/i18n/errors">
																					<td style="margin: 0; padding: 0;"><img src="res/img/important.png" id="h<s:property value='#contador'/>" alt="ayuda" onmouseover="tip(this);" name="h<s:property value='#contador'/>" height="25" width="30" />
																						<div class="h<s:property value='#contador'/>" style="visibility: hidden; display: none;">
																							<p>
																								<s:text name="error.servicio.incomplete.hijos" />
																							</p>
																						</div>
																					</td>
																				</s:i18n>
																			</s:if>
																</table>
															</s:if>
															<s:else>
																<table style="margin: 0; padding: 0;">
																	<tr style="margin: 0; padding: 0;">
																		<td style="margin: 0; padding: 0;">
																			<form action="prepararModificarSalida" method="POST">
																				<s:hidden name="id_servicio_informacion"/>
																				<s:hidden name="id_funcionalidad"/>								
																				<s:hidden name="id_entrada_salida"/>	
																				<s:hidden name="modificar" value="%{true}"/>
																				<input type="submit" value="<s:text name="salida.modificar"/>" style="font-size: 1em;" />
																			</form>
																		</td>
																		<td style="margin: 0; padding: 0;">
																			<form action="eliminarSalidaSimple" method="POST" id="id_elim_<s:property value="id_entrada_salida"/>" onsubmit="return false;">
																				<s:hidden name="id_servicio_informacion"/>
																				<s:hidden name="id_funcionalidad"/>
																				<s:hidden name="id_entrada_salida"/>
																				<input type="submit" value="<s:text name="salida.eliminar"/>" style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Salida(<s:property value="id_entrada_salida"/>,'<s:property value="salida.nombre" />');" />
																			</form>
																		</td>
																	</tr>
																</table>
															</s:else>
														</s:if>
													</s:iterator>
												</td>
											</tr>
										</s:if>
										<s:else>
											<s:set name="padre">0</s:set>
										</s:else>
										<!-- Impresi�n de datos hijos. -->
										<s:if test="%{#padre > 0}">
											<s:iterator value="hijos" status="status_hijos">
												<s:if test="%{id_padre == #padre}">
													<tr id="node-<s:property value="%{( (100) * (#result_entradas.index) )+ #status_hijos.index}" />" class="child-of-node-<s:property value="#result_entradas.index" />">
														<td><s:property value="nombre" /></td>
														<td><s:property value="descripcion" /></td>
														<td><s:set name="id_d2" value="id_tipo_dato"></s:set>
															<s:iterator value="tipoDatos">
																<s:if test="%{id_tipo_dato == #id_d2}">
																	<s:property value="nombre" />
																</s:if>
															</s:iterator></td>
														<td>
															<table style="margin: 0; padding: 0;">
																<tr style="margin: 0; padding: 0;">
																	<td style="margin: 0; padding: 0;">
																		<form action="prepararModificarSalidaSimple" method="POST">
																			<s:hidden name="id_servicio_informacion"/>
																			<s:hidden name="id_funcionalidad"/>
																			<s:hidden name="id_entrada_salida"/>
																			<s:hidden name="id_salida_padre" value="%{id_entrada_salida}"/>
																			<s:hidden name="modificar" value="%{true}"/>
																			<input type="submit" value="<s:text name="salida.modificar"/>" style="font-size: 1em;" />
																		</form>
																	</td>
																	<td style="margin: 0; padding: 0;">
																		<form action="eliminarSalidaSimple" method="POST" id="id_elim_<s:property value="id_entrada_salida"/>"	onsubmit="return false;">
																			<s:hidden name="id_servicio_informacion"/>
																			<s:hidden name="id_funcionalidad"/>
																			<s:hidden name="id_entrada_salida"/>
																			<input type="submit" value="<s:text name="salida.eliminar"/>" style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Salida(<s:property value="id_entrada_salida"/>,'<s:property value="nombre" />');" />
																		</form>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</s:if>
											</s:iterator>
										</s:if>
									</s:iterator>
								</s:if>
							</tbody>
							<s:else>
								<tbody>
									<tr>
										<td colspan="4"><s:text name="salida.salidas.error"></s:text></td>
									</tr>
								</tbody>
							</s:else>
						</table>
					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
		<div class="n" style="visibility: hidden; display: none;">
			<s:property value="#contador" />
		</div>
		<div style="clear: both"></div>
		<div class="vacio"></div>
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>