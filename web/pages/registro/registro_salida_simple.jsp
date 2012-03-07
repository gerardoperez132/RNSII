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


<title>SRSI - Registro Salidas Simples</title>
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

				<h3>Registro de Funcionalidades</h3>

				<hr>
				<s:url id="funcionalidad"
					action="prepararRegistroServicioInformacion"></s:url>
				<s:a href="%{registrarServicioInformacion}">Registrar Servicio de Información</s:a>


				<ul class="tabs">
					<li><a href="#tab1">Descripción General</a></li>
					<li><a href="#tab2">Entradas</a></li>
					<li class="active">Salidas</li>
					<li><a href="#tab4">Resumen Funcionalidad</a></li>
				</ul>
				<div class="tab_container">		
				
					<form action="registrarSalidaSimple" method="post">
						<div id="tab2" class="tab_content">

							<h5 class="formulario">Registro de Salida Simple</h5>
							<h6>
								Perteneciente a la funcionalidad: "
								<s:property value="funcionalidad.nombre" />
								"
							</h6>
							<hr>

							<!-- Nombre de la entrada. -->
							<h5 class="formulario">Nombre:</h5>
							<s:fielderror>
								<s:param>dato.nombre</s:param>
							</s:fielderror>
							<s:textfield name="dato.nombre" />

							<br>
							<!-- Descripción de la entrada. -->
							<h5 class="formulario">Descripción:</h5>
							<s:fielderror>
								<s:param>dato.descripcion</s:param>
							</s:fielderror>
							<s:textarea name="dato.descripcion" cols="30" rows="5" />

							<br>


							<h5 class="formulario">Tipo de dato asociado:</h5>
							<s:fielderror>
								<s:param>tipodato</s:param>
							</s:fielderror>
							<s:select name="dato.id_tipo_dato" list="tipoDatos"
								listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
								headerValue="Seleccione"></s:select>

							<br> <br>
							<s:hidden name="idServicioInformacion"></s:hidden>
							<s:hidden name="idFuncionalidad"></s:hidden>
														
							<input type="submit" value="Registrar Entrada" />

						</div>
					</form>

					
				</div>

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>