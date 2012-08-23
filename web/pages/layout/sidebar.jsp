<%@taglib uri="/struts-tags" prefix="s"%>
<div class="sidebar">
	
	<div class="secciones" style="clear: both;">		
		<ul class="ui-menu-content">
			<li class="li-menu-content">				
				<a href="<s:url action="home"/>" title="Inicio"><s:text name="home" /></a>				
			</li>	
			<li class="li-menu-content">				
				<a><s:text name="servicio_informacion" /></a>				
			</li>
			<li class="li-menu-content">				
				<a style="padding-left: 10px;" href="<s:url action="prepararRegistro"/>"><s:text name="registro" /></a>				
			</li>
			<li class="li-menu-content">				
				<a style="padding-left: 10px;" href="#"><s:text name="suscripciones" /></a>		
			</li>
			<li class="li-menu-content">				
				<a style="padding-left: 25px;" href="<s:url action="prepararSolicitarSuscripcion"/>"><s:text name="solicitud3" /></a>			
			</li>
			<li class="li-menu-content">				
				<a style="padding-left: 25px;" href="<s:url action="ListarSuscricionesPendientes"/>"><s:text name="peticiones_otros_entes" /></a>				
			</li>			
			<li class="li-menu-content">				
				<a style="padding-left: 25px;" href="<s:url action="listarSolicitudesAceptadasRechazadas"/>"><s:text name="solicitudeRealizadas" /></a>				
			</li>
			<li class="li-menu-content">				
				<a href="<s:url action="configuracionUsuario"/>"><s:text name="configuración" /></a>				
			</li>
			<li class="li-menu-content">				
				<a href="<s:url action="salir"/>" title="Cerrar sesión"><s:text name="salir" /></a>				
			</li>
		</ul>		
	</div>
</div>