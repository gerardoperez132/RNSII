<%@taglib uri="/struts-tags" prefix="s"%>
<div id="adminForm">
<table class="category">
	<thead>
	<tr>
		<th colspan="4" class="list-title" style="vertical-align: middle;text-align: center;" id="tableOrdering1">			
			<s:if test="listarPublicados==true">
				<s:text name="listaServiciosPublicados" />	
			</s:if>
			<s:else>
				<s:text name="listaServiciosPorPublicar" />								
			</s:else>
		</th>
	</tr>
	<tr>
		<th class="list-title" style="vertical-align: middle;text-align: center;" id="tableOrdering"><s:text name="n_servicio" /></th>
		<th class="list-title" style="vertical-align: middle;text-align: center;" id="tableOrdering"><s:text name="nombre" /></th>
		<th class="list-title" style="vertical-align: middle;text-align: center;" id="tableOrdering"><s:text name="ente1" /></th>
		<th class="list-title" style="vertical-align: middle;text-align: center;" id="tableOrdering"><s:text name="publicar" /></th>
	</tr>
	</thead>
	<s:if test="servicios.size()>0">
		<s:iterator value="servicios">
		<tbody>
			<tr class="cat-list-row0">
				<td align="center" class="list-title" style="vertical-align: middle;text-align: center;">
					<a href="servicio_view?id_servicio=<s:property value="id_servicio_informacion"/>"><s:property value="id_servicio_informacion" /></a>
				</td>
				<td class="list-title" style="vertical-align: middle;text-align: left;">
					<a href="servicio_view?id_servicio=<s:property value="id_servicio_informacion"/>"><s:property value="nombre" /></a>
				</td>
				<td class="list-hits"><s:set name="id_e" value="id_ente" />
				<s:iterator value="entes">
					<s:if test="id_ente == #id_e">
						<s:property value="siglas" />
					</s:if>
				</s:iterator></td>
				<td>
					<s:if test="listarPublicados==true">
						<form action="despublicarSI" method="POST">
							<s:hidden name="id_servicio_informacion" value="%{id_servicio_informacion}"/>
							<input type="submit" style="font-size: 0.9em;" value="<s:text name="despublicar"/>"/>
						</form>
					</s:if>
					<s:else>
						<form action="publicarSI" method="POST">
							<s:hidden name="id_servicio_informacion" value="%{id_servicio_informacion}"/>
							<input type="submit" style="font-size: 0.9em;" value="<s:text name="publicar"/>"/>
						</form>											
					</s:else>					
				</td>
			</tr>
		</tbody>
		</s:iterator>
	</s:if>
	<s:else>
		<tr>
			<s:if test="listarPublicados==true">
				<td colspan="4"><s:text name="sis_null5" /></td>
			</s:if>
			<s:else>
				<td colspan="4"><s:text name="sis_null4" /></td>						
			</s:else>			
		</tr>
	</s:else>
</table>
</div>