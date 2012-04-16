<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/I18">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script src="res/js/validate.js"  type="text/javascript" charset="UTF-8"></script>
<title><s:text name="inicio" /></title>
</head>
<body>
	<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
	<div id="sombra">
		<!-- Este es el div contenedor del maquetado de la página -->
		<div id="container">
			<%@include file="../layout/header.jsp"%>
			
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
			
			<s:if test="modificarClave == true">				
				<form action="modificarClave" method="post" id="formModificarClave" name="modificarClave">
				<table style="margin-top: 50px; margin-left: 20px;">
					<tr>
						<td colspan="4">							
							<s:fielderror>
								<s:param>password</s:param>
							</s:fielderror>
						</td>						
					<tr>
					<tr>
						<td colspan="4">							
							<h5 class="requerido"><s:text name="usuario.modificar.requerido"/></h5>
						</td>						
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.clave.actual"/></td>
						<td><input type="password" name="clave_actual" id="clave_actual"/></td>
						<td><h5 class="requerido">*</h5></td>
						<td><span id="passrequired"></span></td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.clave.nueva"/></td>
						<td><input type="password" name="clave_nueva" id="pass" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td><span id="passstrength"></span></td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.clave.confirma"/></td>
						<td><input type="password" name="clave_nueva_confirme" id="pass2" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td><span id="passequal"></span>  <br></td>
					<tr>					
					<tr>
						<td colspan="4">
							<s:token name="token" />
							<s:hidden name="modificarClave"></s:hidden>							
							<input type="submit" id="modificar_clave" value="<s:text name="usuario.modificar.clave"></s:text>">
						</td>						
					<tr>
				</table>
				</form>	
			</s:if>	
								
			
			<s:elseif test="modificarDatos == true">
				<form action="modificarDatos" method="post" id="formModificarDatos" name="modificarDatos">
				<table style="margin-top: 50px; margin-left: 20px;">
					<tr>
						<td colspan="4">							
							<s:fielderror>
								<s:param>datos</s:param>
							</s:fielderror>
						</td>						
					<tr>
					<tr>
						<td colspan="4">							
							<h5 class="requerido"><s:text name="usuario.modificar.requerido"/></h5>
						</td>						
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.nombres"/></td>
						<td><s:textfield name="usuario.nombre" id="nombre"/></td>
						<td><h5 class="requerido">*</h5></td>
						<td>
							<span id="nombre_required"></span>
							<s:fielderror>
								<s:param>nombres</s:param>
							</s:fielderror>
						</td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.apellidos"/></td>
						<td><s:textfield name="usuario.apellido" id="apellido" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td>
							<span id="apellido_required"></span>
							<s:fielderror>
								<s:param>apellidos</s:param>
							</s:fielderror>						
						</td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.cedula"/></td>
						<td><s:textfield name="usuario.cedula" id="cedula" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td>
							<span id="cedula_required"></span>
							<s:fielderror>
								<s:param>cedula</s:param>
							</s:fielderror>							
						</td>
					<tr>															
					<tr>
						<td colspan="4">
							<s:token name="token" />
							<s:hidden name="modificarDatos"></s:hidden>							
							<input type="submit" id="modificar_datos" value="<s:text name="usuario.modificar.datos"></s:text>">
						</td>						
					<tr>
				</table>
				</form>		
			</s:elseif>
			
			
			<s:else>	
				<span class="ok_pass"><s:actionmessage/></span>		
				<table>
					<tr>
						<td>
							<form action="prepararFormulario" method="POST">
								<s:hidden name="modificarClave" value="%{true}"></s:hidden>				
								<input type="submit" value="<s:text name="usuario.modificar.clave"></s:text>">
							</form>
						</td>
						<td>
							<form action="prepararFormulario" method="POST">
								<s:hidden name="modificarDatos" value="%{true}"></s:hidden>				
								<input type="submit" value="<s:text name="usuario.modificar.datos"></s:text>">
							</form>
						</td>
					</tr>
				</table>
			</s:else>
			</div>
			<%@include file="../layout/footer.jsp"%>
		</div>
	</div>
</body>
</s:i18n>
</html>