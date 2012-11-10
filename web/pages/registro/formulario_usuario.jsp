<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/messages">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/SRSI/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/formulario_usuario.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>
<title><s:text name="inicio" /></title>
	</head>
	<body>
		<div class="container">
			<%@include file="../layout/header.jsp"%>
			<!-- Esta es la barra lateral -->
			<%@include file="../layout/sidebar.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">
				<%@include file="../layout/bienvenido.jsp"%>
				<div class="pasos">
					<label class="pasos_lbl"><s:text name="configuración" /></label>
				</div>
				<s:if test="modificarClave">
					<form action="modificarClave" method="post" id="formModificarClave" name="modificarClave">
						<div class="administracion_capa">
							<table>
								<tr>
									<td colspan="4"><s:fielderror>
											<s:param>password</s:param>
										</s:fielderror></td>
								</tr>
								<tr>
									<td colspan="4">
										<h5 class="requerido">
											<s:text name="usuario.modificar.requerido" />
										</h5>
									</td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.clave.actual" /></td>
									<td><input type="password" name="clave_actual"
										id="clave_actual" /></td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="passrequired"></span></td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.clave.nueva" /></td>
									<td><input type="password" name="clave_nueva" id="pass" /></td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="passstrength"></span></td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.clave.confirma" /></td>
									<td><input type="password" name="clave_nueva_confirme" id="pass2" /></td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="passequal"></span> <br></td>
								</tr>
								<tr>
									<td colspan="4"><s:token name="token" />
										<s:hidden name="modificarClave"/>
										<input type="submit" id="modificar_clave" value="<s:text name="usuario.modificar.clave"/>">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</s:if>
				<s:elseif test="modificarDatos">
					<div class="administracion_capa">
						<form action="modificarDatos" method="post"
							id="formModificarDatos" name="modificarDatos">
							<table style="margin-top: 50px; margin-left: 20px;">
								<tr>
									<td colspan="4"><s:fielderror>
											<s:param>datos</s:param>
										</s:fielderror></td>
								</tr>
								<tr>
									<td colspan="4">
										<h5 class="requerido">
											<s:text name="usuario.modificar.requerido" />
										</h5>
									</td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.nombres" /></td>
									<td><s:textfield name="usuario.nombre" id="nombre" /></td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="nombre_required"></span> <s:fielderror>
											<s:param>nombres</s:param>
										</s:fielderror></td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.apellidos" /></td>
									<td><s:textfield name="usuario.apellido" id="apellido" /></td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="apellido_required"></span> <s:fielderror>
											<s:param>apellidos</s:param>
										</s:fielderror></td>
								</tr>
								<tr>
									<td><s:text name="usuario.modificar.cedula" /></td>
									<td>																				
										<s:select name="usuario.nacionalidad" id="usuario.nacionalidad"
										   list="nacionalidad" listKey="id_nacionalidad"
										   listValue="nombre" ></s:select>
										<s:textfield name="usuario.cedula" id="cedula" maxlength="9" style="width: 130px;"/>										
									</td>
									<td><h5 class="requerido">*</h5></td>
									<td><span id="cedula_required"></span> <s:fielderror>
											<s:param>cedula</s:param>
										</s:fielderror></td>
								</tr>
								<tr>
									<td colspan="4"><s:token name="token" />
										<s:hidden name="modificarDatos"/>
										<input type="submit" id="modificar_datos" value="<s:text name="usuario.modificar.datos"/>">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</s:elseif>
				<s:else>
					<div class="administracion_capa">
						<span class="ok_pass"><s:actionmessage /></span>						
					</div>
				</s:else>				
			</div>
		</div>
		<div style="clear: both"></div>
		<div class="vacio"></div>
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>
