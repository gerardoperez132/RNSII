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
<!-- Donde dice inicio deber�a ir una var que identifique el lugar -->
</head>
<body>

	<!-- Este es el div de la sombra del contenedor del maquetado de la p�gina -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la p�gina -->
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

				<small>Paso 1 Registro de Servicio de Informaci�n</small><br> <br>
				<small>Paso 2 Registro de Funcionalidad(es)</small> <br> <br>
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
					<li class="active"><a href="#tab1">Descripci�n General</a></li>
					<li><a href="#tab2">Entradas</a></li>
					<li><a href="#tab3">Salidas</a></li>
					<li><a href="#tab4">Resumen Funcionalidad</a></li>
				</ul>
				<div class="tab_container">
					<div id="tab1" class="tab_content">
						<form action="modificarFuncionalidad" method="POST">
							<p>Descripci�n General de la Funcionalidad</p>

							<!-- Nombre de la funcionalidad u operaci�n del servicio. -->
							<h5 class="formulario">Nombre:</h5>
							<s:fielderror>
								<s:param>funcionalidad.nombre</s:param>
							</s:fielderror>
							<s:textfield labelposition="top" name="funcionalidad.nombre" />

							<br>
							<!-- Descripci�n de la funcionalidad u operaci�n del servicio. -->
							<h5 class="formulario">Descripci�n (Pre-Condiciones):</h5>
							<s:fielderror>
								<s:param>funcionalidad.descripcion</s:param>
							</s:fielderror>
							<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" />

							<br>

							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="funcionalidad.id_funcionalidad"></s:hidden>
							<input type="submit" value="Modificar" />
						</form>
					</div>

				</div>

			</div>

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>