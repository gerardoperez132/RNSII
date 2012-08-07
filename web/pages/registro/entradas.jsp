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
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/entradas.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<title><s:text name="entrada.entradas"></s:text></title>
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
		<div class="pasos">
			<table>
				<tr>
					<td>						
						<small>									
							<strong>
								<s:text name="paso2.1.1" />									
								<s:text name="funcionalidades" /> -										
							</strong>
							<s:text name="entrada.tab2.title"/>
						</small>
					</td>							
				</tr>
			</table>
		</div>
		<h3>
			<s:text name="entrada.registro.title"></s:text>
		</h3>
		<h4>
			<s:text name="entrada.servicio.title">
				<s:param>
					<s:property value="servicio.nombre" />
				</s:param>
			</s:text>
		</h4>
		<hr>
		<ul class="tabs">
			<li>
				<form action="prepararFuncionalidad" method="POST">
					<s:hidden name="id_servicio_informacion"></s:hidden>
					<s:hidden name="id_funcionalidad"></s:hidden>
					<s:hidden name="modificar"></s:hidden>
					<input type="submit" value="<s:text name="entrada.tab1.title"></s:text>"
						style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
				</form>
			</li>
			<li class="active"><a><s:text name="entrada.tab2.title"></s:text>
			</a></li>
			<li>
				<form action="prepararSalidas" method="POST">
					<s:hidden name="id_servicio_informacion"></s:hidden>
					<s:hidden name="id_funcionalidad"></s:hidden>
					<s:hidden name="modificar"></s:hidden>
					<input type="submit" value="<s:text name="entrada.tab3.title"></s:text>"
						style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
				</form>
			</li>
			<li>
				<form action="prepararResumen" method="POST">
					<s:hidden name="id_servicio_informacion"></s:hidden>
					<s:hidden name="id_funcionalidad"></s:hidden>
					<s:hidden name="modificar"></s:hidden>
					<input type="submit" value="<s:text name="entrada.tab4.title" />"
						style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
				</form>
			</li>
		</ul>
		<div class="tab_container">
			<div id="tab2" class="tab_content">
				<h4>
					<s:text name="entrada.entradas.title">
						<s:param>
							<s:property value="funcionalidad.nombre" />
						</s:param>
					</s:text>
				</h4>
				<hr>
				<table>
					<tr class="nohover">
						<td>
							<form action="prepararRegistroEntrada" method="POST">
								<s:hidden name="id_servicio_informacion"></s:hidden>
								<s:hidden name="id_funcionalidad"></s:hidden>											
								<input type="submit"
									value="<s:text name="entrada.registro"></s:text>" />
							</form>
						</td>
						
					</tr>
				</table>
				<!-- Tabla en árbol. -->
				<table id="tree" class="results" style="width:750px;">					
					<!-- Validación de lista vacía. -->
					<tbody>
						<tr>
							<th><s:text name="entrada.nombre"></s:text></th>
							<th><s:text name="entrada.descripcion"></s:text></th>
							<th><s:text name="entrada.tipo"></s:text></th>
							<th><s:text name="entrada.acciones"></s:text></th>
						</tr>
					<s:if test="entradas.size > 0">
						
							<!-- Iterador con todas las entradas cargadas. -->
							<s:iterator value="entradas" status="result_entradas">
								<!-- Condición que asegura que sólo se impriman datos sin padres. -->
								<s:if test="id_padre == 0">
									<!-- Creación de fila con su nodo sacado del index del iterador. -->
									<tr id="node-<s:property value="#result_entradas.index"/>">
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<!-- Impresión del tipo dato. -->
										<td>
											<!-- Creación de una variable con el id_entrada_salida para identificar a los datos complejos. -->
											<s:set name="id" value="id_entrada_salida" /> <!-- Creación de una variable con el id_tipo_de_dato. -->
											<s:set name="id_d" value="id_tipo_dato" />
											<s:set name="entrada.nombre" value="nombre" /> 
											<s:iterator	value="tipoDatos">
												<!-- Impresión del tipo de dato de acuerdo a su id. -->
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:property value="nombre" />
												</s:if>
											</s:iterator>
										</td>
										<td>
											<!-- Bloque que muestra un botón si el dato es una lista. -->
											<s:iterator value="tipoDatos">
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:if test="%{tipo == 0}">
														<s:set name="padre" value="#id"></s:set>
														<s:append var="hijos">
															<s:param value="%{entradas}" />
														</s:append>
														<table style="margin: 0; padding: 0;">
															<tr style="margin: 0; padding: 0;">
																<td style="margin: 0; padding: 0;">
																	<form action="prepararEntradaSimple" method="POST">
																		<s:hidden name="id_servicio_informacion"></s:hidden>
																		<s:hidden name="id_funcionalidad"></s:hidden>
																		<s:hidden name="id_entrada_salida"></s:hidden>																					
																		<input type="submit"
																			value="<s:text name="entrada.simple.add"></s:text>"
																			style="font-size: 1em;" />
																	</form>
																</td>
																<td style="margin: 0; padding: 0;">
																	<form action="prepararModificarEntradaCompleja"
																		method="POST">
																		<s:hidden name="id_servicio_informacion"></s:hidden>
																		<s:hidden name="id_funcionalidad"></s:hidden>
																		<s:hidden name="id_entrada_salida" value="%{#id}"></s:hidden>
																		<s:hidden name="modificar" value="%{true}"></s:hidden>
																		<input type="submit"
																			value="<s:text name="entrada.modificar"></s:text>"
																			style="font-size: 1em;" />
																	</form>
																</td>
																<td style="margin: 0; padding: 0;">
																	<form action="eliminarEntradaCompleja" method="POST"
																	id="id_elim_<s:property value="id_entrada_salida"/>" onsubmit="return false;">
																		<s:hidden name="id_servicio_informacion"></s:hidden>
																		<s:hidden name="id_funcionalidad"></s:hidden>
																		<s:hidden name="id_entrada_salida" value="%{#id}"></s:hidden>
																		<s:hidden name="modificar" value="%{true}"></s:hidden>
																		<input type="submit" value="<s:text name="entrada.eliminar"></s:text>"
																		style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Entrada(<s:property value="id_entrada_salida"/>
																			,'<s:property value="entrada.nombre" />');"/>
																	</form>
														</table>
													</s:if>
													<s:else>
														<table style="margin: 0; padding: 0;">
															<tr style="margin: 0; padding: 0;">
																<td style="margin: 0; padding: 0;">
																	<form action="prepararModificarEntradaSimple"
																		method="POST">
																		<s:hidden name="id_se0.7rvicio_informacion"></s:hidden>
																		<s:hidden name="id_funcionalidad"></s:hidden>
																		<s:hidden name="id_entrada_salida"></s:hidden>
																		<s:hidden name="modificar" value="%{true}"></s:hidden>
																		<input type="submit"
																			value="<s:text name="entrada.modificar"></s:text>"
																			style="font-size: 1em;" />
																	</form>
																</td>
																<td style="margin: 0; padding: 0;">
																	<form action="eliminarEntradaSimple" method="POST"
																	id="id_elim_<s:property value="id_entrada_salida"/>" onsubmit="return false;">
																		<s:hidden name="id_servicio_informacion"></s:hidden>
																		<s:hidden name="id_funcionalidad"></s:hidden>
																		<s:hidden name="id_entrada_salida"></s:hidden>
																		<input type="submit" value="<s:text name="entrada.eliminar"></s:text>"
																		style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Entrada(<s:property value="id_entrada_salida"/>
																			,'<s:property value="entrada.nombre" />');"/>
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
								<!-- Impresión de datos hijos. -->
								<s:if test="%{#padre > 0}">
									<s:iterator value="hijos" status="status_hijos">
										<s:if test="%{id_padre == #padre}">
											<tr
												id="node-<s:property value="%{( (100) * (#result_entradas.index) )+ #status_hijos.index}" />"
												class="child-of-node-<s:property value="#result_entradas.index" />">
												<td><s:property value="nombre" /></td>
												<td><s:property value="descripcion" /></td>
												<td>												
													<s:set name="id_d2" value="id_tipo_dato"></s:set>
													<s:iterator value="tipoDatos">
														<s:if test="%{id_tipo_dato == #id_d2}">
															<s:property value="nombre" />
														</s:if>
													</s:iterator></td>
												<td>
													<table style="margin: 0; padding: 0;">
														<tr style="margin: 0; padding: 0;">
															<td style="margin: 0; padding: 0;">
																<form action="prepararModificarEntradaSimple"
																	method="POST">
																	<s:hidden name="id_servicio_informacion"></s:hidden>
																	<s:hidden name="id_funcionalidad"></s:hidden>
																	<s:hidden name="id_entrada_salida"></s:hidden>
																	<s:hidden name="modificar" value="%{true}"></s:hidden>
																	<input type="submit"
																		value="<s:text name="entrada.modificar"></s:text>"
																		style="font-size: 1em;" />
																</form>
															</td>
															<td style="margin: 0; padding: 0;">
																<form action="eliminarEntradaSimple" method="POST"
																	id="id_elim_<s:property value="id_entrada_salida"/>" onsubmit="return false;">
																	<s:hidden name="id_servicio_informacion"></s:hidden>
																	<s:hidden name="id_funcionalidad"></s:hidden>
																	<s:hidden name="id_entrada_salida"></s:hidden>
																	<input type="submit" value="<s:text name="entrada.eliminar"></s:text>"
																		style="font-size: 1em;" style="font-size: 1em;" onclick="eliminar_Entrada(<s:property value="id_entrada_salida"/>
																			,'<s:property value="nombre" />');"/>
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
								<td colspan="4"><s:text name="entrada.entradas.error"></s:text></td>
							</tr>
						</tbody>
					</s:else>
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