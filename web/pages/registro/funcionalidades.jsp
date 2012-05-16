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
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
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
				<%@include file="../layout/sidebar.jsp"%>
				
				<!-- Este es el div de contenidos -->
				<div id="content">
					 
					<form action="prepararModificarServicioInformacion" method="POST">													
						<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>
						<small>Registro de Servicio de Información / 													
						<input type="submit" value='Paso 1' 
							style="background: none; border: none; font-size: small; color: blue; font-style: italic; padding: 0;"/>
						/ <strong>Paso 2</strong></small>
					</form>		
					
					<h4>
						<s:text name="funcionalidades.title" />
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
								<s:property value="id_servicio_informacion" />
							</s:param>
						</s:text>
					</h5>
					<hr>
					<s:fielderror>
						<s:param>funcionalidades</s:param>
					</s:fielderror>
					<table>
					<tr><td>
					<form action="prepararFuncionalidad" method="POST">
						<s:hidden name="id_servicio_informacion"></s:hidden>
						<input type="submit" value="<s:text name="funcionalidad.add"/>" />
					</form>
					</td><td>						
						<form action="home" method="POST">						
							<input type="submit" value="<s:text name="terminar"/>" />
						</form>						
					</td></tr>
					</table>
					<!-- Tabla en árbol. -->
					<table id="tree" class="treeTable">
						<thead>
							<tr>
								<th><s:text name="id"></s:text></th>
								<th><s:text name="nombre"></s:text></th>
								<th><s:text name="fecha"></s:text></th>
								<th><s:text name="acciones"></s:text></th>
							</tr>
						</thead>
						<tbody>
							<s:if test="funcionalidades.size() > 0">
								<s:iterator value="funcionalidades" status="result_Status">
									<tr id="node-<s:property value="#result_Status.index"/>">
										<th><s:property value="id_funcionalidad" /></th>
										<td><s:property value="nombre" /></td>
										<td><s:property value="fecha_creado" /></td>
										<td>
											<table style="margin: 0; padding: 0;">
												<tr style="margin: 0; padding: 0;">
													<td style="margin: 0; padding: 0;">
														<form action="prepararFuncionalidad" method="POST">
															<s:hidden name="id_funcionalidad"
																value="%{id_funcionalidad}"></s:hidden>
															<s:hidden name="id_servicio_informacion"></s:hidden>															
															<input type="submit" value="<s:text name="modificar"/>"
																style="font-size: 0.9em;" />
														</form>
													</td>
													<td style="margin: 0; padding: 0;">
														<form action="eliminarFuncionalidad" method="POST">
															<s:hidden name="id_funcionalidad"
																value="%{id_funcionalidad}"></s:hidden>
															<s:hidden name="id_servicio_informacion"></s:hidden>
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
									<th colspan="4"><s:text name="funcionalidades.error" /></th>
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