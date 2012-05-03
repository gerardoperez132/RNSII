<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/formulario_salida">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<title><s:text name="registro.title"></s:text></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<!-- Esta es la barra lateral -->				
				<%@include file="../layout/sidebar.jsp"%>
				
				<!-- Este es el div de contenidos -->
				<div id="content">
					<h3>
						<s:text name="registro.funcionalidades.title"></s:text>
					</h3>
					<hr>
					<ul class="tabs">
						<li><a><s:text name="tab1.title"></s:text> </a>
						</li>
						<li><a><s:text name="tab2.title"></s:text> </a></li>
						<li class="active"><a href="#tab3"><s:text
									name="tab3.title"></s:text> </a>
						</li>
						<li><a><s:text name="tab4.title"></s:text> </a>
						</li>
					</ul>
					<div class="tab_container">
						<s:if test="modificar!=true">
							<s:set name="action" var="action">registrarSalida</s:set>
							<s:set name="modificar" value="%{false}" />
							<s:set name="submit" var="submit">
								<s:text name="guardar"></s:text>
							</s:set>
						</s:if>
						<s:else>
							<s:set name="action" var="action">modificarSalida</s:set>
							<s:set name="modificar" value="%{true}" />
							<s:set name="submit" var="submit">
								<s:text name="modificar"></s:text>
							</s:set>
						</s:else>
						<!-- Formulario para registrar o modificar entrada -->
						<form action="<s:property value="#action"></s:property>"
							method="post">
							<div id="tab2" class="tab_content">
								<h5 class="formulario">
									<s:if test="modificar!=true">
										<s:text name="registro.title"></s:text>
									</s:if>
									<s:else>
										<s:text name="modificar.title"></s:text>
									</s:else>
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
									<s:param>salida.nombre</s:param>
								</s:fielderror>
								<s:textfield name="salida.nombre" />
								<br>
								<!-- Descripción de la entrada. -->
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>salida.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="salida.descripcion" cols="30" rows="5" />
								<br>
								<h5 class="formulario">
									<s:text name="dato.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>tipodato</s:param>
								</s:fielderror>
								<s:select name="salida.id_tipo_dato" list="tipoDatos"
									listKey="id_tipo_dato" listValue="nombre" headerKey="-1"
									headerValue="%{getText('dato.select')}"></s:select>
								<br> 
								
								
								<s:if test="complejo!=true">
								
								<h5 class="formulario">
									<s:text name="form.salida.formato"/>
								</h5>
								<s:fielderror>
									<s:param>formato</s:param>
								</s:fielderror>								
								<select name="salida.id_formato">
									<s:set var="idF" value="salida.id_formato"/>
									<optgroup label="">
										<option value="-1">
											<s:text name="form.salida.formato.select"></s:text>
										</option>
									</optgroup>
									<s:iterator value="tipoDatos" status="td_status">										
										<s:set var="id_td" value="id_tipo_dato"/>
										<s:if test="hasformatted == true">
										<optgroup label="<s:property value="nombre"/>">
											<s:iterator value="formatos">
												<s:if test="%{#id_td==id_tipo_dato}">
													<s:set var="cen" value="true"/>
													<option  value="<s:property value="id_formato"/>" 
													<s:if test="%{#idF == id_formato}">selected="selected"</s:if>>
														<s:property value="formato"/>
													</option>
												</s:if>										
											</s:iterator>											
										</optgroup>
										</s:if>										
									</s:iterator>
								</select>
								
								<h5 class="formulario">
									<s:text name="form.salida.longitud"/>
								</h5>
								<s:fielderror>
									<s:param>longitud</s:param>
								</s:fielderror>
								<s:textfield name="salida.longitud" maxlength="10" id="longitud"/>
								
								</s:if>
								
								<br>
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