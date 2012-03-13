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

				<h3>Registro de Funcionalidad</h3>
				<h4>Servicio: "<s:property value="servicio.nombre"/>"</h4>	
				<hr>
				
				<s:if test="idFuncionalidad>0 && modificar!=true">
				
				<ul class="tabs">
					<li class="active"><a>Descripción General</a>
					</li>
					<li>
						<form action="prepararEntradas" method="POST">
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
							<input type="submit" value="Entradas" style="background: none;
							border: none;font-size: 0.8em;padding: 0 20px; margin-top: 7px;">
						</form>
					</li>
					<li>
						<form action="prepararSalidas" method="POST">
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
							<input type="submit" value="Salidas" style="background: none;
							border: none;font-size: 0.8em;padding: 0 20px; margin-top: 7px;">
						</form>
					</li>
					<li><a>Resumen Funcionalidad</a>
					</li>
				</ul>
				
				<div class="tab_container">
					<div id="tab1" class="tab_content">
					<table>
					<tr>
						<td>
						<h5>Nombre:</h5> 
						</td>
						<td><s:property value="funcionalidad.nombre"/></td>
					</tr>
					<tr>
						<td>
						<h5>Descripción:</h5>
						</td>
						<td> <s:property value="funcionalidad.descripcion"/></td>
					</tr>
					</table>
					
					<form action="prepararFuncionalidad" method="POST">
						<s:hidden name="idServicioInformacion"></s:hidden>
						<s:hidden name="idFuncionalidad"></s:hidden>
						<s:hidden name= "modificar" value="%{true}"></s:hidden>
						<input type="submit" value="Modificar Funcionalidad">
					</form>
					
					
					</div>
				</div>
				
				
				</s:if>				
				
				<s:else>
				<ul class="tabs">
					<li class="active"><a>Descripción General</a>
					</li>
					<li><a>Entradas</a>
					</li>
					<li><a>Salidas</a>
					</li>
					<li><a>Resumen Funcionalidad</a>
					</li>
				</ul>
				<div class="tab_container">
					<div id="tab1" class="tab_content">
					
						<s:if test="modificar == true">
						
						<form action="modificarFuncionalidad" method="POST">
							<p>Descripción General de la Funcionalidad</p>
							<hr>
							
							<!-- Nombre de la funcionalidad u operación del servicio. -->
							<h5 class="formulario">Nombre:</h5>
							<s:fielderror>
								<s:param>funcionalidad.nombre</s:param>
							</s:fielderror>
							<s:textfield name="funcionalidad.nombre" />

							<br>
							<!-- Descripción de la funcionalidad u operación del servicio. -->
							<h5 class="formulario">Descripción (Pre-Condiciones):</h5>
							<s:fielderror>
								<s:param>funcionalidad.descripcion</s:param>
							</s:fielderror>
							<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" />

							<br>
							
							<s:hidden name="idServicioInformacion" ></s:hidden>	
							<s:hidden name="idFuncionalidad"></s:hidden>
							<s:hidden name= "modificar" value="%{false}"></s:hidden>
							<input type="submit" value="Modificar Funcionalidad" />
						</form>
						
												
						</s:if>
						
						<s:else>
					
						<form action="registrarFuncionalidad" method="POST">
							<p>Descripción General de la Funcionalidad</p>
							
							<!-- Nombre de la funcionalidad u operación del servicio. -->
							<h5 class="formulario">Nombre:</h5>
							<s:fielderror>
								<s:param>funcionalidad.nombre</s:param>
							</s:fielderror>
							<s:textfield labelposition="top" name="funcionalidad.nombre" />

							<br>
							<!-- Descripción de la funcionalidad u operación del servicio. -->
							<h5 class="formulario">Descripción (Pre-Condiciones):</h5>
							<s:fielderror>
								<s:param>funcionalidad.descripcion</s:param>
							</s:fielderror>
							<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" />

							<br>
							
							<s:hidden name="idServicioInformacion" ></s:hidden>	
							<input type="submit" value="Registrar" />
						</form>
						
						</s:else>
						
					</div>
					
				</div>
				
				</s:else>

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>