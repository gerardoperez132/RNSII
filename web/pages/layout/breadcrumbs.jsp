<!-- Ruta de navegación -->
<div id="breadcrumbs" class="sp-inner clearfix">
	<span class="breadcrumbs">
		<span class="showhome">Está	aquí: </span>
		<a href="http://10.8.6.62/interoperabilidad/">Inicio</a>
		<s:if test="consulta_SIxSector">
			<a href="RNSII.action"><s:text name="catalogo" /></a>
			<a href="listarSectores"><s:text name="sectores" /></a>
			<span class="current"><s:property value="sector.nombre" /></span>
		</s:if>
		<s:elseif test="consulta_listarSectores">
			<a href="RNSII.action"><s:text name="catalogo" /></a>
			<span class="current"><s:text name="sectores" /></span>
		</s:elseif>
		<s:elseif test="consulta_listarServicios">
			<a href="RNSII.action"><s:text name="catalogo" /></a>
			<span class="current"><s:text name="listaServicios" /></span>
		</s:elseif>
		<s:elseif test="buscarServicio">
			<a href="RNSII.action"><s:text name="catalogo" /></a>
			<span class="current"><s:text name="listaServiciosEncontrados" /></span>
		</s:elseif>
		<s:elseif test="examinarServicio">
			<a href="RNSII.action"><s:text name="catalogo" /></a>
			<a href="listarSectores"><s:text name="sectores" /></a>
			<a href="listarSector?id_sector=<s:property value='servicio.id_sector'/>"><s:property value="sector.nombre" /></a>
			<span class="current"><s:property value="servicio.nombre" /></span>
		</s:elseif>
		<s:else>
			<span class="current"><s:text name="catalogo" /></span>
		</s:else>
	</span>
	<a id="topofpage" href="#" rel="nofollow">Subir</a>
</div>
