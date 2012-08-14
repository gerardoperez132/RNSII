<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/funcionalidades.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
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
				<div class="Titulo" style="width: 800px;">
					<h1>
						<s:text name="titulo2" />
					</h1>
				</div>
				<div class="busqueda" style="width: 800px;">
					<form method="post" action="Buscar_Servicio">
						<label><s:text name="buscarServicio" /></label>
						<s:textfield name="cadena" id="buscar"
							cssClass="inputBusqueda buscar" style="width:640px;" />
						<input type="submit" class="submit" value="&nbsp;" />
					</form>
				</div>
				<table class="main_user">
					<tr>
						<td>
							<div>
								<h4 style="margin: 0;">
									<s:text name="bienvenido" />
									<s:property value="%{#session.usuario.nombre}" />
								</h4>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<h4 style="margin: 0;">
								<s:text name="ente" />
								<s:property value="%{#session.ente_sesion.nombre}" />
							</h4>
						</td>
					</tr>
				</table>
				<div class="pasos">
					<table>
						<tr>
							<td><small> <strong> <s:text name="paso2.1" /> <s:text name="funcionalidades" /> </strong> </small></td>
							<td><s:if test="modificar">
									<form action="prepararModificarServicioInformacion" method="POST">
										<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>
										- <input type="submit" value='<s:text name="paso1" />' class="btn_form" />
									</form>
								</s:if>
							</td>
						</tr>
					</table>
				</div>
				<table class="results" style="width: 800px;">
					<tr>
						<th colspan="2"><s:text name="datos_basicos_Servicio_informacion" /></th>
					</tr>
					<tr>
						<td class="alt"><s:text name="n_servicio" /></td>
						<td class="alt2"><s:property value="id_servicio_informacion" /></td>
					</tr>
					<tr>
						<td class="alt"><s:text name="servicio.title" /></td>
						<td class="alt2"><s:property value="servicio.nombre" /></td>
					</tr>
					<tr>
						<td class="alt"><s:text name="descripcion.title" /></td>
						<td class="alt2"><s:property value="servicio.descripcion" /></td>
					</tr>
				</table>
				<hr>
				<s:fielderror>
					<s:param>funcionalidades</s:param>
				</s:fielderror>
				<table>
					<tr>
						<td>
							<form action="prepararFuncionalidad" method="POST">
								<s:hidden name="id_servicio_informacion"></s:hidden>
								<s:hidden name="modificar"></s:hidden>
								<s:token name="token" />
								<input type="submit" value="<s:text name="funcionalidad.add"/>" />
							</form>
						</td>
						<td>
							<form action="home" method="POST" onsubmit="return false;" id="salir">
								<input type="submit" value="<s:text name="terminar"/>" onclick="salir('<s:property value="servicio.nombre" />');" />
							</form>
						</td>
					</tr>
				</table>
				<table class="results" style="width: 800px;">
					<tbody>
						<tr>
							<th colspan="4"><s:text name="funcionalidades.title" /></th>
						</tr>
						<tr>
							<th><s:text name="id"></s:text></th>
							<th><s:text name="nombre"></s:text></th>
							<th><s:text name="fecha"></s:text></th>
							<th><s:text name="acciones"></s:text></th>
						</tr>
						<s:if test="funcionalidadesPublicables.size() > 0">
							<s:set name="contador" value="%{0}" />
							<s:iterator value="funcionalidadesPublicables"
								status="result_Status">
								<tr id="node-<s:property value="#result_Status.index"/>">
									<th><s:property value="funcionalidad.id_funcionalidad" /></th>
									<td><s:property value="funcionalidad.nombre" /></td>
									<td><s:date name="funcionalidad.fecha_creado" format="d'/'MM'/'yyyy" /></td>
									<td>
										<table style="margin: 0; padding: 0;">
											<tr style="margin: 0; padding: 0;">
												<td style="margin: 0; padding: 0;">
													<form action="prepararFuncionalidad" method="POST">
														<s:hidden name="id_funcionalidad" value="%{funcionalidad.id_funcionalidad}"></s:hidden>
														<s:hidden name="id_servicio_informacion"></s:hidden>
														<input type="submit" value="<s:text name="modificar"/>" style="font-size: 0.9em;" />
													</form>
												</td>
												<td style="margin: 0; padding: 0;">
													<form action="eliminarFuncionalidad" method="POST"
														onsubmit="return false;"
														id="id_<s:property value="#result_Status.index" />">
														<s:hidden name="id_funcionalidad" value="%{funcionalidad.id_funcionalidad}"></s:hidden>
														<s:hidden name="id_servicio_informacion"></s:hidden>
														<s:token name="token" />
														<input type="submit" value="<s:text name="eliminar" />" style="font-size: 0.9em;" onclick="eliminar_Fun(<s:property value="#result_Status.index"/>,'<s:property value="funcionalidad.nombre" />');" />
													</form>
												</td>
												<s:if test="!salidas.size()>0 || !entradas.size()>0">
													<s:set name="contador" value="%{#contador + 1}" />
													<s:i18n name="ve/gob/cnti/srsi/i18n/errors">
														<td style="margin: 0; padding: 0;"><img src="res/img/important.png"	id="h<s:property value='#contador'/>" alt="ayuda" onmouseover="tip(this);" name="h<s:property value='#contador'/>" height="25" width="30" />
															<div class="h<s:property value='#contador'/>" style="visibility: hidden; display: none;">
															    <s:if test="!salidas.size()>0">
																	<p><s:text name="error.servicio.incomplete.salidas" /></p>
																</s:if>
																<s:if test="!entradas.size()>0">
																	<p><s:text name="error.servicio.incomplete.entradas" /></p>
																</s:if>
															</div></td>
													</s:i18n>
												</s:if>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<th colspan="4"><s:text name="funcionalidades.error" /><br>
									<form action="prepararFuncionalidad" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="modificar"/>
										<s:token name="token" />
										<input type="submit" value="<s:text name="crear.funcionalidad"/>" />
									</form>
								</th>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>
		</div>
		<div class="n" style="visibility: hidden; display: none;">
			<s:property value="#contador" />
		</div>
		<div style="clear: both"></div>
		<div class="vacio"></div>
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>
