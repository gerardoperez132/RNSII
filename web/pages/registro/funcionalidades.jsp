<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/funcionalidades">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css"
	href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<title><s:text name="funcionalidades" /></title>
	</head>
	<body>
		<!-- Este es el div de la sombra del contenedor del maquetado de la página -->
		<div id="sombra">
			<!-- Este es el div contenedor del maquetado de la página -->
			<div id="container">
				<%@include file="../layout/header.jsp"%>
				<!-- Esta es la barra lateral -->
				<div id="sidebar">
					<small>Paso 1 Registro de Servicio de Información</small><br>
					<br> <big>Paso 2 Registro de Funcionalidad(es)</big> <br>
					<br> <small>Paso 3 Registro de Entradas/Salidas</small><br>
					<br> <small>Paso 4 Verificar y guardar</small>
					<br> <small>refresh: <s:property value="refresh"/></small>
				</div>
				<!-- Este es el div de contenidos -->
				<div id="content">
					<h4>
						<s:text name="registro.title" />
					</h4>
					<hr>
					<h5 class="formulario">
						<s:text name="servicio.title">
							<s:param>
								<s:property value="servicio.nombre" />
							</s:param>
						</s:text>
					</h5>
					<h5 class="formulario">
						<s:text name="descripcion.title">
							<s:param>
								<s:property value="servicio.descripcion" />
							</s:param>
						</s:text>
					</h5>
					<h5 class="formulario">
						<s:text name="id.title">
							<s:param>
								<s:property value="idServicioInformacion" />
							</s:param>
						</s:text>
					</h5>
					<hr>
					<s:fielderror>
						<s:param>funcionalidades</s:param>
					</s:fielderror>
					<form action="prepararFuncionalidad" method="POST">
						<s:hidden name="idServicioInformacion"></s:hidden>
						<input type="submit" value="<s:text name="funcionalidad.add"/>" />
					</form>
					<!-- Tabla en árbol. -->
					<table id="tree" class="treeTable">
						<thead>
							<tr>
								<th><s:text name="id"></s:text>
								</th>
								<th><s:text name="nombre"></s:text>
								</th>
								<th><s:text name="descripcion"></s:text>
								</th>								
								<th><s:text name="acciones"></s:text>
								</th>
							</tr>
						</thead>						
						<tbody>
							<s:if test="funcionalidades.size() > 0">
								<s:iterator value="funcionalidades" status="result_Status">
									<tr id="node-<s:property value="#result_datos.index"/>">
										<th><s:property value="id_funcionalidad" /></th>
										<td><s:property value="nombre" /></td>
										<td><s:property value="fecha_creado" /></td>
										<td>
											<table style="margin: 0; padding: 0;">
											<tr style="margin: 0; padding: 0;">
											<td style="margin: 0; padding: 0;">
												<form action="prepararFuncionalidad"
													method="POST">
													<s:hidden name="idFuncionalidad" value="%{id_funcionalidad}"></s:hidden>
													<s:hidden name="idServicioInformacion"></s:hidden>
													<s:hidden name="modificar" value="%{true}"></s:hidden>
													<input type="submit" value="<s:text name="modificar"/>"
														style="font-size: 0.9em;" />													
												</form>												
											</td>
											<td style="margin: 0; padding: 0;">
												<form action="eliminarFuncionalidad"
													method="POST">
													<s:hidden name="idFuncionalidad" value="%{id_funcionalidad}"></s:hidden>
													<s:hidden name="idServicioInformacion"></s:hidden>
													<input type="submit" value="<s:text name="eliminar" />"
														style="font-size: 0.9em;" />													
												</form>
											</td>
											</tr>
											</table>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<th colspan="4">
										<s:text	name="funcionalidades.error" />
									</th>
								</tr>
							</s:else>
						</tbody>
					</table>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>