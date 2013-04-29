<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../layout/cache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<s:i18n name="ve/gob/cnti/rnsii/i18n/messages">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- CSS (required) -->
<link rel="stylesheet" type="text/css" href="/RNSII/pages/res/css/style2.css">
<link rel="stylesheet" type="text/css" href="res/css/menu_vertical.css">
<link rel="stylesheet" type="text/css" href="res/css/jquery.treeTable.css">
<link rel="stylesheet" type="text/css" href="res/css/table2.css">
<link rel="stylesheet" type="text/css" href="res/css/table_tree.css">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-alert-box-1.2.2/sexyalertbox.css" media="all">
<link rel="stylesheet" type="text/css" href="res/js/plugins/sexy-tooltips/blue.css" media="all">
<link rel="stylesheet" type="text/css" href="res/css/tabs.css">
<!-- JS (required) -->
<script type="text/javascript" src="/RNSII/pages/res/js/jquery-1.7.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="/RNSII/pages/res/js/funciones_ge.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/jquery.treeTable.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/main.js" charset="UTF-8"></script>
<script type="text/javascript" src="res/js/registro/funcionalidades.js" charset="UTF-8"></script>
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
<title><s:text name="inicio" /></title>
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
					<table><tr><td>
						<span style="font-weight: bolder;">
							<s:text name="registro.title"/>							
						</span></td>
					<td></tr></table>				
				</div>
				<ul class="tabs">					
					<li>
						<a href="prepararDescripcionGeneral">
							<s:text name="tab1.title"></s:text>
							<s:iterator value="tabs_incompletas" status="status">
							<s:if test="tab==1">
								<s:if test="detalles.size()>0">
									<img src="res/img/important.png" id="h1" alt="ayuda" 
									onmouseover="tip(this);" name="h1" height="15" width="15" />
								</s:if>							
								<s:else>
									<img src="res/img/correcto.png" id="h1" alt="ayuda" 
									onmouseover="tip(this);" name="h1" height="15" width="15" />
								</s:else>
							</s:if>					
							</s:iterator>
						</a>
					</li>
					<li>
						<a href="prepararAspectosLegales">
							<s:text name="tab2.title"></s:text>
						</a>
					</li>
					<li>
						<a href="prepararDescripcionTecnica">
							<s:text name="tab3.title"></s:text>
							<s:iterator value="tabs_incompletas" status="status">
							<s:if test="tab==3">
								<s:if test="detalles.size()>0">
									<img src="res/img/important.png" id="h3" alt="ayuda" 
									onmouseover="tip(this);" name="h3" height="15" width="15" />
								</s:if>							
								<s:else>
									<img src="res/img/correcto.png" id="h3" alt="ayuda" 
									onmouseover="tip(this);" name="h3" height="15" width="15" />
								</s:else>
							</s:if>					
							</s:iterator>
						</a>
					</li>
					<li class="active">
						<a>
							<s:text name="funcionalidades"></s:text>
							<s:iterator value="tabs_incompletas" status="status">
							<s:if test="tab==4">
								<s:if test="detalles.size()>0">
									<img src="res/img/important.png" id="h4" alt="ayuda" 
									onmouseover="tip(this);" name="h4" height="15" width="15" />
								</s:if>							
								<s:else>
									<img src="res/img/correcto.png" id="h4" alt="ayuda" 
									onmouseover="tip(this);" name="h4" height="15" width="15" />
								</s:else>
							</s:if>					
							</s:iterator>
						</a>
					</li>
					<li>
						<a href="prepararDescripcionSoporte">
							<s:text name="tab4.title"></s:text>
							<s:iterator value="tabs_incompletas" status="status">
							<s:if test="tab==5">
								<s:if test="detalles.size()>0">
									<img src="res/img/important.png" id="h5" alt="ayuda" 
									onmouseover="tip(this);" name="h5" height="15" width="15" />
								</s:if>							
								<s:else>
									<img src="res/img/correcto.png" id="h5" alt="ayuda" 
									onmouseover="tip(this);" name="h5" height="15" width="15" />
								</s:else>
							</s:if>					
							</s:iterator>
						</a>
					</li>
				</ul>
				
				<div class="tab_description">
					<div class="tab_description_left">
						<h3 class="formulario">
						<s:text name="funcionalidades.title" />
					</h3>
					<small><s:text name="funcionalidades.description"></s:text></small>
					</div>	
					<div class="tab_description_right">
					<s:if test="finalizar">					
					<br>
					<form action="terminar_registro_si" id="formSI" name="formSI" method="post" enctype="multipart/form-data">
						<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
					</form>
					</s:if>	
					</div>																			
				</div>
				
				<div class="tab_container_height tab_container">					
					<div class="tab_content">		
					
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
									<input type="submit" class="button_h" value="<s:if test="funcionalidades.size == 0"><s:text name='crear.funcionalidad'/></s:if><s:else><s:text name='funcionalidad.add'/></s:else>"/>
								</form>
							</td>							
						</tr>
					</table>
					<div id="adminForm">
					<table class="category">
						<thead>
							<tr>
								<th class="list-title" style="vertical-align: middle;text-align: center;" colspan="4"><s:text name="funcionalidades.title" /></th>
							</tr>
							<tr>
								<th class="list-title" style="vertical-align: middle;text-align: center;"><s:text name="id"></s:text></th>
								<th class="list-title" style="vertical-align: middle;text-align: center;"><s:text name="nombre"></s:text></th>
								<th class="list-title" style="vertical-align: middle;text-align: center;"><s:text name="fecha"></s:text></th>
								<th class="list-title" style="vertical-align: middle;text-align: center;"><s:text name="acciones"></s:text></th>
							</tr>
						</thead>
						<tbody>							
							<s:if test="funcionalidadesPublicables.size() > 0">
								<s:set name="contador" value="%{0}" />
								<s:iterator value="funcionalidadesPublicables"
									status="result_Status">
									<tr class="cat-list-<s:if test="#result_Status.odd == true ">row0</s:if><s:else>row1</s:else>"
										style="vertical-align: middle;text-align: center;"
										id="node-<s:property value="#result_Status.index"/>">
										<td style="width: 30px; vertical-align: middle;"><s:property value="funcionalidad.id_funcionalidad" /></td>
										<td style="text-align: left; vertical-align: middle;"><s:property value="funcionalidad.nombre" /></td>
										<td style="width: 60px; vertical-align: middle;"><s:date name="funcionalidad.fecha_creado" format="d'/'MM'/'yyyy" /></td>
										<td style="width: 180px; vertical-align: middle;">
											<table style="margin: 0; padding: 0;">
												<tr style="margin: 0; padding: 0;">
													<td style="margin: 0; padding: 0;">
														<form action="prepararFuncionalidad" method="POST">
															<s:hidden name="id_funcionalidad" value="%{funcionalidad.id_funcionalidad}"></s:hidden>
															<s:hidden name="id_servicio_informacion"></s:hidden>
															<s:hidden name="mostrarTabla" value="%{true}"/>
															<input type="submit" class="button_h" value="<s:text name="modificar"/>" style="font-size: 0.9em;text-transform: none;" />
														</form>
													</td>
													<td style="margin: 0; padding: 0;">
														<form action="eliminarFuncionalidad" method="POST"
															onsubmit="return false;"
															id="id_<s:property value="#result_Status.index" />">
															<s:hidden name="id_funcionalidad" value="%{funcionalidad.id_funcionalidad}"></s:hidden>
															<s:hidden name="id_servicio_informacion"></s:hidden>
															<s:token name="token" />
															<input type="submit" class="button_h" value="<s:text name="eliminar" />" style="font-size: 0.9em;text-transform: none;" onclick="eliminar_Fun(<s:property value="#result_Status.index"/>,'<s:property value="funcionalidad.nombre" />');" />
														</form>
													</td>
													<s:if test="!salidas.size()>0 || !entradas.size()>0">
														<s:set name="contador" value="%{#contador + 1}" />
														<s:i18n name="ve/gob/cnti/rnsii/i18n/errors">
															<td style="margin: 0; padding: 0;"><img src="res/img/important.png"	id="h<s:property value='tabs_incompletas.size()+1+#contador'/>" alt="ayuda" onmouseover="tip(this);" name="h<s:property value='tabs_incompletas.size()+1+#contador'/>" height="25" width="30" />
																<div class="h<s:property value='tabs_incompletas.size()+1+#contador'/>" style="visibility: hidden; display: none;">
																    <s:if test="!salidas.size()>0">
																		<p><s:text name="error.servicio.incomplete.salidas" /></p>
																	</s:if>
																	<s:if test="!entradas.size()>0">
																		<p><s:text name="error.servicio.incomplete.entradas" /></p>
																	</s:if>
																	<s:if test="!hijos">
																		<p><s:text name="error.servicio.incomplete.hijos" /></p>
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
									<th colspan="4" style="text-align:center;"><s:text name="funcionalidades.error" /><br>
									</th>
								</tr>
							</s:else>
						</tbody>
					</table>
					</div>					
					</div>
				</div>				
			</div>
		</div>		
		<div class="n" style="visibility: hidden; display: none;">
			<s:property value="tabs_incompletas.size()+1+#contador" />
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
