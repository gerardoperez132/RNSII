<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<title>SRSI - Inicio</title>
<!-- Donde dice inicio deber�a ir una var que identifique el lugar -->
</head>
<body>

	<!-- Este es el div de la sombra del contenedor del maquetado de la p�gina -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la p�gina -->
		<div id="container">

			<!-- Este es el div de la cabecera -->
			<div id="header">
				<img src="res/img/header.png" width="880" height="70"
					alt="Cintillo Gobierno Bolivariano" /> <img
					src="res/img/mio.png" width="874" height="116"
					alt="Marco de Interoperabilidad" style="border: 3px solid #57cedc;" />
			</div>

			<!-- Este es el div de los men�es -->
			<div id="menu"></div>

			<!-- Esta es la barra lateral -->
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">
				<br>
				<div Style="margin-left: 10%; margin-top: 5%; margin-bottom:15%; width: 400px;">				
            	<form action="index" method="post">
            		<table>
            		<tr>
            			<td colspan="3">
            			<h5>Control de acceso</h5>
            			</td>	
            		</tr>
            		<tr>
            			<td colspan="3">
            			<s:fielderror>
            			<s:param>error</s:param>
            			</s:fielderror>
            			</td>            				
            		</tr>						
					<tr>
						<td>Correo:</td>
            			<td><input type="text" name="correo"/></td>
            			<td>
	            			<s:fielderror>
	            				<s:param>correo</s:param>
	            			</s:fielderror>
            			</td>
					</tr>            		
            		<tr>
						<td>Clave:</td>
            			<td><input type="password" name="password"/></td>
            			<td>
	            			<s:fielderror>
	            				<s:param>password</s:param>
	            			</s:fielderror>
            			</td>	
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

			<!-- Este es el pie de p�gina -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>