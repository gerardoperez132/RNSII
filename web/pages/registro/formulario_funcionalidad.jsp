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
<link rel="stylesheet" type="text/css" href="res/css/jquery.alerts.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/SRSI/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/SRSI/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.alerts.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/formulario_funcionalidad.js" charset="UTF-8"></script>
<title><s:text name="registro.title"></s:text>
</title>
	</head>
<body>	
	<div class="container">
		<%@include file="../layout/header.jsp"%>
		
		<!-- Esta es la barra lateral -->
		<%@include file="../layout/sidebar.jsp"%>			

		<!-- Este es el div de contenidos -->
		<div class="contenido">
		<div class="Titulo" style="width:800px;">
   			<h1><s:text name="titulo2" /></h1>
   		</div>
   			
		<div class="busqueda" style="width:800px;">
			<form method="post" action="Buscar_Servicio">
				<label><s:text name="buscarServicio"/></label>
				<s:textfield name="cadena" id="buscar" cssClass="inputBusqueda buscar" style="width:640px;"/>
				<input type="submit" class="submit" value="&nbsp;"/>
			</form>
		</div>
		
		<table class="main_user">	
		<tr>
			<td>
				<div>
					<h4 style="margin: 0;">
					<s:text name="bienvenido" /> 
					<s:property value="%{#session.usuario.nombre}"/>
					</h4>
				</div>
			</td>			
		</tr>		
		<tr>
			<td>
				<h4 style="margin: 0;"><s:text name="ente" />
				<s:property value="%{#session.ente_sesion.nombre}"/></h4>
			</td>										
		</tr>								
		</table>
		<div class="pasos">
			<table>
				<tr>
					<td><small> <strong> <s:if test="resumen">
									<s:text name="paso2.2" />
								</s:if> <s:else>
									<s:text name="paso2.1" />
								</s:else> <s:text name="funcionalidades" /> - </strong> <s:if
								test="resumen == true">
								<s:text name="tab.resumen" />
							</s:if> <s:else>
								<s:text name="tab1.title" />
							</s:else> </small></td>
				</tr>
			</table>
		</div>
					<h3>
						<s:text name="funcionalidades.title" />
					</h3>
					<h4>
						<s:text name="servicio.title" />
						<s:property value="servicio.nombre" />
					</h4>
					<hr>
					<s:if test='id_funcionalidad > 0 && modificarf != true && resumen != true'>
						<ul class="tabs">
							<li class="active"><a><s:text name="tab1.title"></s:text>
							</a>
							</li>
							<li>
								<form action="prepararEntradas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit"
										value='<s:text name="tab.entrada"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
							<li>
								<form action="prepararSalidas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit"
										value='<s:text name="tab.salida"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
							<li>
								<form action="prepararResumen" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit" value="<s:text name="tab.resumen" />"
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<table class="tb" style="width:750px;">
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="nombre.title" /> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="funcionalidad.nombre" /> </span></td>
									</tr>
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="descripcion.title" /> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="funcionalidad.descripcion" /> </span></td>
									</tr>
								</table>
								<form action="prepararFuncionalidad" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<s:hidden name="modificarf" value="%{true}"></s:hidden>
									<input type="submit" value='<s:text name="modificar"></s:text>'>
								</form>
							</div>
						</div>
					</s:if>
					<s:elseif test="resumen">
						<ul class="tabs">
							<li>
								<form action="prepararFuncionalidad" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit"
										value="<s:text name="tab1.title"></s:text>"
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form></li>
							<li>
								<form action="prepararEntradas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit"
										value='<s:text name="tab.entrada"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
							<li>
								<form action="prepararSalidas" method="POST">
									<s:hidden name="id_servicio_informacion"></s:hidden>
									<s:hidden name="id_funcionalidad"></s:hidden>
									<s:hidden name="modificar"></s:hidden>
									<input type="submit"
										value='<s:text name="tab.salida"></s:text>'
										style="background: none; border: none; font-size: 0.8em; padding: 0 20px; height: 31px;">
								</form>
							</li>
							<li class="active"><a><s:text name="tab.resumen"></s:text>
							</a>
							</li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<s:fielderror>
									<s:param>Salidas</s:param>
								</s:fielderror>
								<table class="tb" style="width:750px;">
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="nombre.title"></s:text> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="funcionalidad.nombre" /> </span></td>
									</tr>
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="descripcion.title"></s:text> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="funcionalidad.descripcion" /> </span></td>
									</tr>
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="salidas.cargadas"></s:text> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="salidas.size" /> </span></td>
									</tr>
									<tr>
										<td class="tb_alt"><span class="txt_small"> <s:text
													name="entradas.cargadas"></s:text> </span></td>
										<td class="tb_td"><span class="txt_small"> <s:property
													value="entradas.size" /> </span></td>
									</tr>
								</table>
								<s:if test="salidas.size>0">
									<form action="prepararFuncionalidades" method="POST">
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<s:hidden name="id_funcionalidad"></s:hidden>
										<s:hidden name="modificar"></s:hidden>
										<input type="submit" value='<s:text name="guardar"></s:text>'>
									</form>
								</s:if>
							</div>
						</div>
					</s:elseif>
					<s:else>
						<ul class="tabs">
							<li class="active"><a><s:text name="tab1.title"></s:text>
							</a>
							</li>
							<li><a><s:text name="tab.entrada"></s:text> </a>
							</li>
							<li><a><s:text name="tab.salida"></s:text> </a>
							</li>
							<li><a><s:text name="tab.resumen"></s:text> </a>
							</li>
						</ul>
						<div class="tab_container">
							<div id="tab1" class="tab_content">
								<s:if test="modificarf == true">
									<form action="modificarFuncionalidad" method="POST" id="formFunc" name="formFunc">
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
										<s:textfield name="funcionalidad.nombre" id="funcionalidad.nombre"/>
										<br>
										<!-- Descripción de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="descripcion.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.descripcion</s:param>
										</s:fielderror>
										<s:textarea name="funcionalidad.descripcion" cols="30"
											rows="5" id="funcionalidad.descripcion"/>
										<br>
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<s:hidden name="id_funcionalidad"></s:hidden>
										<s:hidden name="modificar"></s:hidden>
										<s:hidden name="modificarf" value="%{false}"></s:hidden>
										<input type="submit" value=<s:text name="guardar"></s:text> />
									</form>
								</s:if>
								<s:else>
									<form action="registrarFuncionalidad" method="POST" id="formFunc" name="formFunc">
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
										<s:textfield labelposition="top" name="funcionalidad.nombre" id="funcionalidad.nombre"/>
										<br>
										<!-- Descripción de la funcionalidad u operación del servicio. -->
										<h5 class="formulario">
											<s:text name="descripcion.title"></s:text>
										</h5>
										<s:fielderror>
											<s:param>funcionalidad.descripcion</s:param>
										</s:fielderror>
										<s:textarea name="funcionalidad.descripcion" cols="30"
											rows="5" id="funcionalidad.descripcion"/>
										<br>
										<s:hidden name="id_servicio_informacion"></s:hidden>
										<s:hidden name="modificar"></s:hidden>
										<input type="submit" value='<s:text name="guardar"></s:text>' />
									</form>
								</s:else>
							</div>
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