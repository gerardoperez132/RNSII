	<%@taglib uri="/struts-tags" prefix="s"%>
	<div class="sidebar_left">				
		<ul class="pureCssMenu pureCssMenum">			
			<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="home"/>" title="Inicio"><s:text name="home" /></a></li>
			<li class="pureCssMenui"><a class="pureCssMenui" href="#"><span><s:text name="servicio_informacion" /></span></a>
			<ul class="pureCssMenum">
				<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="prepararRegistro"/>"><s:text name="registro" /></a></li>		
				<li class="pureCssMenui"><a class="pureCssMenui" href="#" title="Suscripciones"><span><s:text name="suscripciones" /></span></a>
					<ul class="pureCssMenum">
						<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="prepararSolicitarSuscripcion"/>"><s:text name="solicitud3" /></a></li>
						<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="ListarSuscricionesPendientes"/>"><s:text name="peticiones_otros_entes" /></a></li>
						<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="listarSolicitudesAceptadasRechazadas"/>"><s:text name="solicitudeRealizadas" /></a></li>
					</ul>
				</li>
		
			</ul>
			<!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
			<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="configuracionUsuario"/>"><s:text name="configuración" /></a></li>
			<li class="pureCssMenui"><a class="pureCssMenui" href="<s:url action="salir"/>" title="Cerrar sesión"><s:text name="salir" /></a></li>
		</ul>		
	</div>