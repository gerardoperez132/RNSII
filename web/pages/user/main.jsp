<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/userlogin">
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

			<!-- Este es el div de los menúes -->
			<div id="menu">
				<h5 align="center" style="color:#f5f5ee;">
				<s:text name="titulo" />
				</h5>
			</div>



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
			<!-- Tabla en árbol. -->
			<table id="tree" class="treeTable">
				<thead>
					<tr>
						<th colspan="4" style="text-align: center;" >Servicios de Información</th>						
					</tr>
					<tr>
						<th><s:text name="id"></s:text></th>
						<th><s:text name="nombre"></s:text></th>
						<th><s:text name="estado"></s:text></th>
						<th><s:text name="fecha"></s:text></th>
						<th><s:text name="acciones"></s:text></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="ListaServicios.size() > 0">
						<s:iterator value="ListaServicios" status="result_Status">
							<tr id="node-<s:property value="#result_datos.index"/>">								
								<th><s:property value="servicio.id_servicio_informacion" /></th>
								<td><s:property value="servicio.nombre" /></td>
								<td><s:property value="servicio.id_estado" /></td>
								<td><s:date name="servicio.fecha_creado" format="d'/'MM'/'yyyy" /></td>
								<td>
									<table style="margin: 0; padding: 0;">
										<tr style="margin: 0; padding: 0;">
											<td style="margin: 0; padding: 0;">
												<form action="examinarServicioInformacion" method="POST">													
													<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
													<input type="submit" value="<s:text name="detalles"/>"
														style="font-size: 0.9em;" />
												</form>
											</td>
											<td style="margin: 0; padding: 0;">
												<form action="prepararModificarServicioInformacion" method="POST">													
													<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
													<input type="submit" value="<s:text name="modificar"/>"
														style="font-size: 0.9em;" />
												</form>
											</td>
											<td style="margin: 0; padding: 0;">
												<form action="eliminarServicioInformacion" method="POST">
													<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
													<input type="submit" value="<s:text name="eliminar" />"
														style="font-size: 0.9em;" />
												</form>
											</td>
											<td style="margin: 0; padding: 0;">
												<s:if test="publicable==true">
												<s:if test="servicio.publicado==false">
													<form action="publicarServicioInformacion" method="POST">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
														<input type="submit" value="<s:text name="publicar" />"
															style="font-size: 0.9em;" />
													</form>
												</s:if>
												<s:else>
													<form action="despublicarServicioInformacion" method="POST">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
														<input type="submit" value="<s:text name="despublicar" />"
															style="font-size: 0.9em;" />
													</form>
												</s:else>
												</s:if>
												<s:else>
													<form action="prepararModificarServicioInformacion" method="POST">
														<s:hidden name="id_servicio_informacion" value="%{servicio.id_servicio_informacion}"></s:hidden>													
														<input type="submit" value="continuar registro"
															style="font-size: 0.9em;" />
													</form>
												</s:else>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<th colspan="4"><s:text name="servicios.error" /></th>
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