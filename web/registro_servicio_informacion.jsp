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
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">


				<h3>Registro de Servicio de Informaci�n</h3>

				<hr>
				<p>Descripci�n General del Servicio</p>

				<h5 id="formulario">Sector:</h5>
				<s:select list="sectores" listKey="id_sector" listValue="nombre"
					headerKey="-1" headerValue="Seleccione un sector"></s:select>

				<br>
				<h5 id="formulario">Nombre:</h5>
				<s:textfield labelposition="top" name="nombre" />

				<br>
				<h5 id="formulario">Descripci�n:</h5>
				<s:textarea name="descripcion" cols="40" rows="10" />

				<br>
				<h5 id="formulario">Estado del Servicio:</h5>
				<s:select list="estados" listKey="id_estado" listValue="nombre"
					headerKey="-1" headerValue="Seleccione"></s:select>

				<br> <br>
				<hr>

				<p id="formulario">Aspectos legales que rigen al servicio</p>
				<small>Incluir documento de acuerdo de nivel de
					servicio(SLA) por el qu� se regir� este Servicio de Informaci�n.</small>

				<h5 id="formulario">Nombre del Documento:</h5>
				<s:textfield labelposition="top" name="nombre_documento" />

				<h5 id="formulario">Adjuntar SLA:</h5>
				<s:file name="documento"></s:file>

				<br> <br>
				<hr>

				<p id="formulario">Descripci�n t�cnica del servicio</p>
				<small>Especificaciones del intercambio de Informaci�n</small>

				<h5 id="formulario">Orientado a:</h5>
				<s:checkboxlist list="areas" listKey="id_area" listValue="nombre"
					name="area" />


				<br>
				<h5 id="formulario">Seguridad:</h5>
				<s:select list="seguridad" listKey="id_seguridad" listValue="nombre"
					headerKey="-1" headerValue="Seleccione"></s:select>

				<br>
				<h5 id="formulario">Arquitectura:</h5>
				<s:checkboxlist list="arquitecturas" listKey="id_arquitectura"
					listValue="nombre" name="arquitectura" required="true" />

				<br>

				<s:select list="intercambiosPadres" listKey="id" listValue="nombre"
					headerKey="-1" headerValue="Seleccione"></s:select>
				<s:select list="intercambiosHijos" listKey="id" listValue="nombre"
					headerKey="-1" headerValue="Seleccione"></s:select>
				
				<s:select list="intercambios" listKey="id" listValue="nombre"
					headerKey="-1" headerValue="Seleccione"></s:select>




				<h5 id="formulario">Tipo de Intercambio:</h5>
				<s:select list="{}">
				<s:iterator value="intercambiosPadres">
					
					<s:set name="padre" value="id_intercambio"></s:set>
					<s:set name="nombrePadre" value="nombre"></s:set>
					<s:iterator value="intercambiosHijos">
						
						<s:if test="%{#padre == id_padre}">
						
							<input value="id_intercambio" name="intercambio" type="radio" />
							
						</s:if>
					</s:iterator>
				
				</s:iterator>
				</s:select>
				
				
				
				<s:select list="{}">
				<s:optgroup  list="intercambiosHijos" listKey="id_intercambio" listValue="nombre" />
				</s:select>




			</div>

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>