<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="robots" content="index, follow">
		<meta name="description" content="Registro Nacional de Servicios Interoperables del Estado Venezolano" />
 		<meta name="keywords" content="Servicios Interoperables del Estado Venezolano, rnsii, servicios web, interoperabilidad Venezuela, interoperabilidad Gobierno de Venezuela,UDDI Venezuela" />
		<!-- CSS (required) -->
		<link rel="stylesheet" type="text/css" href="res/css/style2.css">
		<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
		<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
		<link rel="stylesheet" type="text/css" href="res/css/table2.css">
		<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
		<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
		<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
		<!-- JS (required) -->
		<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/tabs.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
		<script type="text/javascript" src="res/js/funciones_ge.js" charset="UTF-8"></script>
		
		<%@include file="../layout/header_joomla.jsp"%>
		
		<title><s:text name="inicio" /></title>
	</head>
	<body class="bg clearfix">
		<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="../layout/menus.jsp"%>
		<!-- Este es el div de contenidos -->
		<div id="content">
			<div class="main admin">
				<%@include file="login_admin.jsp"%>	
			</div>
		</div>
		<div class="clr"></div>
		</div>
		</div>
		<!-- Footer -->	
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>
