<%@ taglib prefix="s" uri="/struts-tags"%>
<ul class="tabs">
	<li>
	<a href="prepararDescripcionGeneral">
		<s:text name="tab1.title"></s:text>
		<s:iterator value="tabs_incompletas" status="status">
		<s:if test="tab==1">
			<s:if test="detalles.size()>0">
				<img src="res/img/important.png" id="h1" alt="ayuda" 
				onmouseover="tip(this);" name="h1" height="15" width="15" />
			</s:if>							
			<s:else>
				<img src="res/img/correcto.png" id="h1" alt="ayuda" 
				onmouseover="tip(this);" name="h1" height="15" width="15" />
			</s:else>
		</s:if>					
		</s:iterator>
	</a>
	</li>
	<li>
		<a href="prepararAspectosLegales">
			<s:text name="tab2.title"></s:text>							
		</a>
	</li>
	<li>
		<a href="prepararDescripcionTecnica">
			<s:text name="tab3.title"></s:text>
			<s:iterator value="tabs_incompletas" status="status">
			<s:if test="tab==3">
				<s:if test="detalles.size()>0">
					<img src="res/img/important.png" id="h3" alt="ayuda" 
					onmouseover="tip(this);" name="h3" height="15" width="15" />
				</s:if>							
				<s:else>
					<img src="res/img/correcto.png" id="h3" alt="ayuda" 
					onmouseover="tip(this);" name="h3" height="15" width="15" />
				</s:else>
			</s:if>					
			</s:iterator>
		</a>
	</li>
	<li class="active">
		<a>
			<s:text name="funcionalidades"></s:text>
			<s:iterator value="tabs_incompletas" status="status">
			<s:if test="tab==4">
				<s:if test="detalles.size()>0">
					<img src="res/img/important.png" id="h4" alt="ayuda" 
					onmouseover="tip(this);" name="h4" height="15" width="15" />
				</s:if>							
				<s:else>
					<img src="res/img/correcto.png" id="h4" alt="ayuda" 
					onmouseover="tip(this);" name="h4" height="15" width="15" />
				</s:else>
			</s:if>					
			</s:iterator>
		</a>
	</li>
	<li>
		<a href="prepararDescripcionSoporte">
			<s:text name="tab4.title"></s:text>
			<s:iterator value="tabs_incompletas" status="status">
			<s:if test="tab==5">
				<s:if test="detalles.size()>0">
					<img src="res/img/important.png" id="h5" alt="ayuda" 
					onmouseover="tip(this);" name="h5" height="15" width="15" />
				</s:if>							
				<s:else>
					<img src="res/img/correcto.png" id="h5" alt="ayuda" 
					onmouseover="tip(this);" name="h5" height="15" width="15" />
				</s:else>
			</s:if>					
			</s:iterator>
		</a>
	</li>
</ul>

<div class="tab_description">
	<div class="tab_description_left">
		<h3 class="formulario">
			<s:text name="funcionalidad.registro" />
		</h3>
		<small><s:text name="funcionalidad.registro.description"></s:text></small>
	</div>	
	<div class="tab_description_right">
	<s:if test="finalizar">					
		<br>
		<form action="terminar_registro_si" id="formSI" name="formSI" method="post" enctype="multipart/form-data">
			<input type="submit" class="tab_button" name="submit" value='<s:text name="registro.finalizar"></s:text>' id="btn_submit_2"/>
		</form>
	</s:if>	
	</div>																				
</div>