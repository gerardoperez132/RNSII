<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/registro_servicio_informacion">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<s:if test="tab==1">
	<s:set name="action" value="%{'registrarDescripcionGeneral'}" />
	<s:set name="submit" value="%{getText('guardar')}" />
	<s:set name="title" value="%{getText('registro.title')}" />
</s:if>
<title><s:text name="title"></s:text>
</title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<%@include file="../layout/sidebar.jsp"%>
				<div id="content">
					<small><s:text name="title" /> / <strong>Paso 1:</strong>
						/ Paso 2 / Paso 3</small>
					<h3>
						<s:text name="title"></s:text>
					</h3>
					<hr>
					<ul class="tabs">
						<s:if test="%{tab==1}">
							<li class="active"><a href="#tab1"><s:text
										name="tab1.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<li><a href="#tab1"><s:text name="tab1.title"></s:text>
							</a></li>
						</s:else>
						<s:if test="%{tab==2}">
							<li class="active"><a href="#tab2"><s:text
										name="tab2.title"></s:text> </a>
							</li>
						</s:if>
						<s:else>
							<li><a href="#tab2"><s:text name="tab2.title"></s:text>
							</a>
							</li>
						</s:else>
						<s:if test="%{tab==3}">
							<li class="active"><a href="#tab3"><s:text
										name="tab3.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<li><a href="#tab3"><s:text name="tab3.title"></s:text>
							</a></li>
						</s:else>
						<s:if test="%{tab==4}">
							<li class="active"><a href="#tab4"><s:text
										name="tab4.title"></s:text> </a></li>
						</s:if>
						<s:else>
							<li><a href="#tab4"><s:text name="tab4.title"></s:text>
							</a></li>
						</s:else>
					</ul>
					<div class="tab_container">
						<div id="tab1" class="tab_content">
							<form action="<s:property value="#action"/>" method="post"
								enctype="multipart/form-data">
								<p class="formulario">
									<s:text name="tab1.subtitle" />
								</p>
								<small><s:text name="tab1.description"></s:text> </small>
								<hr>
								<h5 class="formulario">
									<s:text name="sector.title" />
								</h5>
								<s:fielderror>
									<s:param>sector</s:param>
								</s:fielderror>
								<s:select name="sector" list="sectores" listKey="id_sector"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('sector.select')}"></s:select>
								<h5 class="formulario">
									<s:text name="nombre.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.nombre</s:param>
								</s:fielderror>
								<s:textfield name="servicio.nombre" />
								<h5 class="formulario">
									<s:text name="descripcion.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>servicio.descripcion</s:param>
								</s:fielderror>
								<s:textarea name="servicio.descripcion" cols="40" rows="10" />
								<h5 class="formulario">
									<s:text name="area.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>area</s:param>
								</s:fielderror>
								<s:checkboxlist list="areas" listValue="nombre" name="area"
									listKey="id_area" />
								<h5 class="formulario">
									<s:text name="estado.title"></s:text>
								</h5>
								<s:fielderror>
									<s:param>estado</s:param>
								</s:fielderror>
								<s:select name="estado" list="estados" listKey="id_estado"
									listValue="nombre" headerKey="-1"
									headerValue="%{getText('estado.select')}"></s:select>
								<s:token name="token" />
								<s:hidden name="tab" value="1"></s:hidden>
								<input type="submit" value='<s:property value="#submit"/>' />
						</div>
					</div>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>