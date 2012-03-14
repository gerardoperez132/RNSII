<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">

<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"></script>

<script type="text/javascript">
	$(document).ready(
		function() {
			$("#tree").treeTable();
		}
	);
</script>



<title>SRSI - Salidas</title>
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
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
				<small>Paso 1 Registro de Servicio de Información</small><br> <br>
				<big>Paso 2 Registro de Funcionalidad(es)</big> <br> <br>
				<small>Paso 3 Registro de Salidas/Salidas</small><br> <br>
				<small>Paso 4 Verificar y guardar</small>
			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">

				<h3>Registro de Funcionalidad</h3>
				<h4>Servicio: "<s:property value="servicio.nombre"/>"</h4>	
				<hr>

				<ul class="tabs">
					<li>
						<form action="prepararFuncionalidad" method="POST">
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
							<input type="submit" value="Descripcion General" style="background: none;
							border: none;font-size: 0.8em;padding: 0 20px; height: 31px;">
						</form>
					</li>
					<li>
						<form action="prepararEntradas" method="POST">
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
							<input type="submit" value="Entradas" style="background: none;
							border: none;font-size: 0.8em;padding: 0 20px; height: 31px;">
						</form>
					</li>
					<li class="active"><a>Salidas</a></li>					
					<li>
						<form action="prepararResumen" method="POST">
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
							<input type="submit" value="Resumen de Funcionalidad" style="background: none;
							border: none;font-size: 0.8em;padding: 0 20px; height: 31px;">
						</form>
					</li>
				</ul>
				<div class="tab_container">


					<div id="tab2" class="tab_content">

						<h4>
							Registro de Salidas de la Funcionalidad:
							<s:property value="funcionalidad.nombre" />
						</h4>
						
						<hr>
						
						<table>
							<tr class="nohover">
								<td>
									<form action="prepararSalidaSimple" method="POST">
										<s:hidden name="idServicioInformacion"></s:hidden>
										<s:hidden name="idFuncionalidad"></s:hidden>
										<input type="submit" value="Registrar Dato Simple" />
									</form>
								</td>
								<td>
									<form action="prepararSalidaCompleja" method="POST">
										<s:hidden name="idServicioInformacion"></s:hidden>
										<s:hidden name="idFuncionalidad"></s:hidden>
										<input type="submit" value="Registrar Dato Complejo">
									</form>
								</td>
							</tr>
						</table>
						
						<!-- Tabla en arborl -->
						<table id="tree" class="treeTable">
							<thead>
								<tr>
									<th>Nombre</th>
									<th>Descripción</th>
									<th>Tipo</th>	
									<th>Acciones</th>
								</tr>
							</thead>
							
							<!-- Validación de lista vacia -->
							<s:if test="datos.size > 0">
							
							<tbody>
							<!-- iterador con todas las Salidas cargadas -->
							<s:iterator value="datos" status="result_datos">
							
								<!-- Condicción que asegura que solo se impriman datos sin padres -->
								<s:if test="id_padre == 0">
								
								<!-- Creación de fila con su nodo sacado del index del iterador -->
								<tr id="node-<s:property value="#result_datos.index"/>">
									
										<td><s:property value="nombre" /></td>
										<td><s:property value="descripcion" /></td>
										<!-- impresión del tipo dato -->
										<td>
											<!-- creación de una variable con el id_dato para identicar a los datos complejos -->										
											<s:set name="id" value="id_dato" ></s:set>
											<!-- creación de una variable con el id_tipo_de_dato -->
											<s:set name="id_d" value="id_tipo_dato" ></s:set>
											<s:iterator value="tipoDatos">
												<!--impresión del tipo de dato de acuerdo a su id --> 
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:property value="nombre" />
												</s:if>											
											</s:iterator> 									
										</td>
										<td>
											<!-- Bloque que muestra un boton si el dato es una lista-->
											<s:iterator value="tipoDatos">
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:if test="%{tipo == 0}">
													<s:set name="padre" value="#id" ></s:set>
													<s:append var="datos2">  
													    <s:param value="%{datos}" />						      
													</s:append>
														<table style="margin: 0;padding: 0;"><tr style="margin: 0;padding: 0;">
														<td style="margin: 0;padding: 0;">
														<form action="prepararSalidaSimple" method="POST">
															<s:hidden name="idServicioInformacion"></s:hidden>
															<s:hidden name="idFuncionalidad"></s:hidden>
															<s:hidden name="id_dato"></s:hidden>
															<input type="submit" value="Agregar dato Simple" style="font-size: 0.7em;" />
														</form>
														</td>
														<td style="margin: 0;padding: 0;">
														<form action="prepararModificarSalidaCompleja" method="POST">
															<s:hidden name="idServicioInformacion"></s:hidden>
															<s:hidden name="idFuncionalidad"></s:hidden>
															<s:hidden name="id_dato" value="%{#id}"></s:hidden>
															<s:hidden name= "modificar" value="%{true}"></s:hidden>
															<input type="submit" value="Modificar" style="font-size: 0.7em;" />
														</form>
														</td>
														<td style="margin: 0;padding: 0;">
														<form action="eliminarSalidaCompleja" method="POST">
															<s:hidden name="idServicioInformacion"></s:hidden>
															<s:hidden name="idFuncionalidad"></s:hidden>
															<s:hidden name="id_dato" value="%{#id}"></s:hidden>
															<s:hidden name= "modificar" value="%{true}"></s:hidden>
															<input type="submit" value="Eliminar" style="font-size: 0.7em;" />
														</form>
														</table>
																												
													</s:if>
													<s:else>
													<table style="margin: 0;padding: 0;"><tr style="margin: 0;padding: 0;">
													<td style="margin: 0;padding: 0;">
													<form action="prepararModificarSalidaSimple" method="POST">
														<s:hidden name="idServicioInformacion"></s:hidden>
														<s:hidden name="idFuncionalidad"></s:hidden>
														<s:hidden name="id_dato"></s:hidden>
														<s:hidden name= "modificar" value="%{true}"></s:hidden>
														<input type="submit" value="Modificar" style="font-size: 0.7em;" />
													</form>
													</td>
													<td style="margin: 0;padding: 0;">
													<form action="eliminarSalidaSimple" method="POST">
														<s:hidden name="idServicioInformacion"></s:hidden>
														<s:hidden name="idFuncionalidad"></s:hidden>
														<s:hidden name="id_dato"></s:hidden>														
														<input type="submit" value="Eliminar" style="font-size: 0.7em;" />
													</form>
													</td>
													</tr></table>
													</s:else>			
												</s:if>																				
											</s:iterator>								
										</td>							
								
								</tr>
								</s:if>
								<s:else>
									<s:set name="padre">0</s:set>
								</s:else>
								<!-- impresion de datos hijos-->
								<s:if test="%{#padre > 0}">	
									<s:iterator value="datos2" status="status_datos2">
										
										<s:if test="%{id_padre == #padre}">		
											<tr id="node-<s:property value="%{( (100) * (#result_datos.index) )+ #status_datos2.index}" />" 
										class="child-of-node-<s:property value="#result_datos.index" />" >
												
												<td><s:property value="nombre" /></td>
												<td><s:property value="descripcion" /></td>
												<td>
													<s:set name="id_d2" value="id_tipo_dato" ></s:set>										
													<s:iterator value="tipoDatos"> 
														<s:if test="%{id_tipo_dato == #id_d2}">
															<s:property value="nombre" />
														</s:if>											
													</s:iterator> 									
												</td>
												<td>
												<table style="margin: 0;padding: 0;"><tr style="margin: 0;padding: 0;">
													<td style="margin: 0;padding: 0;">
													<form action="prepararModificarSalidaSimple" method="POST">
														<s:hidden name="idServicioInformacion"></s:hidden>
														<s:hidden name="idFuncionalidad"></s:hidden>
														<s:hidden name="id_dato"></s:hidden>
														<s:hidden name= "modificar" value="%{true}"></s:hidden>
														<input type="submit" value="Modificar" style="font-size: 0.7em;" />
													</form>
													</td>
													<td style="margin: 0;padding: 0;">
													<form action="eliminarSalidaSimple" method="POST">
														<s:hidden name="idServicioInformacion"></s:hidden>
														<s:hidden name="idFuncionalidad"></s:hidden>
														<s:hidden name="id_dato"></s:hidden>														
														<input type="submit" value="Eliminar" style="font-size: 0.7em;" />
													</form>
													</td>
													</tr></table>
												</td>
												
											</tr>		
										</s:if>																
									</s:iterator>
								
								</s:if>	
									
								
							</s:iterator>
							</tbody>
							
							</s:if>
							<s:else>
							
								<tbody>
									<tr>
										<th colspan="4">Aún no hay Salidas cargadas para está funcionalidad
										</th>																			
									</tr>												
								</tbody>
							
							</s:else>
							
						</table>
					</div>
				</div>

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>