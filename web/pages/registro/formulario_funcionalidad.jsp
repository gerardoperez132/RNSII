<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="robots" content="index, follow">
		<meta name="description" content="Registro Nacional de Servicios Interoperables del Estado Venezolano" />
 		<meta name="keywords" content="Servicios Interoperables del Estado Venezolano, rnsii, servicios web, interoperabilidad Venezuela, interoperabilidad Gobierno de Venezuela,UDDI Venezuela" />
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/RNSII/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.validate.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/messages_es.js" charset="UTF-8"></script>
<script type="text/javascript" src="/RNSII/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/formulario_funcionalidad.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>

<!-- Added... -->
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<!-- JS (required) -->
<script type="text/javascript" src="res/js/plugins/sexy-tooltips.v1.1.jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/jquery.easing.1.3.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.v1.2.jquery.js" charset="UTF-8"></script>

<%@include file="../layout/header_joomla.jsp" %>
<s:set name="finalizar" value="true" />
<s:iterator value="tabs_incompletas" status="status">
<s:if test="detalles.size()>0">
	<s:set name="finalizar" value="false" />
</s:if>		
</s:iterator>
<title><s:text name="registro.title"/></title>
</head>
	<body class="bg clearfix">
	<s:iterator value="tabs_incompletas" status="status">			
		<div class="h<s:property value="tab" />" style="visibility: hidden; display: none;">	
			<s:if test="detalles.size()>0">
				<s:iterator value="detalles">
					<p><s:property/></p>
				</s:iterator>
			</s:if>		
			<s:else>
				<p><s:text name="servicio.tab.complete"></s:text></p>
			</s:else>			
		</div>						
	</s:iterator>	
	<div class="h2" style="visibility: hidden; display: none;">
			<p><s:text name="servicio.tab.complete"></s:text></p>
	</div>
	<div class="n" style="visibility: hidden; display: none;">
		<s:property value="tabs_incompletas.size()+1" />
	</div>
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
<!-- Mostrar Tabla -->				
					<%@include file="layout_funcionalidades/tabla_funcionalidad.jsp"%>							
				</s:if>				
				<s:elseif test="resumen">		
<!-- Resumen -->							
					<%@include file="layout_funcionalidades/resumen_funcionalidad.jsp"%>					
				</s:elseif>				
				<s:else>
<!-- Formulario -->				
					<%@include file="layout_funcionalidades/formulario_funcionalidad.jsp"%>
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
		<div class="t3" style="visibility: hidden; display: none;">
			<s:text name="servicio.tab.complete"/>
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