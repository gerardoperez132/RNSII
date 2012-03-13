<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/registro_salida_compleja">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<title><s:text name="registro.title"></s:text></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<!-- Esta es la barra lateral -->
				<div id="sidebar">
					<small>Paso 1 Registro de Servicio de Información</small><br>
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
						<form action="registrarSalidaCompleja" method="post">
							<div id="tab2" class="tab_content">
								<h5 class="formulario">
									<s:text name="registro.complejo"></s:text>
								</h5>
								<h6>
									<s:text name="funcionalidad.title">
										<s:param>
											<s:property value="funcionalidad.nombre" />
										</s:param>
									</s:text>
								</h6>
								<hr>
								<!-- Nombre de la entrada. -->
								<h5 class="formulario">
									<s:text name="nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>dato.nombre</s:param>
								</s:fielderror>
								<s:textfield name="dato.nombre" />
								<br>
								<!-- Descripción de la entrada. -->
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>dato.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="dato.descripcion" cols="30" rows="5" />
								<br>
								<h5 class="formulario">
									<s:text name="dato.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>tipodato</s:param>
								</s:fielderror>
								<s:select name="dato.id_tipo_dato" list="tipoDatos"
									listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
									headerValue="%{getText('dato.select')}"></s:select>
								<br> <br>
								<s:hidden name="idServicioInformacion"></s:hidden>
								<s:hidden name="idFuncionalidad"></s:hidden>
								<s:hidden name="complejo"></s:hidden>
								<input type="submit" value="<s:text name="guardar"></s:text>" />
							</div>
						</form>
					</div>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>