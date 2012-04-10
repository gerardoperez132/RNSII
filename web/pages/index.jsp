<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="Refresh" CONTENT="2;URL=pages/autenticarUsuario.action">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/SRSI/pages/res/css/styles.css">
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
				<img src="/SRSI/pages/res/img/header.png" width="880" height="70"
					alt="Cintillo Gobierno Bolivariano" /> <img
					src="/SRSI/pages/res/img/mio.png" width="874" height="116"
					alt="Marco de Interoperabilidad" style="border: 3px solid #57cedc;" />
			</div>

			<!-- Este es el div de los menúes -->
			<div id="menu"></div>

			<!-- Esta es la barra lateral -->
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">
				<br>
				
				<s:url id="login"
					action="autenticarUsuario"></s:url>
				<s:a href="%{login}">Entrar al Sistema</s:a>
				<br>	
								
				<s:url id="registrarServicioInformacion"
					action="prepararServicioInformacion"></s:url>
				<s:a href="%{registrarServicioInformacion}">Registrar Servicio de Información</s:a>
				<br>

				<s:url id="registrarPrueba" action="registrarPrueba"><s:param name="refresh" value="%{false}"></s:param></s:url>				
				<s:a href="%{registrarPrueba}">Registrar Servicio de Información de Prueba</s:a>			
				

			</div>
			<%@include file="layout/footer.jsp"%>
		</div>
	</div>
</body>
</html>