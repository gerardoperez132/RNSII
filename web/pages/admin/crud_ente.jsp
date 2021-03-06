<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="robots" content="index, follow">
		<meta name="description" content="Registro Nacional de Servicios Interoperables del Estado Venezolano" />
 		<meta name="keywords" content="Servicios Interoperables del Estado Venezolano, rnsii, servicios web, interoperabilidad Venezuela, interoperabilidad Gobierno de Venezuela,UDDI Venezuela" />
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/RNSII/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script type="text/javascript" src="/RNSII/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/formulario_ente.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<%@include file="../layout/header_joomla.jsp" %>

<title><s:text name="inicio" /></title>
	</head>
	<body class="bg clearfix">
	<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="../layout/menus.jsp"%>
		<div class="content">
		
			<!-- Esta es la barra lateral -->
			<%@include file="sidebar_admin.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">
				<h1><a><s:text name="titulo" /></a></h1>				
				<s:if test="accion_ente==1">				
<!-- CREAR ENTE -->
					<div class="pasos">
						<label class="pasos_lbl"><s:text name="configuraci�n" /> - <s:text name="ente.registro" /></label>
					</div>
					<form action="registrar_ente_execute" method="post"	id="registrar_ente" name="crud_ente">
						<%@include file="layout/formulario_ente.jsp"%>
					</form>
				</s:if>
				<s:elseif test="accion_ente==2">
<!-- LEER ENTE -->					
				</s:elseif>
				<s:elseif test="accion_ente==3">
<!-- MODIFICAR ENTE -->
					<form action="modificar_ente_adm" method="post"	name="crud_ente">
						<%@include file="layout/formulario_ente.jsp"%>
					</form>
				</s:elseif>				
				<s:elseif test="accion_ente==4">
<!-- LISTAR ENTES -->
				</s:elseif>
				<s:elseif test="accion_ente==-1">
<!-- Capturando alg�n error -->
					<h1>
						<s:text name="error.unexpected" />
					</h1>
					<h3>Por favor, informe de este error al administrador del
						sistema o al personal competente en soporte t�cnico.</h3>
					<h3>Gracias por su cooperaci�n.</h3>
					<h1>Detalles t�cnicos</h1>
					<p>
						<s:actionmessage />
					</p>					
				</s:elseif>
				<s:else>
<!-- Mensaje del Controlador -->
					<div class="administracion_capa">
						<span class="ok_pass"><s:actionmessage /></span>						
					</div>
				</s:else>				
			</div>
		</div>
		
		
		<div class="clr"></div>
		<%@include file="../layout/breadcrumbs.jsp"%>	
		<%@include file="../layout/links_over_footer.jsp"%>	
		</div>		
		</div>		
		<!-- Footer -->	
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>