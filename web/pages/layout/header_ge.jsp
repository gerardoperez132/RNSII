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
		<s:set name="mes" name="mes"><s:date name="fecha" format="MM"/></s:set>
		
        <span class="fecha">
        	<s:date name="fecha" format="dd" />
        	 de 
        	 <s:if test="%{#mes==01}">Enero,</s:if>
        	 <s:elseif test="%{#mes==02}">Febrero,</s:elseif>
        	 <s:elseif test="%{#mes==03}">Marzo,</s:elseif>
        	 <s:elseif test="%{#mes==04}">Abril,</s:elseif>
        	 <s:elseif test="%{#mes==05}">Mayo,</s:elseif>
        	 <s:elseif test="%{#mes==06}">Junio,</s:elseif>
        	 <s:elseif test="%{#mes==07}">Julio,</s:elseif>
        	 <s:elseif test="%{#mes==08}">Agosto,</s:elseif>
        	 <s:elseif test="%{#mes==09}">Septiembre,</s:elseif>
        	 <s:elseif test="%{#mes==10}">Octubre,</s:elseif>
        	 <s:elseif test="%{#mes==11}">Noviembre,</s:elseif>        	 
        	 <s:else>Diciembre,</s:else>
        	 <s:date name="fecha" format="yyyy" /></span> <span class="hora">Hora: <em><s:date name="fecha" format="hh:mm a" /></em></span>

			<select name="selectTiempo" id="selectTiempo" onchange="actualizarClima(this.value)">					
					<s:iterator value="estadosTiempo">						
						<option value="<s:property value="t_max"/>,<s:property value="t_min"/> "><s:property value="nombre"/></option>
						<s:if test="codigo == 1"> 
							<s:set name="tmax" value="%{''+t_max}"/>
							<s:set name="tmin" value="%{''+t_min}"/>
						</s:if>
					</s:iterator>					
			</select>
        
		<div id="tiempo_ciudad">
			<span class="tiempo"><em>&nbsp;</em> T max: <a class="estilo2" id="t_max"><s:property value="%{tmax}"/></a>
			&nbsp;&nbsp;&nbsp;
			T min: <a class="estilo2" id="t_min"><s:property value="%{tmin}"/></a> 			
			</span>
       		<a href="http://www.inameh.gob.ve/" class="otrasciudades" target="_blank" title="INAMEH"></a>
        </div>
</div>
</form>