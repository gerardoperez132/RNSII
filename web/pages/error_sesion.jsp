<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
	<s:i18n name="ve/gob/cnti/rnsii/i18n/errors">
		<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="robots" content="index, follow">
		<meta name="description" content="Registro Nacional de Servicios Interoperables del Estado Venezolano" />
 		<meta name="keywords" content="Servicios Interoperables del Estado Venezolano, rnsii, servicios web, interoperabilidad Venezuela, interoperabilidad Gobierno de Venezuela,UDDI Venezuela" />
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css"
	href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="res/js/tabs.js"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js"></script>
<title><s:text name="inicio" /></title>
		</head>
		<body class="bg clearfix">
			<div class="container">				
				<div id="content">
					<!-- Ruta de navegación -->
					<div class="main">
						<div class="RutaNavegacion">
							<a href="RNSII.action"><s:text name="catalogo" /></a>
						</div>
						<br> <br>
						<h1>
							<s:text name="error.sesion.expired" />
						</h1>										
					</div>
				</div>
			</div>			
		</body>
	</s:i18n>
</s:i18n>
</html>
