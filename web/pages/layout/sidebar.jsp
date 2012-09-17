<%@taglib uri="/struts-tags" prefix="s"%>
<div class="sidebar">	
	<div class="busqueda_width_user busqueda">
		<form method="post" action="Buscar_Servicio">
			<label><s:text name="buscarServicio"/></label>
			<s:textfield name="cadena" id="buscar" cssClass="inputBusqueda buscar"/>
			<input type="submit" class="submit" value="&nbsp;"/>
		</form>
	</div>
	<br>
	<div class="m_a">
    <ul>
        <li class="m_a_fondo">
            <a class="m_a_titulo" href="<s:url action="home"/>">
            	<s:text name="home" />            	
            </a>
        </li>
        <li class="m_a_fondo">
            <a class="m_a_titulo">
            	<s:text name="servicio_informacion" />
            	<span class="m_a_desplegar">+</span>
            </a>            
            <ul class="m_a_top" style="display: block;">
                <li>
                <a class="m_a_item"  href="<s:url action="prepararRegistro"/>">
                	<s:text name="registro" />
                </a>
                </li>                
            </ul>
        </li>
        <li class="m_a_fondo">
            <a class="m_a_titulo" href="<s:url action="configuracionUsuario"/>">
            	<s:text name="configuración" />            	
            </a> 
        </li>
        <li class="m_a_fondo">
            <a class="m_a_titulo" href="<s:url action="salir"/>">
            	<s:text name="salir" />            	
            </a> 
        </li>
    </ul>       
    </div>
</div>