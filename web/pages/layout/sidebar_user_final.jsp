<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Esta es la barra lateral -->
<div class="sidebar_user_final">
	<div class="busqueda">
			<form method="post" action="buscar_servicio">
				<label><s:text name="buscarServicio"/></label>
				<s:textfield name="cadena" id="buscar" cssClass="inputBusqueda" class="buscar"/>
				<input type="submit" class="submit" value="&nbsp;"/>
			</form>
		</div>
	<div class="secciones" style="clear: both;">
		
		<ul class="ui-menu-content">
			<li class="li-menu-content">
				<h3 class="h3-ui-menu-content">
					<a style="font-size: 11px;"><s:text name="si_mas_Visitados" /></a>
				</h3>
			</li>
			<s:if test='SI_masVisitados.size()>0'>
				<s:iterator value="SI_masVisitados" status="index">
					<li class="li-menu-content">
						<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
							<s:property value="nombre" /> - (<s:property value="visitas" />)
						</a>
					</li>
				</s:iterator>
			</s:if>
			<s:else>
				<li class="li-menu-content"><s:text name="sis_null3" /></li>
			</s:else>
			<li class="li-menu-content">
				<a href="listarServicios"><s:text name="ver_servicios" /></a>
			</li>
		</ul>
		<ul class="ui-menu-content">
			<li class="li-menu-content"><h3 class="h3-ui-menu-content">
					<a style="font-size: 10px;"><s:text name="sectores_list" /></a>
				</h3></li>
			<s:if test="listaSectores.size()>0">
				<s:iterator value="listaSectores" status="index">
					<li class="li-menu-content">
						<a href="listarSector?id_sector=<s:property value="id_sector"/>">
							<s:property value="nombre" /> - (<s:property value="n" />)
						</a>
					</li>
				</s:iterator>
			</s:if>
			<s:else>
				<li class="li-menu-content"><s:text name="sis_null3" /></li>
			</s:else>
			<li class="li-menu-content">
				<a href="listarSectores"><s:text name="ver_sectores" /></a>
			</li>
		</ul>
	</div>
</div>