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
				<big>Paso 2 Registro de Funcionalidad(es)</big> <br> <br>
				<small>Paso 3 Registro de Entradas/Salidas</small><br> <br>
				<small>Paso 4 Verificar y guardar</small>




			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">

				<h4 class="errorMessage">Error!!!</h4>
				<p>No puedes hacer puedes registrar el mismo formulario dos veces.</p>
				<p>En breves momentos seras redireccionado...</p>
				<s:url id="siguiente" action="prepararEntradas"></s:url>
				<s:a href="%{siguiente}" cssClass="enlace">
					Seguir
				</s:a>
			</div>

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>