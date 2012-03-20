<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<s:if test="%{#session.logueado != true}">
 	<META HTTP-EQUIV="Refresh" CONTENT="0;URL=../index.action">
</s:if>
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
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
				<div id="menu8">
				  <ul>
				    <li><a href="#1" title="Home">Home</a></li>
				    <li><a href="#2" title="About">About</a></li>
				    <li><a href="#3" title="Services">Services</a></li>
				    <li><a href="#4" title="Portfolio">Portfolio</a></li>
				    <li><a href="#5" title="Store">Store</a></li>
				    <li><a href="#6" title="Download">Download Menu</a></li>
				  </ul>
				</div>
			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">
			
			llegó: <s:property value="%{#session.usuario.nombre}"/>
			
			<a href="<s:url action="salir"/>" >Salir</a>			
			
			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>