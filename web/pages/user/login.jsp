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
			<div id="menu"></div>

			<!-- Esta es la barra lateral -->
			<div id="sidebar"></div>

			<!-- Este es el div de contenidos -->
			<div id="content">
				<br>
				<div Style="margin-left: 35%; margin-top: 5%; margin-bottom:15%; border : 3px solid blue; width: 285px;">				
            	<s:form action="autenticarUsuario" cssStyle="padding-left:5px;">
            		<tr>
            			<td>
            			<h5>Control de acceso</h5>
            			</td>	
            		</tr>
            		<s:fielderror>
						<s:param>userName</s:param>
					</s:fielderror>
					<s:textfield name="userName" label="Usuario"/>
					<s:password name="password" label="Clave" />						
					<s:submit value="Entrar"/>	
				</s:form>
				</div>
						
				

			</div>

			<!-- Este es el pie de página -->
			<div id="footer"></div>
		</div>
	</div>
</body>
</html>