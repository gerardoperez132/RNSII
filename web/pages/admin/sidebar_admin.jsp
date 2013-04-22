<%@taglib uri="/struts-tags" prefix="s"%>
<s:set var="sistema" scope="action" value="true"></s:set>
<div class="sidebar">
	
	<div class="busqueda">
		<form method="post" action="Buscar_Servicio">
			<div class="finder">
				<input type="text" name="cadena" id="mod-finder-searchword" class="inputbox" size="25" value="">			
			</div>
		</form>
	</div>
	
	<br>	
	
	<div class="ingresar" style="height: 120px;">
		<h1>
			<s:text name="bienvenido" />
			<s:property value="%{#session.usuario.nombre}" />
		</h1>
		<table class="main_user">
			<tr>
				<td>
					<h4 style="margin: 0;">
						<s:text name="ente" />
						<s:property value="%{#session.ente_sesion.nombre}" />
					</h4>
				</td>
			</tr>		
			<tr>
				<td>
					<div class="enlace_home">
						<a href="<s:url action="salir"/>"> <s:text name="salir" />
						</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<div style="clear: both"></div>
	<div class="m_a">
		<ul>
			<li class="m_a_fondo">
				<a class="m_a_titulo" href="<s:url action="registrar_ente"/>"> <s:text name="registro_ente" /></a>
			</li>
			<li class="m_a_fondo">
				<a class="m_a_titulo" href="<s:url action="registrar_usuario"/>"><s:text name="registro_usuario" /></a>
			</li>			
			<li class="m_a_fondo">
				<a class="m_a_titulo"><s:text name="configuraci�n" /> <span class="m_a_desplegar">+</span></a>
				<ul class="m_a_top" style="display: block;">
					<li>
						<form action="prepararFormulario" method="POST">
							<s:hidden name="modificarClave" value="%{true}" />
							<input style="text-transform: none; border: none;padding-left: 20px;border-bottom: 1px dotted #CCC;" class="btn_menu" type="submit" value="<s:text name="usuario.modificar.clave"/>">
						</form>
					</li>
					<li>
						<form action="prepararFormulario" method="POST">
							<s:hidden name="modificarDatos" value="%{true}" />
							<input style="text-transform: none; border: none;padding-left: 20px;border-bottom: 1px dotted #CCC;" class="btn_menu" type="submit" value="<s:text name="usuario.modificar.datos"/>">
						</form>
					</li>
				</ul>
			</li>			
		</ul>				
	</div>
</div>