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
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/messages_es.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/formulario_funcionalidad.js" charset="UTF-8"></script>

<!-- Added... -->
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>

<%@include file="../layout/header_joomla.jsp" %>

<title><s:text name="registro.title"/></title>
	</head>
	<body class="bg clearfix">
	<div class="bg1">
		<div class="sp-wrap main-bg clearfix" style="width: 960px;">
		<%@include file="../layout/menus.jsp"%>
		<div class="content">
			
			<!-- Esta es la barra lateral -->
			<%@include file="../layout/sidebar.jsp"%>
			<!-- Este es el div de contenidos -->
			<div class="contenido">
				<h1><a><s:text name="titulo2" /></a></h1>
				<div class="pasos">
					<table>
						<tr>
							<td><span style="font-weight: bolder;"><s:text name="registro.title" /></span></td>
							<td>
						</tr>
					</table>
				</div>
				<s:if test='mostrarTabla'>
					<ul class="tabs">
						<li><a href="prepararDescripcionGeneral"><s:text name="tab1.title"/></a></li>
						<li><a href="prepararAspectosLegales"><s:text name="tab2.title"/></a></li>
						<li><a href="prepararDescripcionTecnica"><s:text name="tab3.title"/></a></li>
						<li><a href="prepararDescripcionSoporte"><s:text name="tab4.title"/></a></li>
						<li class="active"><a href="prepararFuncionalidades"><s:text name="funcionalidades"/></a></li>
					</ul>
					<div class="tab_container_height tab_container">
						<div class="tab_content">
							<h3 class="formulario">
								<s:text name="funcionalidad.registro" />
							</h3>
							<small><s:text name="funcionalidad.registro.description"/></small>
							<hr>
							<ul class="tabs">
								<li class="active"><a><s:text name="tab1.title"/>
								</a></li>
								<li>
									<form action="prepararEntradas" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<input type="submit" value='<s:text name="tab.entrada"/>' 
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
								<li>
									<form action="prepararSalidas" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<input type="submit" value='<s:text name="tab.salida"/>' 
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
								<li>
									<form action="prepararResumen" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<input type="submit" value="<s:text name="tab.resumen" />" 
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
							</ul>
							<div class="tab_container">
								<div id="tab1" class="tab_content">
									<table class="results_width_user_datos tb">
										<tr>
											<td class="tb_alt_f"><span class="txt_small"> <s:text name="nombre.title" /></span></td>
											<td class="tb_td"><span class="txt_small"> <s:property value="funcionalidad.nombre" /></span></td>
										</tr>
										<tr>
											<td class="tb_alt_f"><span class="txt_small"> <s:text name="descripcion.title" /></span></td>
											<td class="tb_td"><span class="txt_small"> <s:property value="funcionalidad.descripcion" /></span></td>
										</tr>
									</table>
									<form action="prepararFuncionalidad" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<s:hidden name="modificarf" value="%{true}"/>
										<input type="submit" value='<s:text name="modificar"/>' class="button_h">
									</form>
								</div>
							</div>

						</div>
					</div>
				</s:if>
				<s:elseif test="resumen">
					<ul class="tabs">
						<li><a href="prepararDescripcionGeneral"><s:text name="tab1.title"/></a></li>
						<li><a href="prepararAspectosLegales"><s:text name="tab2.title"/></a></li>
						<li><a href="prepararDescripcionTecnica"><s:text name="tab3.title"/></a></li>
						<li><a href="prepararDescripcionSoporte"><s:text name="tab4.title"/></a></li>
						<li class="active"><a href="prepararFuncionalidades"><s:text name="funcionalidades"/></a></li>
					</ul>
					<div class="tab_container" style="height: 450px;">
						<div class="tab_content">
							<h3 class="formulario">
								<s:text name="funcionalidad.registro" />
							</h3>
							<small><s:text name="funcionalidad.registro.description"/></small>
							<hr>
							<ul class="tabs">
								<li>
									<form action="prepararFuncionalidad" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<s:hidden name="mostrarTabla" value="%{true}"/>
										<input type="submit" value="<s:text name="tab1.title"/>" 
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
								<li>
									<form action="prepararEntradas" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<input type="submit" value='<s:text name="tab.entrada"/>'											
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
								<li>
									<form action="prepararSalidas" method="POST">
										<s:hidden name="id_servicio_informacion"/>
										<s:hidden name="id_funcionalidad"/>
										<s:hidden name="modificar"/>
										<input type="submit" value='<s:text name="tab.salida"/>' 
									style="background: none; border: none; font-size: 12px; padding: 0 20px; 
									height: 31px; text-transform: none; font-family: sans-serif; color:black;
									font-weight: normal;">
									</form>
								</li>
								<li class="active"><a><s:text name="tab.resumen"/>
								</a></li>
							</ul>
							<div class="tab_container">
								<div id="tab1" class="tab_content">
									<s:fielderror>
										<s:param>Salidas</s:param>
									</s:fielderror>
									<table class="results_width_user_datos tb">
										<tr>
											<td class="tb_alt"><span class="txt_small"> <s:text name="nombre.title"/></span></td>
											<td class="tb_td"><span class="txt_small"> <s:property value="funcionalidad.nombre" /></span></td>
										</tr>
										<tr>
											<td class="tb_alt"><span class="txt_small"> <s:text name="descripcion.title"/></span></td>
											<td class="tb_td"><span class="txt_small"> <s:property value="funcionalidad.descripcion" /></span></td>
										</tr>
										<tr>
											<td class="tb_alt"><span class="txt_small"> <s:text name="salidas.cargadas"/></span></td>
											<td class="tb_td"><span class="txt_small"> <s:property value="salidas.size" /></span></td>
										</tr>
										<tr>
											<td class="tb_alt"><span class="txt_small"> <s:text	name="entradas.cargadas"/></span></td>								
											<td class="tb_td"><span class="txt_small"> <s:property value="entradas.size" /></span></td>
										</tr>
									</table>									
								</div>
							</div>
						</div>
					</div>
				</s:elseif>
				<s:else>
					<ul class="tabs">
						<li><a href="prepararDescripcionGeneral"><s:text name="tab1.title"/></a></li>
						<li><a href="prepararAspectosLegales"><s:text name="tab2.title"/></a></li>
						<li><a href="prepararDescripcionTecnica"><s:text name="tab3.title"/></a></li>
						<li><a href="prepararDescripcionSoporte"><s:text name="tab4.title"/></a></li>
						<li class="active"><a href="prepararFuncionalidades"><s:text name="funcionalidades"/></a></li>
					</ul>
					<div class="tab_container" style="height: 450px;">
						<div class="tab_content">
							<h3 class="formulario">
								<s:text name="funcionalidad.registro" />
							</h3>
							<small><s:text name="funcionalidad.registro.description"/></small>
							<hr>
							<ul class="tabs">
								<li class="active"><a><s:text name="tab1.title"/></a></li>
								<li><a><s:text name="tab.entrada"/> </a></li>
								<li><a><s:text name="tab.salida"/> </a></li>
								<li><a><s:text name="tab.resumen"/> </a></li>
							</ul>
							<div class="tab_container">
								<div id="tab1" class="tab_content">
									<s:if test="modificarf">
										<form action="modificarFuncionalidad" method="POST" id="formFunc" name="formFunc">											
											<!-- Nombre de la funcionalidad u operación del servicio. -->
											<h5 class="formulario">
												<s:text name="nombre.title"/>
											</h5>
											<s:fielderror>
												<s:param>funcionalidad.nombre</s:param>
											</s:fielderror>
											<s:textfield name="funcionalidad.nombre" id="funcionalidad.nombre" />
											<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t1" id="t1" onmouseover="tip(this);" title="">
											<br>
											<!-- Descripción de la funcionalidad u operación del servicio. -->
											<h5 class="formulario">
												<s:text name="descripcion.title"/>
											</h5>
											<s:fielderror>
												<s:param>funcionalidad.descripcion</s:param>
											</s:fielderror>
											<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" id="funcionalidad.descripcion" />
											<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t2" id="t2" onmouseover="tip(this);" title="">
											<br>
											<s:hidden name="id_servicio_informacion"/>
											<s:hidden name="id_funcionalidad"/>
											<s:hidden name="modificar"/>
											<s:hidden name="modificarf"/>
											<input type="submit" value="<s:text name="guardar"/>" class="button_h"/>
										</form>											
									</s:if>
									<s:else>
										<form action="registrarFuncionalidad" method="POST"
											id="formFunc" name="formFunc">											
											<!-- Nombre de la funcionalidad u operación del servicio. -->
											<h5 class="formulario">
												<s:text name="nombre.title"/>
											</h5>
											<s:fielderror>
												<s:param>funcionalidad.nombre</s:param>
											</s:fielderror>
											<s:textfield labelposition="top" name="funcionalidad.nombre" id="funcionalidad.nombre" />
											<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t1" id="t1" onmouseover="tip(this);" title="">
											<br>
											<!-- Descripción de la funcionalidad u operación del servicio. -->
											<h5 class="formulario">
												<s:text name="descripcion.title"/>
											</h5>
											<s:fielderror>
												<s:param>funcionalidad.descripcion</s:param>
											</s:fielderror>
											<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" id="funcionalidad.descripcion" />
											<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t2" id="t2" onmouseover="tip(this);" title="">
											<br>
											<s:hidden name="id_servicio_informacion"/>
											<s:hidden name="modificar"/>
											<input type="submit" value='<s:text name="guardar"/>' class="button_h"/>
										</form>
									</s:else>
								</div>
							</div>
						</div>
					</div>
				</s:else>
			</div>
		</div>
		<div style="clear: both"></div>
		<div class="vacio"></div>
		<div class="t1" style="visibility: hidden; display: none;">
			<s:text name="tooltip.funcionalidad.nombre"/>
		</div>
		<div class="t2" style="visibility: hidden; display: none;">
			<s:text name="tooltip.funcionalidad.descripcion"/>
		</div>		
		<div class="clr"></div>
		<%@include file="../layout/breadcrumbs.jsp"%>	
		<%@include file="../layout/links_over_footer.jsp"%>	
		</div>		
		</div>		
		<!-- Footer -->	
		<%@include file="../layout/footer.jsp"%>
	</body>
</s:i18n>
</html>