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
<link rel="stylesheet" type="text/css" href="res/css/table.css">



<title>SRSI - Inicio</title>
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
				<small>Paso 3 Registro de Entradas/Salidas</small><br> <br>
				<small>Paso 4 Verificar y guardar</small>




			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">

				<h3>
					Registro de Funcionalidades del servicio: "
					<s:property value="servicio.nombre" />
					"
				</h3>

				<hr>
				<ul class="tabs">
					<li><a href="#tab1">Descripción General</a></li>
					<li class="active"><a>Entradas</a></li>
					<li><a href="#tab3">Salidas</a></li>
					<li><a href="#tab4">Resumen Funcionalidad</a></li>
				</ul>
				<div class="tab_container">


					<div id="tab2" class="tab_content">

						<h4>
							Registro de Entradas de la Funcionalidad:
							<s:property value="funcionalidad.nombre" />
						</h4>
						
						
						
						
						
						
						<hr>
						
						<table>
							<tr class="nohover">
								<td>
									<form action="prepararEntradaSimple" method="POST">
										<s:hidden name="idServicioInformacion"></s:hidden>
										<s:hidden name="idFuncionalidad"></s:hidden>
										<input type="submit" value="Registrar Dato Simple" />
									</form>
								</td>
								<td>
									<form action="prepararEntradaCompleja" method="POST">
										<s:hidden name="idServicioInformacion"></s:hidden>
										<s:hidden name="idFuncionalidad"></s:hidden>
										<input type="submit" value="Registrar Dato Complejo">
									</form>
								</td>
							</tr>
						</table>
						
						<table class="result">						
							
							<thead>
								<tr>
									<th scope="col">Nombre</th>
									<th scope="col">Descripción</th>
									<th scope="col">Tipo</th>	
									<th scope="col"></th>								
								</tr>
							</thead>	
							
							
							<tfoot>
								<tr class="hv">
									<th scope="row">Total</th>
									<td colspan="3"><s:property value="datos.size"/> entradas cargadas</td>
								</tr>
							</tfoot>
							
							<s:if test="datos.size > 0">
							<tbody>
							<s:iterator value="datos" status="result_Status">							
									<s:if test="id_padre == 0">
									<tr class="<s:if test="#result_Status.odd == true ">odd</s:if><s:else>hv</s:else>">
										<th><s:property value="nombre" /></th>
										<td><s:property value="descripcion" /></td>
										<td>
											<s:set name="id_d" value="id_tipo_dato" ></s:set>										
											<s:set name="id" value="id_dato" ></s:set>
											<s:iterator value="tipoDatos"> 
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:property value="nombre" /><s:property value="#id"/>
												</s:if>											
											</s:iterator> 									
										</td>
										<td>
											<s:iterator value="tipoDatos">
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:if test="%{tipo == 0}">
													<s:set name="padre" value="#id" ></s:set>
													<s:append var="datos2">  
													    <s:param value="%{datos}" />						      
													</s:append>
														<form action="prepararEntradaSimple" method="POST">
															<s:hidden name="idServicioInformacion"></s:hidden>
															<s:hidden name="idFuncionalidad"></s:hidden>
															<s:hidden name="id_dato"></s:hidden>
															<input type="submit" value="Registrar Dato Simple" /><s:property value="id_dato"/>
														</form>
														<s:property value="#padre"/>
													</s:if>			
												</s:if>												
											</s:iterator>								
										</td>
															
									</tr>
									
									<s:if test="%{#padre > 0}">
									entro
									<tr>
									<td colspan="4">
									<table class="result">
									<s:iterator value="datos2">
										
										<s:if test="%{id_padre == #padre}">	
													<tr>
														<th><s:property value="nombre" /></th>
														<td><s:property value="descripcion" /></td>
														<td>
															<s:set name="id_d2" value="id_tipo_dato" ></s:set>										
															<s:iterator value="tipoDatos"> 
																<s:if test="%{id_tipo_dato == #id_d2}">
																	<s:property value="nombre" />
																</s:if>											
															</s:iterator> 									
														</td>
													</tr>		
										</s:if>																
									</s:iterator>
									</table>
									</td>												
									</tr>	
									</s:if>
									
									</s:if>
									
																						
							</s:iterator>
							</tbody>
							</s:if>
							<s:else>
								<tbody>
									<tr class="hv">
										<th class="row" colspan="4">Aún no hay Entradas cargadas para
								está funcionalidad
										</th>																			
									</tr>												
								</tbody>
							</s:else>	
						</table>
						
<s:property value="#padre"/>
						

					</div>

				</div>

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>