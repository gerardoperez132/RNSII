<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/RNSII/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/RNSII/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/funcionalidades.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<%@include file="../header_joomla.jsp" %>
<title><s:text name="inicio" /></title>
	</head>
	<body class="bg clearfix">		
	<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="../menus.jsp"%>
		<div class="content">			
			<!-- Esta es la barra lateral -->
			<%@include file="../sidebar.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">
				<h1><a><s:text name="titulo2" /></a></h1>
				<div class="register_success">
					<table>
						<tr>
							<td>
								<p>
									<s:actionmessage/>
								</p>
							</td>							
						</tr>
					</table>				
				</div>
				<form action="home" method="post">
					<input type="submit" class="button_h" value="<s:text name='home'/>">
				</form>		
			</div>
		</div>
		<div class="clr"></div>
		<%@include file="../breadcrumbs.jsp"%>	
		<%@include file="../links_over_footer.jsp"%>	
		</div>		
	</div>		
	<!-- Footer -->	
	<%@include file="../footer.jsp"%>
	</body>
</s:i18n>
</html>
