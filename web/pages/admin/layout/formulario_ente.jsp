<%@taglib uri="/struts-tags" prefix="s"%>
<div class="crud_user_capa">	
	<table style="margin-top: 50px; margin-left: 20px;">
		<tr>
			<td colspan="3"><s:fielderror>
					<s:param>datos</s:param>
				</s:fielderror></td>
		</tr>
		<tr>
			<td colspan="3">
				<h5 class="requerido">
					<s:text name="field.required" />
				</h5>
			</td>
		</tr>
		<s:if test="accion_ente==1">
		<tr>
			<td></td>
			<td><s:text name="ente4" /></td>			
			<td>
				<s:select name="ente.id_padre" id="ente"
				   list="entes" listKey="id_ente" listValue="nombre" headerKey="-1"
				   headerValue="%{getText('ente.select')}" cssStyle="width: 185px;" />				   
			</td>
		</tr>
		</s:if>		
		<tr>
			<td><h5 class="requerido">*</h5></td>
			<td><s:text name="nombre" /></td>
			<td>
				<s:textfield name="ente.nombre"/>				 
				<s:fielderror><s:param>nombre</s:param></s:fielderror>
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">*</h5></td>
			<td><s:text name="rif" /></td>
			<td>
				<s:select name="tipo_rif" list="rif_type" />
				<s:textfield name="ente.rif" maxlength="9" cssStyle="width: 116px;"/>							 
				<s:fielderror><s:param>rif</s:param></s:fielderror>
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="sigla"/></td>
			<td>
				<s:textfield name="ente.siglas"/>
				<s:fielderror><s:param>sigla</s:param></s:fielderror>
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="direccion"/></td>
			<td>
				<s:textfield name="ente.direccion"/>
				<s:fielderror><s:param>direccion</s:param></s:fielderror>
			</td>
		</tr>		
		<tr>
			<td colspan="3">
				<s:token name="token" />
				<s:hidden name="accion_ente"/>
				<input type="submit" value="<s:text name="enviar"/>">
			</td>
		</tr>
	</table>	
</div>