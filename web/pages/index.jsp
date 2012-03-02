<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="pages/res/css/styles.css">
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
				<img src="pages/res/img/header.png" width="880" height="70"
					alt="Cintillo Gobierno Bolivariano" /> <img src="pages/res/img/mio.png"
					width="874" height="116" alt="Marco de Interoperabilidad"
					style="border: 3px solid #57cedc;" />
			</div>

			<!-- Este es el div de los men�es -->
			<div id="menu"></div>

			<!-- Esta es la barra lateral -->
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">
				<br>
				<s:url id="registrarServicioInformacion"
					action="prepararRegistroServicioInformacion"></s:url>
				<s:a href="%{registrarServicioInformacion}">Registrar Servicio de Informaci�n</s:a>
				<br>
				<s:url id="prepararFuncionalidad" action="prepararFuncionalidad"></s:url>
				<s:a href="%{prepararFuncionalidad}">Registrar Funcionalidades del Servicio</s:a>

			</div>

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>