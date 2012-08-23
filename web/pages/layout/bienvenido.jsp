<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="Titulo_width_user Titulo">
	<h1><s:text name="titulo2" /></h1>
</div>
 			
<div class="busqueda_width_user busqueda">
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