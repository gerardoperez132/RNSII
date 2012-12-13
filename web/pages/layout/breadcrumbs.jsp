<!-- Ruta de navegación -->

	
		
		
	</span>			
	<a id="topofpage" href="#" rel="nofollow">Subir</a>
</div>
<div id="breadcrumbs" class="sp-inner clearfix">
	<span class="breadcrumbs">
	<span class="showhome">Está aquí: </span>
	<a href="http://10.8.6.62/interoperabilidad/">Inicio</a>	
<s:if test="consulta_SIxSector">
	<span class="separator"><a href="SRSI.action"><s:text name="catalogo" /></a></span>
	<span class="separator"><a
			href="listarSectores"><s:text name="sectores" /></a></span>
			<span class="current"> <a href="#"><s:property
				value="sector.nombre" /></a></span>
		 
	</div>
</s:if>
<s:elseif test="consulta_listarSectores">
	<div class="RutaNavegacion">
		<a href="SRSI.action"><s:text name="catalogo" /></a> <a
			href="listarSectores"><s:text name="sectores" /></a>
	</div>
</s:elseif>
<s:elseif test="consulta_listarServicios">
	<div class="RutaNavegacion">
		<a href="SRSI.action"><s:text name="catalogo" /></a> <a href="#"><s:text
				name="listaServicios" /></a>
	</div>
</s:elseif>
<s:elseif test="buscarServicio">
	<div class="RutaNavegacion">
		<a href="SRSI.action"><s:text name="catalogo" /></a> <a href="#"><s:text
				name="listaServiciosEncontrados" /></a>
	</div>
</s:elseif>
<s:elseif test="examinarServicio">
	<div class="RutaNavegacion">
		<a href="SRSI.action"><s:text name="catalogo" /></a> <a
			href="listarSectores"><s:text name="sectores" /></a> <a
			href="listarSector?id_sector=<s:property value='servicio.id_sector'/>"><s:property
				value="sector.nombre" /></a> <a href="#"><s:property
				value="servicio.nombre" /></a>
	</div>
</s:elseif>
<s:else>
	<div class="RutaNavegacion">
		<a href="SRSI.action"><s:text name="catalogo" /></a>
	</div>
</s:else>
