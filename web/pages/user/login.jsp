<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/login">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<title><s:text name="inicio" /></title>
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
</head>
<body>

	<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la página -->
		<div id="container">

			<!-- Este es el div de la cabecera -->
			<div id="header">
				<img src="res/img/header.png" width="880" height="70"
					alt="Cintillo Gobierno Bolivariano" /> <img
					src="res/img/mio.png" width="874" height="116"
					alt="Marco de Interoperabilidad" style="border: 3px solid #57cedc;" />
			</div>

			<!-- Este es el div de los menúes -->
			<div id="menu">
				<h5 align="center" style="color:#f5f5ee;">
				<s:text name="titulo" />
				</h5>
			</div>

			<!-- Este es el div de contenidos -->
			<div id="content2">
				<br>
				<div Style="margin-left: 35%; margin-top: 5%; margin-bottom:15%; 
							width: 285px; padding: 15px;  border : 3px solid blue;">				
            	<form action="index" method="post">
            		<table>
            		<tr>
            			<td colspan="3">
            			<h5 style="margin: 0;"><s:text name="sesion" /></h5>
            			</td>	
            		</tr>
            		<tr>
            			<td colspan="3">
            			<s:fielderror>
            			<s:param>error</s:param>
            			</s:fielderror>
            			<s:fielderror>
            				<s:param>correo</s:param>
            			</s:fielderror>
            			<s:fielderror>
            				<s:param>password</s:param>
            			</s:fielderror>
            			</td>            				
            		</tr>						
					<tr>
						<td><s:text name="user" /></td>
            			<td><input type="text" name="correo"/></td>            			
					</tr>            		
            		<tr>
						<td><s:text name="pass" /></td>
            			<td><input type="password" name="password"/></td>            				
					</tr>	
					<tr>
            			<td colspan="2">
            			<input type="submit" value="Entrar"/>
            			</td>	
            		</tr>					
					</table>	
				</form>
				</div>
						
				

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</s:i18n>
</html>