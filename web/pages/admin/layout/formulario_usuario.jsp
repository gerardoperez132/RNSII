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
					<s:text name="usuario.modificar.requerido" />
				</h5>
			</td>
		</tr>
		<s:if test="accion_usuario==1">
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="ente1" /></td>			
			<td>
				<s:select name="usuario.id_ente" id="ente"
				   list="entes" listKey="id_ente" listValue="nombre" headerKey="-1"
				   headerValue="%{getText('ente.usuario.select')}" />
				   <span id="ente_required"></span> 
				   <s:fielderror><s:param>ente</s:param></s:fielderror>
			</td>
		</tr>
		</s:if>		
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="usuario.modificar.nombres" /></td>
			<td>
				<s:textfield name="usuario.nombre" id="nombre" />
				<span id="nombre_required"></span> 
				<s:fielderror><s:param>nombres</s:param></s:fielderror>
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="usuario.modificar.apellidos" /></td>
			<td>
				<s:textfield name="usuario.apellido" id="apellido" />
				<span id="apellido_required"></span> <s:fielderror>
					<s:param>apellidos</s:param>
				</s:fielderror>
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="usuario.modificar.cedula" /></td>
			<td>																				
				<s:select name="usuario.nacionalidad" id="usuario.nacionalidad"
				   list="nacionalidad" listKey="id_nacionalidad"
				   listValue="nombre" ></s:select>
				<s:textfield name="usuario.cedula" id="cedula" maxlength="9" style="width: 116px;"/>
				<span id="cedula_required"></span> <s:fielderror>
					<s:param>cedula</s:param>
				</s:fielderror>										
			</td>
		</tr>
		<tr>
			<td><h5 class="requerido">* </h5></td>
			<td><s:text name="correo" /></td>
			<td>
				<s:textfield name="correo" id="correo" />
				<span id="correo_required"></span> <s:fielderror>
					<s:param>correo</s:param>
				</s:fielderror>										
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<s:token name="token" />
				<s:hidden name="accion_usuario"/>
				<input type="submit" value="<s:text name="enviar"/>">
			</td>
		</tr>
	</table>	
</div>