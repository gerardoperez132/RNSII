	<div id="sidebar">
		<div id="menuv">
			<ul>
				<li class="nivel1 primera">
					<a href="<s:url action="home"/>" class="nivel1"> 
						<s:text name="home" /> 
					</a>
				</li>
				<li class="nivel1">
					<a class="nivel1" style="font-size: 0.9em; padding-top: 9px; padding-left: 16px;">
						<s:text name="servicio_informacion" /> 
					</a>
					<ul class="nivel2">
						<li>
							<a href="<s:url action="prepararRegistro"/>"> 
								<s:text name="registro" /> 
							</a>
						</li>
						<li>
							<a class="nivel2"> <s:text name="suscripciones" /> </a>
							<ul>
								<li><a class="nivel3"><s:text name="solicitud3" /></a></li>
								<li>
									<a href="<s:url action="ListarSuscricionesPendientes"/>" class="nivel3">
										<s:text name="peticiones_otros_entes" />
									</a>
								</li>
								<li>
									<a href="<s:url action="listarSolicitudesAceptadasRechazadas"/>" class="nivel3">
										<s:text name="solicitudeRealizadas" />
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="nivel1">
					<a href="<s:url action="configuracionUsuario"/>" class="nivel1">
					 <s:text name="configuración" />
					</a>
				</li>
				<li class="nivel1">
					<a href="<s:url action="salir"/>" class="nivel1"> 
					  <s:text name="salir" /> 
					</a>
				</li>
			</ul>
		</div>
	</div>