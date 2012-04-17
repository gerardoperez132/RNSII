<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/I18">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>
<script src="res/js/datos.js"  type="text/javascript" charset="UTF-8"></script>
<title><s:text name="form.entrada.registro.title"></s:text></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<!-- Esta es la barra lateral -->
				<div id="sidebar">
					<%@include file="../layout/sidebar.jsp"%>
				</div>
				<!-- Este es el div de contenidos -->
				<div id="content">
					<h3>
						<s:text name="form.entrada.registro.funcionalidades.title"></s:text>
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
							<s:set name="action" var="action">registrarEntrada</s:set>
							<s:set name="modificar" value="%{false}" />
							<s:set name="submit" var="submit">
								<s:text name="form.entrada.guardar"></s:text>
							</s:set>
						</s:if>
						<s:else>
							<s:set name="action" var="action">modificarEntrada</s:set>
							<s:set name="modificar" value="%{true}" />
							<s:set name="submit" var="submit">
								<s:text name="form.entrada.modificar"></s:text>
							</s:set>
						</s:else>
						<!-- Formulario para registrar o modificar entrada -->
						<form action="<s:property value="#action"></s:property>"
							method="post">
							<div id="tab2" class="tab_content">
								<h5 class="formulario">
									<s:if test="modificar!=true">
										<s:text name="form.entrada.registro.title"></s:text>
									</s:if>
									<s:else>
										<s:text name="form.entrada.modificar.title"></s:text>
									</s:else>
								</h5>
								<h6>
									<s:text name="form.entrada.funcionalidad.title">
										<s:param>
											<s:property value="funcionalidad.nombre" />
										</s:param>
									</s:text>
								</h6>
								<hr>
								<!-- Nombre de la entrada. -->
								<h5 class="formulario">
									<s:text name="form.entrada.nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>entrada.nombre</s:param>
								</s:fielderror>
								<s:textfield name="entrada.nombre" />
								<br>
								<!-- Descripción de la entrada. -->
								<h5 class="formulario">
									<s:text name="form.entrada.descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>entrada.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="entrada.descripcion" cols="30" rows="5" />
								<br>
								<h5 class="formulario">
									<s:text name="form.entrada.dato.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>tipodato</s:param>
								</s:fielderror>
								<s:select name="entrada.id_tipo_dato" list="tipoDatos"
									listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
									headerValue="%{getText('form.entrada.dato.select')}" id="tipoDato"></s:select>
								 
								<div id="string" style="visibility: hidden; position:fixed;">
									<h5 class="formulario">
										<s:text name="form.entrada.formato"/>
									</h5>
									<s:text name="form.entrada.longitud"/> <input type="text" name="longitud"> 
									<a href="#" class="tooltip">
										<img src="res/img/ayuda.gif"> 
										<span><s:text name="form.entrada.formato.string"/></span> 
									</a>
								</div>
								<div id="integer" style="visibility: hidden; position:fixed; ">
									<s:text name="form.entrada.formato"/><br>
										<s:text name="form.entrada.tipo"/>
										<select name="tipo"> 
											<option value="-1">Seleccione</option>
											<option value="1">Sin signo</option>
											<option value="2">Con signo</option>
										</select>
										<s:text name="form.entrada.longitud"/> <input type="text" name="longitud">
								</div>
								<s:hidden name="id_servicio_informacion"></s:hidden>
								<s:hidden name="id_funcionalidad"></s:hidden>
								<s:hidden name="id_entrada_salida"></s:hidden>
								<s:hidden name="modificar"></s:hidden>
								<s:hidden name="complejo"></s:hidden>
								<input type="submit"
									value="<s:property value="#submit"></s:property>" />
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