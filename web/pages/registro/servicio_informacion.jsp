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

				<h5 class="formulario">Servicio de Información: "<s:property value="servicio.nombre" />"</h5>				
				<h5 class="formulario">Descripcion: "<s:property value="servicio.descripcion" />"</h5>
				<h5 class="formulario">id: "<s:property value="idServicioInformacion" />"</h5>
				 
				<hr>
				<s:fielderror>
					<s:param>funcionalidades</s:param>
				</s:fielderror>
				<s:if test="funcionalidades.size() > 0">
					<div>
						<p>Funcionalidades cargadas</p>
						<table border="1">
							<tr>
								<th>Número</th>
								<th>Nombre</th>
								<th>Fecha</th>
								<th>Acciones</th>
							</tr>
							<s:iterator value="funcionalidades">
								<tr>
									<td><s:property value="id_funcionalidad" /></td>
									<td><s:property value="nombre" /></td>
									<td><s:property value="fecha_creado" /></td>
									<td><a href="#">Editar</a> <a href="#">Eliminar</a></td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</s:if>
				<s:else>
					<p>No hay funcionalidades cargadas</p>
				</s:else>
				
				<s:url id="registrarFuncionalidad" action="prepararFuncionalidad"></s:url>
				<s:a href="%{registrarFuncionalidad}" cssClass="enlace">
					<input type="button" value="Registrar Funcionalidad">
				</s:a>
				
				<form action="prepararFuncionalidad" method="POST">
					<s:hidden name="idServicioInformacion" ></s:hidden>
					<input type="submit" value="Registrar" />
				</form>

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>