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
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>

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

				<h3>Registro de Funcionalidades</h3>

				<hr>

				<ul class="tabs">
					<li><a href="#tab1">Descripción General</a></li>
					<li><a href="#tab2">Entradas</a></li>
					<li><a href="#tab3">Salidas</a></li>
					<li><a href="#tab4">Guardar</a></li>
				</ul>
				<form action="registrarFuncionalidad" method="POST">
					<div class="tab_container">
						<div id="tab1" class="tab_content">

							<p>Descripción General de la Funcionalidad</p>

							<h5 id="formulario">Nombre:</h5>
							<s:textfield labelposition="top" name="funcionalidad.nombre" />
							<!-- Nombre de la funcionalidad u operación del servicio. -->

							<br>
							<h5 id="formulario">Descripción (Pre-Condiciones):</h5>
							<s:textarea name="funcionalidad.descripcion" cols="40" rows="10" />
							<!-- Descripción de la funcionalidad u operación del servicio. -->
							<br>
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
							<input type="submit" value="Registrar" />
						</div>
				</form>

				<div id="tab2" class="tab_content">
					<h5 id="formulario">Formato:</h5>
					<s:textfield labelposition="top" name="formato_entrada" />

					<h5 id="formulario">Tipo de dato asociado:</h5>
					<select>
						<optgroup></optgroup>
						<option value="-1">Ninguno</option>
						<s:iterator value="datosPadres">

							<s:set name="padre" value="id_dato"></s:set>
							<s:set name="nombrePadre" value="nombre"></s:set>
							<optgroup label="<s:property value="nombre"/>">

								<s:iterator value="datosHijos">
									<s:if test="%{#padre == id_padre}">

										<option value="<s:property value="id_dato"/>">
											<s:property value="nombre" />
										</option>

									</s:if>
								</s:iterator>
							</optgroup>
						</s:iterator>
					</select>

				</div>

				<div id="tab3" class="tab_content">
					<h5 id="formulario">Formato:</h5>
					<s:textfield labelposition="top" name="formato_salida" />

					<h5 id="formulario">Tipo de dato asociado:</h5>
					<select>
						<optgroup></optgroup>
						<option value="-1">Seleccione</option>
						<s:iterator value="datosPadres">

							<s:set name="padre" value="id_dato"></s:set>
							<s:set name="nombrePadre" value="nombre"></s:set>
							<optgroup label="<s:property value="nombre"/>">

								<s:iterator value="datosHijos">
									<s:if test="%{#padre == id_padre}">

										<option value="<s:property value="id_dato"/>">
											<s:property value="nombre" />
										</option>

									</s:if>
								</s:iterator>
							</optgroup>
						</s:iterator>
					</select>
				</div>

				<div id="tab4" class="tab_content"></div>

			</div>


		</div>

		<!-- Este es el pie de página -->
		<div id="footer"></div>
	</div>
	</div>
</body>
</html>