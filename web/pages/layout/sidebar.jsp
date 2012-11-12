<%@taglib uri="/struts-tags" prefix="s"%>
<s:set var="sistema" scope="action" value="true"></s:set>
<div class="sidebar sidebar_user_final">
	<div class="busqueda">
		<form method="post" action="Buscar_Servicio">
			<label><s:text name="buscarServicio" /></label>
			<s:textfield name="cadena" id="buscar"
				cssClass="inputBusqueda buscar" />
			<input type="submit" class="submit" value="&nbsp;" />
		</form>
	</div>
	<br>
	<div style="clear: both"></div>
	<%@include file="../user/login.jsp"%>
	<div style="clear: both"></div>
	<div class="m_a">
		<ul>
			<li class="m_a_fondo">
				<a class="m_a_titulo" href="<s:url action="home"/>"> <s:text name="home" /></a>
			</li>
			<li class="m_a_fondo">
				<a class="m_a_titulo"><s:text name="servicio_informacion" /> <span class="m_a_desplegar">+</span></a>
				<ul class="m_a_top" style="display: block;">
					<li>
						<a class="m_a_item"	href="<s:url action="prepararRegistro"/>"><s:text name="registro" /></a>
					</li>
				</ul>
			</li>
			<li class="m_a_fondo">
				<a class="m_a_titulo"><s:text name="configuración" /> <span class="m_a_desplegar">+</span></a>
				<ul class="m_a_top" style="display: block;">
					<li>
						<form action="prepararFormulario" method="POST">
							<s:hidden name="modificarClave" value="%{true}" />
							<input class="btn_menu" type="submit" value="<s:text name="usuario.modificar.clave"/>">
						</form>
					</li>
					<li>
						<form action="prepararFormulario" method="POST">
							<s:hidden name="modificarDatos" value="%{true}" />
							<input class="btn_menu" type="submit" value="<s:text name="usuario.modificar.datos"/>">
						</form>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
