<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/registro_entrada_simple">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<title><s:text name="registro.title"></s:text></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la p�gina -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la p�gina -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<!-- Esta es la barra lateral -->
				<div id="sidebar">
					<small>Paso 1 Registro de Servicio de Informaci�n</small><br>
					<br> <big>Paso 2 Registro de Funcionalidad(es)</big> <br>
					<br> <small>Paso 3 Registro de Entradas/Salidas</small><br>
					<br> <small>Paso 4 Verificar y guardar</small>
				</div>
				<!-- Este es el div de contenidos -->
				<div id="content">
					<h3>
						<s:text name="registro.funcionalidades.title"></s:text>
					</h3>
					<hr>
					<ul class="tabs">
						<li><a href="#tab1"><s:text name="tab1.title"></s:text> </a>
						</li>
						<li class="active"><a><s:text name="tab2.title"></s:text>
						</a></li>
						<li><a href="#tab3"><s:text name="tab3.title"></s:text> </a>
						</li>
						<li><a href="#tab4"><s:text name="tab4.title"></s:text> </a>
						</li>
					</ul>
					<div class="tab_container">
						<s:if test="modificar!=true">
							<!-- Formulario para registrar entrada -->
							<form action="registrarEntradaSimple" method="post">

								<div id="tab2" class="tab_content">

									<h5 class="formulario">Registro de Entrada</h5>
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
									<!-- Descripci�n de la entrada. -->
									<h5 class="formulario">Descripci�n:</h5>
									<s:fielderror>
										<s:param>dato.descripcion</s:param>
									</s:fielderror>
									<s:textarea name="dato.descripcion" cols="30" rows="5" />

									<br>


									<h5 class="formulario">Tipo de dato:</h5>
									<s:fielderror>
										<s:param>tipodato</s:param>
									</s:fielderror>
									<s:select name="dato.id_tipo_dato" list="tipoDatos"
										listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
										headerValue="Seleccione"></s:select>

									<br> <br>
									<s:hidden name="idServicioInformacion"></s:hidden>
									<s:hidden name="idFuncionalidad"></s:hidden>
									<s:hidden name="id_dato"></s:hidden>
									<input type="submit" value="Registrar Entrada" />

								</div>
							</form>
						</s:if>
						<s:else>
							<!-- Formulario para modificar entrada -->
							<form action="modificarEntrada" method="post">

								<div id="tab2" class="tab_content">

									<h5 class="formulario">Modificar Entrada</h5>
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
									<!-- Descripci�n de la entrada. -->
									<h5 class="formulario">Descripci�n:</h5>
									<s:fielderror>
										<s:param>dato.descripcion</s:param>
									</s:fielderror>
									<s:textarea name="dato.descripcion" cols="30" rows="5" />

									<br>


									<h5 class="formulario">Tipo de dato:</h5>
									<s:fielderror>
										<s:param>tipodato</s:param>
									</s:fielderror>
									<s:select name="dato.id_tipo_dato" list="tipoDatos"
										listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
										headerValue="Seleccione"></s:select>

									<br> <br>
									<s:hidden name="idServicioInformacion"></s:hidden>
									<s:hidden name="idFuncionalidad"></s:hidden>
									<s:hidden name="id_dato"></s:hidden>
									<s:hidden name="modificar" value="%{true}"></s:hidden>
									<input type="submit" value="Modificar Entrada" />

								</div>
							</form>
						</s:else>
					</div>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>