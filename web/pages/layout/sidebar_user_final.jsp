<!-- Esta es la barra lateral -->
			<div id="sidebar">
				<table class="links">
					<tr>
						<th><a href="listarSector"><s:text name="home" /></a></th>
					</tr>					
				</table>			
				<table class="links">
					<tr>
						<th><s:text name="si_mas_Visitados" /></th>
					</tr>
					<s:if test='SI_masVisitados.size()>0'>
					<s:iterator value="SI_masVisitados" status="index">						
						<tr>
							<td>
								<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
										<s:property value="nombre"/> - (<s:property value="visitas"/>)
								</a>
							</td>
						</tr>						
					</s:iterator>
					</s:if>					
					<s:else>
						<tr>
							<td>
								<s:text name="sis_null3" />
							</td>
						</tr>						
					</s:else>
					<tr>
						<th>
							<a href="listarServicios"><s:text name="ver_servicios" /></a>
						</th>
					</tr>
				</table>
				<table class="links">
					<tr>
						<th><s:text name="sectores" /></th>
					</tr>
					<s:if test="listaSectores.size()>0">
					<s:iterator value="listaSectores" status="index">						
						<tr>
							<td>
								<a href="listarSector?id_sector=<s:property value="id_sector"/>">
									<s:property value="nombre"/> - (<s:property value="n"/>)
								</a>
							</td>
						</tr>						
					</s:iterator>
					</s:if>		
					<s:else>
						<tr>
							<td>
								<s:text name="sis_null3" />
							</td>
						</tr>						
					</s:else>
					<tr>
						<th>
							<a href="listarSectores"><s:text name="ver_sectores" /></a>
						</th>
					</tr>								
				</table>							
			</div>