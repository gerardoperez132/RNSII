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
<link rel="stylesheet" type="text/css" href="res/css/table.css">


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

				<h4>Registro de Funcionalidades</h4>
				<hr>

				<h5 class="formulario">
					Servicio de Información: "
					<s:property value="servicio.nombre" />
					"
				</h5>
				<h5 class="formulario">
					Descripcion: "
					<s:property value="servicio.descripcion" />
					"
				</h5>
				<h5 class="formulario">
					Id: "
					<s:property value="idServicioInformacion" />
					"
				</h5>

				<hr>
				<s:fielderror>
					<s:param>funcionalidades</s:param>
				</s:fielderror>

				<form action="prepararFuncionalidad" method="POST">
					<s:hidden name="idServicioInformacion"></s:hidden>
					<input type="submit" value="Registrar" />
				</form>

				<table class="result">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nombre</th>
							<th scope="col">Fecha</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>

					<tfoot>
						<tr class="hv">
							<th scope="row">Total</th>
							<td colspan="3"><s:property value="datos.size" />
								funcionalidades cargadas</td>
						</tr>
					</tfoot>

					<s:url id="modificarFuncionalidad" action="modificarFuncionalidad"></s:url>

					<tbody>
						<s:if test="funcionalidades.size() > 0">

							<s:iterator value="funcionalidades" status="result_Status">
								<tr
									class="<s:if test="#result_Status.odd == true ">odd</s:if><s:else>hv</s:else>">
									<th><s:property value="id_funcionalidad" /></th>
									<td><s:property value="nombre" /></td>
									<td><s:property value="fecha_creado" /></td>
									<td><form action="modificarFuncionalidad" method="POST">
											<s:hidden name="idFuncionalidad" value="%{id_funcionalidad}"></s:hidden>
											<input type="submit" value="Editar"
												style="background: none; border: 0;" /> Eliminar
										</form></td>
								</tr>
							</s:iterator>

						</s:if>
						<s:else>
							<tr class="hv">
								<th class="row" colspan="4">Aún no hay Funcionalidades
									cargadas para este Servicio</th>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>