<%@taglib uri="/struts-tags" prefix="s"%>
<form action="http://gobiernoenlinea.gob.ve/home/perfilesG.dot" method="post" name="GenericHeaderForm" id="GenericHeaderForm">
	<input type="hidden" name="idSeccion" id="idSeccionHeader" value="136156" />
	<input type="hidden" name="idNoticia" id="idNoticiaHeader" value="0" />
	<input type="hidden" name="idTramite" id="idTramiteHeader" value="0" />
	<input type="hidden" name="idVideo" id="idVideoHeader" value="0" />
	<input type="hidden" name="idGaleria" id="idGaleriaHeader" value="0" />
	<input type="hidden" name="idCategoria" id="idCategoriaHeader" value="0" />
	<input type="hidden" name="idForo" id="idForoHeader" value="0" />
	<input type="hidden" name="letraBusqueda" id="letraBusquedaHeader" value="A" />
	<input type="hidden" name="letra" id="letraHeader" value="A" />
	<input type="hidden" name="tipo" id="tipoHeader" value="$request.getParameter('tipo')" />
	<input type="hidden" name="perfil" id="perfilHeader" value="ci" />
	<input type="hidden" name="ordenado" id="ordenadoHeader" value="$request.getParameter('ordenado')" />
	<input type="hidden" name="indice" id="indiceHeader" value="$request.getParameter('indice')" />
	<input type="hidden" name="ordenamientoForo" id="ordenamientoForoHeader" value="$request.getParameter('ordenamientoForo')" />
<div class="header">
	<div class="logo" onclick="window.location.href='http://gobiernoenlinea.gob.ve/home/homeG.dot'" style="cursor:pointer"></div>
	<div class="anios"></div>
</div>
<div  class="headerInterna">
	<a href="http://gobiernoenlinea.gob.ve/home/homeG.dot" style="cursor:pointer;" class="estilo2"><img  src="res/images/logo_web_interno.png" alt="Logo Gobierno en L&iacute;nea" title="Logo Gobierno en L&iacute;nea"  /></a>
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
</div>
</form>