<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Tabs Princpales -->
<%@include file="tabs_principales.jsp"%>

<div class="tab_container_height tab_container">
	<div class="tab_content">						
		<ul class="tabs">
			<li class="active">
				<a>
				<s:text name="tab1.title"/>
				<s:if test="id_funcionalidad > 0">						
					<img src="res/img/correcto.png" id="h2" alt="ayuda" 
					onmouseover="tip(this);" name="h2" height="15" width="15" />
				</s:if>
				</a>
			</li>
			<li>
				<form action="prepararEntradas" method="POST">
					<s:hidden name="id_servicio_informacion"/>
					<s:hidden name="id_funcionalidad"/>
					<s:hidden name="modificar"/>
					<input type="submit" value='<s:text name="tab.entrada"/>' 
						style="background: none; border: none; font-size: 12px; padding-left: 0 20px; 
						height: 31px; text-transform: none; font-family: sans-serif; color:black;
						font-weight: normal;"/>
				</form>
			</li>
			<li>
				<s:set var="btn_s" value="true"></s:set>
				<form action="prepararSalidas" method="POST">
					<s:hidden name="id_servicio_informacion"/>
					<s:hidden name="id_funcionalidad"/>
					<s:hidden name="modificar"/>					
					<s:iterator value="tabs_incompletas" status="status">
					<s:if test="tab==6">
						<s:set var="btn_s" value="false"></s:set>
						<input type="submit" value='<s:text name="tab.salida"/>' 
							style="background: none; border: none; font-size: 12px; padding-left: 20px; 
							height: 31px; text-transform: none; font-family: sans-serif; color:black;
							font-weight: normal;">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h6" alt="ayuda" 
							onmouseover="tip(this);" name="h6" height="15" width="15" />
						</s:if>							
						<s:else>
							<img src="res/img/correcto.png" id="h6" alt="ayuda" 
							onmouseover="tip(this);" name="h6" height="15" width="15" />
						</s:else>
					</s:if>					
					</s:iterator>
					<s:if test="btn_s">
					<input type="submit" value='<s:text name="tab.salida"/>' 
						style="background: none; border: none; font-size: 12px; padding: 0 20px; 
						height: 31px; text-transform: none; font-family: sans-serif; color:black;
						font-weight: normal;">
					</s:if>					
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