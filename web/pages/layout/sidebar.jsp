<s:i18n name="ve/gob/cnti/srsi/i18n/sidebar">
<div id="menuv">
	<ul>
	  <li class="nivel1 primera">
	    <a href="<s:url action="home"/>" class="nivel1">
	    	<s:text name="inicio" />
	    </a>
	  </li>
	  <li class="nivel1">
	  	<a class="nivel1" style="font-size: 0.9em; padding-top:9px;padding-left: 16px;">
	  		<s:text name="servicios" />
	  	</a>					
		<ul class="nivel2">
			<li>
				<a href="<s:url action="prepararServicioInformacion"/>">
					<s:text name="registro" />
				</a>								
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
</s:i18n>