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
<script type="text/javascript" src="res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script src="res/js/validacion_datos.js"  type="text/javascript" charset="UTF-8"></script>
<title><s:text name="form.entrada.registro.title"></s:text></title>
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
			
					<div class="tab_container">
						<form action="Buscar_Servicio" method="post">
							<table align="center">
								<tr>
									<td><s:textfield name="cadena" size="50" /></td>
									<td><input type="submit"
										value="<s:text name="buscarServicio"/>"></td>
								</tr>
							</table>
						</form>
						<hr>										
						<s:if test="buscarServicio==true">
							<!-- Lista de servicios encontrados -->
							<table class="results">
								<tr>
									<th colspan="3"><s:text name="listaServiciosEncontrados" />
									</th>
								</tr>
								<tr>
									<th><s:text name="argumentoConsultado" /></th>
									<td colspan="2"><s:property value="cadena" /></td>
								</tr>
								<tr>
									<th><s:text name="nombre" /></th>
									<th><s:text name="ente1" /></th>
									<th><s:text name="fecha_creado" /></th>
								</tr>
								<s:if test="servicios.size()>0">
									<s:iterator value="servicios">
										<tr>
											<td><a
												href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
													<s:property value="nombre" /> </a>
											</td>
											<td><s:set name="id_e" value="id_ente" /> <s:iterator
													value="entes">
													<s:if test="id_ente = #id_e">
														<s:property value="nombre" />
													</s:if>
												</s:iterator>
											</td>
											<td><s:date name="fecha_creado" format="d'/'MM'/'yyyy" />
											</td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td colspan="3"><span class="ok_pass"><s:text
													name="sis_null2" /> </span></td>
									</tr>
								</s:else>
							</table>					
						</s:if>
						<s:elseif test="">
							<!-- Formulario para solicitar una suscripción a un servicio de información -->
							<form action="<s:property value="#action"></s:property>"
								method="post" id="myForm">
								<div id="tab2" class="tab_content">								
									suscripción
									<input type="submit"
										value="<s:property value="#submit"></s:property>" />
								</div>								
							</form>	
						</s:elseif>						
						<s:else>
							<s:fielderror>
								<s:param>error</s:param>
							</s:fielderror>
						</s:else>						
					</div>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>