
<div class="header">
	<div class="logo" onclick="window.location.href='homeG.dot'" style="cursor:pointer"></div>
	<div class="anios"></div>
</div>
<div  class="headerInterna">
	<a onclick="window.location.href='homeG.dot'" style="cursor:pointer" ><img  src="res/images/logo_web_interno.png" alt="Logo Gobierno en L&iacute;nea" title="Logo Gobierno en L&iacute;nea"  /></a>
	<div class="ContenedorHeaderMenu" >
		<ul>
			<li class="CiudadanoH"><a onclick="changeValue('perfilesG.dot','perfilHeader','ci','GenericHeaderForm');"  style="background:url(res/images/menu_interno_es.png) no-repeat scroll 0 0 transparent;">Ciudadanoo</a></li>
			<li class="EmpresasH"><a onclick="changeValue('perfilesG.dot','perfilHeader','em','GenericHeaderForm');"  style="background:url(res/images/menu_interno_es.png) no-repeat scroll -208px 0 transparent;">Empresaa</a></li>
			<li class="ExtranjerosH"><a onclick="changeValue('perfilesG.dot','perfilHeader','ex','GenericHeaderForm');"  style="background:url(res/images/menu_interno_es.png) no-repeat scroll -415px 0 transparent;">Extranjeroo</a></li>
			<li class="ParticipacionH"><a onclick="changeValue('perfilesG.dot','perfilHeader','pc','GenericHeaderForm');"  style="background:url(res/images/menu_interno_es.png) no-repeat scroll -623px 0 transparent;">Participacionn</a></li>
		</ul>
	</div>
	
</div>

<div class="info_bar">
	<a href="http://gobiernoenlinea.gob.ve/home/homeG.dot" class="inicio" title="Inicio">inicio</a>
		<a href="http://gobiernoenlinea.gob.ve/home/ayuda.dot"  class="pregunta" title="Ayuda">inicio</a>

        <div id="fontResize">
                <h2> <a href="#" class="fontSizePlus" title="Aumentar Fuente">A+</a></h2>
                <h2> <a href="#" class="fontSizeMinus" title="Disminuir Fuente">A-</a></h2>
        </div>
	
	<!--  Language Select Box code begins here -->  
	
	<input type="hidden" name="com.dotmarketing.htmlpage.language" id="com.dotmarketing.htmlpage.language" value="2" />  	
						       	        		        	<!--  Language Select Box code ends here -->  

        <span class="fecha">11 de Julio, 2012</span> <span class="hora">Hora: <em>01:41 PM</em></span>

			<select name="selectTiempo" id="selectTiempo">
					<option onclick="actualizarClima('32','23')">Amazonas</option>
					<option onclick="actualizarClima('32','21')">Anzoategui</option>
					<option onclick="actualizarClima('33','22')">Apure</option>
					<option onclick="actualizarClima('32','22')">Aragua</option>
					<option onclick="actualizarClima('33','22')">Barinas</option>
					<option onclick="actualizarClima('32','22')">Bolivar</option>
					<option onclick="actualizarClima('32','21')">Carabobo</option>
					<option onclick="actualizarClima('32','20')">Cojedes</option>
					<option onclick="actualizarClima('33','22')">Delta Amacuro</option>
					<option onclick="actualizarClima('31','18')">Distrito Capital</option>
					<option onclick="actualizarClima('32','22')">Falcon</option>
					<option onclick="actualizarClima('33','22')">Guarico</option>
					<option onclick="actualizarClima('31','21')">Isla de Aves</option>
					<option onclick="actualizarClima('32','20')">Lara</option>
					<option onclick="actualizarClima('28','17')">Merida</option>
					<option onclick="actualizarClima('31','20')">Miranda</option>
					<option onclick="actualizarClima('32','22')">Monagas</option>
					<option onclick="actualizarClima('33','22')">Nueva Esparta</option>
					<option onclick="actualizarClima('33','21')">Portuguesa</option>
					<option onclick="actualizarClima('32','22')">Sucre</option>
					<option onclick="actualizarClima('29','19')">Tachira</option>
					<option onclick="actualizarClima('31','19')">Trujillo</option>
					<option onclick="actualizarClima('32','20')">Vargas</option>
					<option onclick="actualizarClima('31','20')">Yaracuy</option>
					<option onclick="actualizarClima('34','22')">Zulia</option>
			</select>
        
	<div id="tiempo_ciudad">
					<span class="tiempo"> <em>&nbsp;</em> T max: 34&nbsp;&nbsp;&nbsp;T min: 22</span> <a href="http://www.inameh.gob.ve/" class="otrasciudades" target="_blank" title="INAMEH"></a>
			</div>
        <div class="otrasciudades_cont"> <a href="#" class="cerrar">cerrar</a>
                <div class="ciudadseleccionado">
                        <p><span>HOY</span></p>
                        <div class="col1">
                                <ul>
                                        <li class="icon"><em>&nbsp;</em>T: <strong>27.9&deg;</strong></li>

                                        <li>ST: <strong>27.9&deg;</strong></li>
                                        <li style="border: none">H: <strong>50%;</strong></li>
                                </ul>
                        </div>
                        <div class="col2">
                                <select name="select">
                                        <option>Otras ciudades</option>

                                </select>
                                <a href="#" class="vermas">Ver mas sobre clima</a></div>
                </div>
        </div>
</div>