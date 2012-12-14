<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Esta es la barra lateral -->
<div class="sidebar">
	<div class="busqueda">
	<form method="post" action="buscar_servicio">
	<div class="finder">
		<input type="text" name="cadena" id="mod-finder-searchword" class="inputbox" size="25" value="">			
	</div>
	</form>
	</div>
	
	<div class="error_msj" style="visibility: visible;">
		<s:if test="msj_error.length()>0">
			<span class="errorMessage"><s:property value="msj_error" /></span>
		</s:if>
		<s:elseif test="msj_actionInfo.length()>0">
			<span class="ok_pass"><s:property value="msj_actionInfo" /></span>
		</s:elseif>
		<s:else>
			<s:fielderror />
		</s:else>
	</div>
	<div style="clear: both"></div>
	<%@include file="../user/login.jsp"%>
	<div style="clear: both"></div>
	<div id="adminForm">
	<table class="category">
		<thead>
			<tr>
			<th class="list-title" id="tableOrdering1" colspan="2">
				<s:text name="si_mas_Visitados" />
			</th>
			</tr>
			<tr>
				<th class="list-title" id="tableOrdering">
					Servicio
				</th>
				<th class="list-hits" id="tableOrdering4">
					Visto	
				</th>							
			</tr>
		</thead>		
		<tbody>		
		<s:if test='SI_masVisitados.size()>0'>	
			<s:iterator value="SI_masVisitados" status="index">
			<tr class="cat-list-row0">		
			<td class="list-title">
				<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
					<s:property value="nombre" />
				</a>
			</td>
			<td class="list-hits">
				<s:property value="visitas" />
			</td>
			</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr class="cat-list-row0">							
				<td class="list-title" colspan="2">
					<s:text name="sis_null3" />
				</td>
			</tr>
		</s:else>			
		<tr class="cat-list-row0">
			<td class="list-title">
				<a href="listarServicios" style="font-weight:bold;"><s:text name="ver_servicios"/></a>
				<td class="list-hits">
				</td>
			</td>
		</tr>
		</tbody>
	</table>
	<table class="category">
		<thead>
			<tr>
			<th class="list-title" id="tableOrdering1" colspan="2">
				<s:text name="sectores_list" />
			</th>
			</tr>
			<tr>
				<th class="list-title" id="tableOrdering">
					Sector
				</th>
				<th class="list-hits" id="tableOrdering4">
					Visto	
				</th>							
			</tr>
		</thead>		
		<tbody>	
		<s:if test="listaSectores.size()>0">
		<s:iterator value="listaSectores" status="index">
			
			<tr class="cat-list-row0">							
				<td class="list-title">
					<a href="listarSector?id_sector=<s:property value="id_sector"/>">
					<s:property value="nombre" />
					</a>
				</td>
				<td class="list-hits">
					<s:property value="n" />
				</td>
			</tr>
		</s:iterator>
		</s:if>
		<s:else>
			<tr class="cat-list-row0">							
				<td class="list-title" colspan="2">
					<s:text name="sis_null3" />
				</td>				
			</tr>
		</s:else>
		<tr class="cat-list-row0">
			<td class="list-title">
				<a href="listarSectores" style="font-weight:bold;"><s:text name="ver_sectores"/></a>
				<td class="list-hits">
				</td>
			</td>
		</tr>
		</tbody>
	</table>
	</div>	
</div>
