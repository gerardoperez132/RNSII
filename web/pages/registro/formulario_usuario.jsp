<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/usuario">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<s:if test="%{#session.logueado != true}">
 	<META HTTP-EQUIV="Refresh" CONTENT="0;URL=../index.action">
</s:if>
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<title><s:text name="inicio" /></title>
</head>
<body>
	<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la página -->
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
				<%@include file="../layout/sidebar.jsp"%>
			</div>

			<!-- Este es el div de contenidos -->
			<div id="content">
			<table class="main">
			<tr>
				<td style="width: 350px;">
					<h4 style="margin: 0;"><s:text name="catalogo" /></h4>
				</td>	
				<td style="width: 350px;">
					<div style="text-align:right;">
						<s:text name="bienvenido" /> 
						<s:property value="%{#session.usuario.nombre}"/>
					</div>
				</td>							
			</tr>
			<tr>
				<td colspan="2">
					<s:text name="ente" />
					<s:property value="ente.nombre"/>
				</td>
			</tr>						
			</table>
			<hr>
			<s:if test="modificarContraseña = true">
				<form action="modificarClave" method="POST">
					<s:hidden name="modificarClave" value="%{true}"></s:hidden>				
					<input type="submit" value="<s:text name="modificar.clave"></s:text>"
					style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
				</form>
			
			</s:if>
			<s:elseif test="modificarDatos = true">
			</s:elseif>
			<s:else>
			</s:else>
				<form action="modificarClave" method="POST">
					<s:hidden name="modificarClave" value="%{true}"></s:hidden>				
					<input type="submit" value="<s:text name="modificar.clave"></s:text>"
						style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
				</form>
				modificarContraseña,modificarDatos
			</div>
			<%@include file="../layout/footer.jsp"%>
		</div>
	</div>
</body>
</s:i18n>
</html>