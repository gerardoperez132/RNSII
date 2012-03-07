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
					<li><a href="#tab3">Entradas</a></li>
					<li class="active"><a>Salidas</a></li>
					<li><a href="#tab4">Resumen Funcionalidad</a></li>
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
										<input type="submit" value="Agregar Dato Simple" />
									</form>
								</td>
								<td>
									<s:url id="registrarSalidaComplejo"
										action="prepararSalidaComplejo"></s:url>
									<s:a href="%{registrarSalidaComplejo}" cssClass="enlace">
										<input type="button" value="Agregar Dato Complejo">
									</s:a>
								</td>
							</tr>
						</table>
						
						<table class="result">						
							
							<thead>
								<tr>
									<th scope="col">Nombre</th>
									<th scope="col">Descripción</th>
									<th scope="col">Tipo</th>									
								</tr>
							</thead>	
							
							
							<tfoot>
								<tr class="hv">
									<th scope="row">Total</th>
									<td colspan="2"><s:property value="datos.size"/> salidas cargadas</td>
								</tr>
							</tfoot>
							
							<s:if test="datos.size > 0">
							<tbody>
							<s:iterator value="datos" status="result_Status">							
								
									<tr class="<s:if test="#result_Status.odd == true ">odd</s:if><s:else>hv</s:else>">
										<th ><s:property value="nombre" /></th>
										<td><s:property value="descripcion" /></td>
										<td>
											<s:set name="id_d" value="id_tipo_dato" ></s:set>										
											<s:iterator value="tipoDatos"> 
												<s:if test="%{id_tipo_dato == #id_d}">
													<s:property value="nombre" />
												</s:if>											
											</s:iterator> 									
										</td>									
									</tr>												
																
								</s:iterator>
							</tbody>
							</s:if>
							<s:else>
								<tbody>
									<tr class="hv">
										<th class="row" colspan="3">Aún no hay Salidas cargadas para
								está funcionalidad
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