<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/I18">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<script type="text/javascript" src="res/js/jquery-1.7.1.js"
	charset="UTF-8"></script>
<script src="res/js/validate.js" type="text/javascript" charset="UTF-8"></script>
<title><s:text name="inicio" /></title>
<!-- Donde dice inicio debería ir una var que identifique el lugar -->
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<!-- Este es el div de contenidos -->
				<div id="content2">
					<br>
					<s:if test="recoveryPass">
						<div
							Style="margin-left: 35%; margin-top: 5%; margin-bottom: 15%; width: 310px; padding: 15px; border: 3px solid blue;">
							<form action="enviarDatos" method="post">
								<table>
									<tr>
										<td colspan="3">
											<h5 style="margin: 0;">
												<s:text name="recoveryPass" />
											</h5></td>
									</tr>
									<tr>
										<td colspan="3">
											<p style="font-size: x-small; font-family: sans-serif;">
												<s:text name="recoveryPassInfo" />
											</p></td>
									</tr>
									<tr>
										<td colspan="3"><s:fielderror>
												<s:param>error</s:param>
											</s:fielderror> <s:fielderror>
												<s:param>correo</s:param>
											</s:fielderror> <span class="ok_pass"> <s:actionmessage /> </span></td>
									</tr>
									<tr>
										<td><s:text name="correo" /></td>
										<td><input type="text" name="correo" /></td>
										<td><a href="#" class="tooltip" tabindex="-1"> <img
												src="res/img/ayuda.gif" alt="ayuda"> <span><s:text
														name="mailHelp" /> </span> </a></td>
									</tr>
									<tr>
										<td colspan="3" align="right" style="padding-right: 28px;">
											<input type="submit" value="<s:text name="recoveryPass" />" />
										</td>
									</tr>
								</table>
								<s:hidden name="password" value="-1"></s:hidden>
							</form>
						</div>
					</s:if>
					<s:elseif test="datosEnviados">
						<div
							Style="margin-left: 35%; margin-top: 5%; margin-bottom: 15%; width: 310px; padding: 15px; border: 3px solid blue;">
							<table>
								<tr>
									<td colspan="3">
										<h5 style="margin: 0;">
											<s:text name="recoveryPass" />
										</h5></td>
								</tr>
								<tr>
									<td colspan="3"><s:fielderror>
											<s:param>error</s:param>
										</s:fielderror> <span class="ok_pass"> <s:actionmessage /> </span></td>
								</tr>
								<tr>
									<td colspan="3" align="center"><s:a
											href="autenticarUsuario">
											<s:text name="home" />
										</s:a></td>
								</tr>
							</table>
						</div>
					</s:elseif>
					<s:elseif test="recoveryPassForm == true">
						<div
							Style="margin-left: 25%; margin-top: 5%; margin-bottom: 15%; width: 500px; padding: 15px;">
							<form action="cambiarClave" method="post" id="formModificarClave"
								name="modificarClave">
								<table style="margin-left: 20px;">
									<tr>
										<td colspan="4"><s:fielderror>
												<s:param>password</s:param>
											</s:fielderror></td>
									<tr>
									<tr>
										<td colspan="4">
											<h5 class="requerido">
												<s:text name="usuario.modificar.requerido" />
											</h5></td>
									<tr>
									<tr>
										<td><s:text name="usuario.modificar.clave.nueva" />
										</td>
										<td><input type="password" name="clave_nueva" id="pass" />
										</td>
										<td><h5 class="requerido">*</h5>
										</td>
										<td><span id="passstrength"></span>
										</td>
									<tr>
									<tr>
										<td><s:text name="usuario.modificar.clave.confirma" />
										</td>
										<td><input type="password" name="clave_nueva_confirme"
											id="pass2" />
										</td>
										<td><h5 class="requerido">*</h5>
										</td>
										<td><span id="passequal"></span> <br>
										</td>
									<tr>
									<tr>
										<td colspan="4"><s:token name="token" /> <s:hidden
												name="cuenta"></s:hidden> <input type="submit"
											id="cambiar_clave"
											value="<s:text name="usuario.modificar.clave"></s:text>">
										</td>
									<tr>
								</table>
							</form>
						</div>
					</s:elseif>
					<s:else>
						<div
							Style="margin-left: 35%; margin-top: 5%; margin-bottom: 15%; width: 310px; padding: 15px; border: 3px solid blue;">
							<form action="index" method="post">
								<table>
									<tr>
										<td colspan="3">
											<h5 style="margin: 0;">
												<s:text name="sesion" />
											</h5>
										</td>
									</tr>
									<tr>
										<td colspan="3"><s:fielderror>
												<s:param>error</s:param>
											</s:fielderror> <s:fielderror>
												<s:param>correo</s:param>
											</s:fielderror> <s:fielderror>
												<s:param>password</s:param>
											</s:fielderror>
										</td>
									</tr>
									<tr>
										<td><s:text name="user" /></td>
										<td><input type="text" name="correo" /></td>
										<td><a href="#" class="tooltip"> <img
												src="res/img/ayuda.gif" alt="ayuda"> <span><s:text
														name="mailHelp" /> </span> </a></td>
									</tr>
									<tr>
										<td><s:text name="pass" /></td>
										<td><input type="password" name="password" /></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="3" align="right" style="padding-right: 28px;">
											<s:a href="recuperarClave">
												<s:text name="accessSystem" />
											</s:a></td>
									</tr>
									<tr>
										<td colspan="3" align="right" style="padding-right: 28px;">
											<input type="submit" value="<s:text name="entrar" />" /></td>
									</tr>
								</table>
							</form>
						</div>
					</s:else>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>