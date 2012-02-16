<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">
			
			<h3>Registro de Servicio de Información</h3>
			
			<br><br>
			<s:select list="sectores" listKey="id_sector" listValue="nombre" label="Sector" headerKey="-1" headerValue="Seleccione un sector"></s:select>
			
			<br><br>
			<s:textfield label="Nombre" labelposition="top"  name="nombre"  />
			
			<br><br>
			<div id="resaltar">
				<h5 id="formulario">Descripción</h5>
				<s:textarea name="descripcion" cols="40" rows="10" />
				<div id="mensaje">
					<p>Por favor introduzca una breve descripción del servicio de información a registrar.</p>
				</div>
			</div>
			
			<br><br>
			<div id="resaltar">
				<h5 id="formulario">Descripción</h5>
				<s:checkboxlist list="areas" listKey="id_area" listValue="nombre" name="area" label="Orientado a" /> 
				<div id="mensaje">
					<p>Por favor introduzca una breve descripción del servicio de información a registrar.</p>
				</div>
			</div>
			
			
			<br><br>
				<div id="resaltar">
				<h5 id="formulario">Descripción</h5>
				<s:select list="estados" listKey="id_estado" listValue="nombre" label="Estado de desarrollo" headerKey="-1" headerValue="Seleccione"></s:select>
				<div id="mensaje">
					<p>Por favor introduzca una breve descripción del servicio de información a registrar.</p>
				</div>
			</div>
			
			
			<br><br>
			<div id="resaltar">
				<h5 id="formulario">Descripción</h5>
				<s:select list="seguridad" listKey="id_seguridad" listValue="nombre" label="Nivel de Seguridad" headerKey="-1" headerValue="Seleccione"></s:select>
				<br><br>
				<s:checkboxlist list="arquitecturas" listKey="id_arquitectura" listValue="nombre" name="arquitectura" label="Arquitectura" required="true" />
				<div id="mensaje">
					<p>Por favor introduzca una breve descripción del servicio de información a registrar.</p>
				</div>
			</div>
			
			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>