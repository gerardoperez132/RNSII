<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Tabs Princpales -->
<%@include file="tabs_principales.jsp"%>

<div class="tab_container" style="height: 450px;">
	<div class="tab_content">							
		<ul class="tabs">
			<li class="active">
				<a>
					<s:text name="tab1.title"/>
					<s:if test="id_funcionalidad > 0">						
						<img src="res/img/correcto.png" id="h2" alt="ayuda" 
						onmouseover="tip(this);" name="h2" height="15" width="15" />
					</s:if>
				</a>
			</li>
			<li><a><s:text name="tab.entrada"/> </a></li>
			<li>
				<a>
					<s:text name="tab.salida"/>
					
					<s:iterator value="tabs_incompletas" status="status">					
					<s:if test="tab==6 && id_funcionalidad > 0">
						<s:if test="detalles.size()>0">
							<img src="res/img/important.png" id="h6" alt="ayuda" 
							onmouseover="tip(this);" name="h6" height="15" width="15" />
						</s:if>							
						<s:elseif test="id_funcionalidad > 0">
							<img src="res/img/correcto.png" id="h6" alt="ayuda" 
							onmouseover="tip(this);" name="h6" height="15" width="15" />
						</s:elseif>
					</s:if>					
					</s:iterator>										
															 
				</a>
			</li>
			<li><a><s:text name="tab.resumen"/> </a></li>
		</ul>
		<div class="tab_container">
			<div id="tab1" class="tab_content">
				<s:if test="modificarf">
					<form action="modificarFuncionalidad" method="POST" id="formFunc" name="formFunc">											
						<!-- Nombre de la funcionalidad u operación del servicio. -->
						<h5 class="requerido">
							<s:text name="usuario.modificar.requerido" />
						</h5>
						<h5 class="formulario">
							<span style="color:red;">*</span>
							<s:text name="nombre.title"/>
							<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
							name="t1" id="t1" onmouseover="tip(this);" title="">
						</h5>
						<s:fielderror>
							<s:param>funcionalidad.nombre</s:param>
						</s:fielderror>
						<s:textfield name="funcionalidad.nombre" id="funcionalidad.nombre" />
						
						<br>
						<!-- Descripción de la funcionalidad u operación del servicio. -->
						<h5 class="formulario">
							<span style="color:red;">*</span>
							<s:text name="descripcion.title"/>
							<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
							name="t2" id="t2" onmouseover="tip(this);" title="">
						</h5>
						<s:fielderror>
							<s:param>funcionalidad.descripcion</s:param>
						</s:fielderror>
						<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" id="funcionalidad.descripcion" />
						
						<br>
						<s:token name="token" />
						<s:hidden name="id_servicio_informacion"/>
						<s:hidden name="id_funcionalidad"/>
						<s:hidden name="modificar"/>
						<s:hidden name="modificarf"/>
						<input type="submit" value="<s:text name="guardar"/>" class="button_h"/>
					</form>											
				</s:if>
				<s:else>
					<form action="registrarFuncionalidad" method="POST"
						id="formFunc" name="formFunc">											
						<!-- Nombre de la funcionalidad u operación del servicio. -->
						<h5 class="requerido">
							<s:text name="usuario.modificar.requerido" />
						</h5>
						<h5 class="formulario">
							<span style="color:red;">*</span>
							<s:text name="nombre.title"/>
							<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t1" id="t1" onmouseover="tip(this);" title="">
						</h5>
						<s:fielderror>
							<s:param>funcionalidad.nombre</s:param>
						</s:fielderror>
						<s:textfield labelposition="top" name="funcionalidad.nombre" id="funcionalidad.nombre" />
						
						<br>
						<!-- Descripción de la funcionalidad u operación del servicio. -->
						<h5 class="formulario">
							<span style="color:red;">*</span>
							<s:text name="descripcion.title"/>
							<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" 
								name="t2" id="t2" onmouseover="tip(this);" title="">
						</h5>
						<s:fielderror>
							<s:param>funcionalidad.descripcion</s:param>
						</s:fielderror>
						<s:textarea name="funcionalidad.descripcion" cols="30" rows="5" id="funcionalidad.descripcion" />
						
						<br>
						<s:hidden name="id_servicio_informacion"/>
						<s:hidden name="modificar"/>
						<s:hidden name="registrado"/>
						<s:token name="token" />
						<input type="submit" value='<s:text name="guardar"/>' class="button_h"/>
					</form>
				</s:else>
			</div>
		</div>
	</div>
</div>