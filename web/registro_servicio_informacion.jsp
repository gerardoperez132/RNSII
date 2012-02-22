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
				
				<small>Paso 1 Registro de Servicio de Informaci�n</small><br><br>
				<small>Paso 2 Registro de Funcionalidad(es)</small>	<br>		<br>	
				<small>Paso 3 Registro de Entradas/Salidas</small><br><br>
				<small>Paso 4 Verificar y guardar</small>
				
			
			</div>
			
			

			<!-- Este es el div de contenidos -->
			<div id="content">

				<h3>Registro de Servicio de Informaci�n</h3>

				<hr>

				<ul class="tabs">
					<li><a href="#tab1">Descripci�n General</a></li>
					<li><a href="#tab2">Aspectos legales</a></li>
					<li><a href="#tab3">Descripci�n t�cnica</a></li>
					<li><a href="#tab4">Guardar</a></li>					
				</ul>
			
				<div class="tab_container">
					<div id="tab1" class="tab_content">
						
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


					</div>
					
					<div id="tab2" class="tab_content">
					   <p id="formulario">Aspectos legales que rigen al servicio</p>
						<small>Incluir documento de acuerdo de nivel de
							servicio(SLA) por el qu� se regir� este Servicio de Informaci�n.</small>
		
						<h5 id="formulario">Nombre del Documento:</h5>
						<s:textfield labelposition="top" name="nombre_documento" />
		
						<h5 id="formulario">Adjuntar SLA:</h5>
						<s:file name="documento"></s:file>
					</div>
					
					<div id="tab3" class="tab_content">

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
						<h5 id="formulario">Tipo de Intercambio:</h5>
						<select>
						<optgroup></optgroup> 
						<option value="-1">Seleccione</option>
						<s:iterator value="intercambiosPadres">
							
							<s:set name="padre" value="id_intercambio"></s:set>
							<s:set name="nombrePadre" value="nombre"></s:set>
							<optgroup label="<s:property value="nombre"/>"> 
							
							<s:iterator value="intercambiosHijos">						
								<s:if test="%{#padre == id_padre}">	
									
									<option value="<s:property value="id_intercambio"/>">
										<s:property value="nombre"/>
									</option>		
									
								</s:if>
							</s:iterator>
							</optgroup>
						</s:iterator>
						</select>
					</div>
					
					<div id="tab4" class="tab_content">
					   
					</div>
					
				</div>


			</div>

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>