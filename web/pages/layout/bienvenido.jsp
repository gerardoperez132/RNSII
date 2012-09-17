<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="Titulo_width_user Titulo">
	<h1><s:text name="titulo2" /></h1>
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