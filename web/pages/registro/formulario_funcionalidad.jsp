<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/srsi/i18n/formulario_funcionalidad">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="res/css/styles.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<title><s:text name="registro.title"></s:text></title>
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
					<small>Registro de Servicio de Información / Paso 1: / <strong> Paso 2 </strong> / Paso 3</small>
					<h3>
						<s:text name="registro.title"></s:text>
					</h3>
					<h4>
						<s:text name="servicio.title">
							<s:param>
								<s:property value="servicio.nombre" />
							</s:param>
						</s:text>
					</h4>
					<hr>
					<s:if
						test="id_funcionalidad > 0 && modificar != true && resumen != true">
						<ul class="tabs">
							<li class="active"><a><s:text name="tab1.title"></s:text>
							</a></li>
							<li>
								<form action="prepararEntradas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit"
										value='<s:text name="tab2.title"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
							<li>
								<form action="prepararSalidas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit"
										value='<s:text name="tab3.title"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
							<li>
								<form action="prepararResumen" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit" value="<s:text name="tab4.title" />"
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<table class="tb">
									<tr>
										<td class="tb_alt">
											<span class="txt_small">
												<s:text name="nombre.title"/>
											</span>
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="funcionalidad.nombre" />
											</span>
										</td>
									 </tr>
									 <tr>
										<td class="tb_alt">
											<span class="txt_small">
												<s:text name="descripcion.title"/>
											</span>
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="funcionalidad.descripcion" />
											</span>
										</td>
									 </tr>
								</table>								
								<form action="prepararFuncionalidad" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar" value="%{true}"></s:hidden>
									<input type="submit" value='<s:text name="modificar"></s:text>'>
								</form>
							</div>
						</div>
					</s:if>
					<s:elseif test="resumen == true">
						<ul class="tabs">
							<li>
								<form action="prepararFuncionalidad" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit"
										value="<s:text name="tab1.title"></s:text>"
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
							<li>
								<form action="prepararEntradas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit"
										value='<s:text name="tab2.title"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
							<li>
								<form action="prepararSalidas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<input type="submit"
										value='<s:text name="tab3.title"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
							<li class="active"><a><s:text name="tab4.title"></s:text>
							</a></li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<s:fielderror>									
									<s:param>Salidas</s:param>										
								</s:fielderror>
								<table class="tb">
									<tr>
										<td class="tb_alt">
											<span class="txt_small">
												<s:text name="nombre.title"></s:text>
											</span>
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="funcionalidad.nombre" />
											</span>
										</td>
									</tr>
									<tr>
										<td class="tb_alt">
											<span class="txt_small">
												<s:text name="descripcion.title"></s:text>
											</span>											
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="funcionalidad.descripcion" />
											</span>
										</td>
									</tr>
									
																	
									
									<tr>
									<td class="tb_alt">											
											<span class="txt_small">
												<s:text name="salidas.title"></s:text>
											</span>											
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="salidas.size" />
											</span>
										</td>
									</tr>
									<tr>
										<td class="tb_alt">
											<span class="txt_small">
												<s:text name="entradas.title"></s:text>
											</span>
										</td>
										<td class="tb_td">
											<span class="txt_small">
												<s:property value="entradas.size" />
											</span>
										</td>
									</tr>
								</table>
								<s:if test="salidas.size>0">
									<form action="prepararFuncionalidades" method="POST">
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<s:hidden name="id_funcionalidad"></s:hidden>
										<input type="submit" value='<s:text name="guardar"></s:text>'>
									</form>
								</s:if>
							</div>
						</div>
					</s:elseif>
					<s:else>
						<ul class="tabs">
							<li class="active"><a><s:text name="tab1.title"></s:text>
							</a></li>
							<li><a><s:text name="tab2.title"></s:text> </a></li>
							<li><a><s:text name="tab3.title"></s:text> </a></li>
							<li><a><s:text name="tab4.title"></s:text> </a></li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<s:if test="modificar == true">
									<form action="modificarFuncionalidad" method="POST">
										<p>
											<s:text name="tab1.title"></s:text>
										</p>
										<hr>
										<!-- Nombre de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="nombre.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.nombre</s:param>
										</s:fielderror>
										<s:textfield name="funcionalidad.nombre" />
										<br>
										<!-- Descripción de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="descripcion.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.descripcion</s:param>
										</s:fielderror>
										<s:textarea name="funcionalidad.descripcion" cols="30"
											rows="5" />
										<br>
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<s:hidden name="id_funcionalidad"></s:hidden>
										<s:hidden name="modificar" value="%{false}"></s:hidden>
										<input type="submit" value=<s:text name="guardar"></s:text> />
									</form>
								</s:if>
								<s:else>
									<form action="registrarFuncionalidad" method="POST">
										<p>
											<s:text name="tab1.title"></s:text>
										</p>
										<!-- Nombre de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="nombre.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.nombre</s:param>
										</s:fielderror>
										<s:textfield labelposition="top" name="funcionalidad.nombre" />
										<br>
										<!-- Descripción de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="descripcion.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.descripcion</s:param>
										</s:fielderror>
										<s:textarea name="funcionalidad.descripcion" cols="30"
											rows="5" />
										<br>
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<input type="submit" value='<s:text name="guardar"></s:text>' />
									</form>
								</s:else>
							</div>
						</div>
					</s:else>
				</div>
				<%@include file="../layout/footer.jsp"%>
			</div>
		</div>
	</body>
</s:i18n>
</html>