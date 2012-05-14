<!-- Esta es la barra lateral -->
			<div id="sidebar">
				<table class="links">
					<tr>
						<th><a href="listarSector"><s:text name="home" /></a></th>
					</tr>					
				</table>
				<table class="links">
					<tr>
						<th><a href="autenticarUsuario"><s:text name="login" /></a></th>
					</tr>					
				</table>
				<table class="links">
					<tr>
						<th><a href="listarServicios"><s:text name="SI" /></a></th>
					</tr>					
				</table>
				<table class="links">
					<tr>
						<th><a href="listarSectores"><s:text name="sectores" /></a></th>
					</tr>
					<s:iterator value="listaSectores" status="index">
						<s:if test="#index.index<5">
						<tr>
							<td>
								<a href="listarSector?id_sector=<s:property value="id_sector"/>">
									<s:property value="nombre"/> - (<s:property value="n"/>)
								</a>
							</td>
						</tr>
						</s:if>
					</s:iterator>					
				</table>	
				<table class="links">
					<tr>
						<th><s:text name="si_mas_Visitados" /></th>
					</tr>
					<s:iterator value="SI_masVisitados" status="index">						
						<tr>
							<td>
								<a href="servicio?id_servicio=<s:property value="id_servicio_informacion"/>">
										<s:property value="nombre"/> - (<s:property value="visitas"/>)
								</a>
							</td>
						</tr>						
					</s:iterator>					
				</table>		
			</div>