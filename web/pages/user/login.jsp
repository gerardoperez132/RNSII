<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="#session.usuario.nombre.length()>0">
	<div class="ingresar" style="height: 120px;">
		<h1>
			<s:text name="bienvenido" />
			<s:property value="%{#session.usuario.nombre}" />
		</h1>
		<table class="main_user">
			<tr>
				<td>
					<h4 style="margin: 0;">
						<s:text name="ente" />
						<s:property value="%{#session.ente_sesion.nombre}" />
					</h4>
				</td>
			</tr>
			<s:if test="%{#sistema!=true}">
				<tr>
					<td>
						<div class="enlace_home">
							<a href="home"> <s:text name="back.registration" />
							</a>
						</div>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td>
						<div class="enlace_home">
							<a href="SRSI"> <s:text name="back.home" />
							</a>
						</div>
					</td>
				</tr>
			</s:else>
			<tr>
				<td>
					<div class="enlace_home">
						<a href="<s:url action="salir"/>"> <s:text name="salir" />
						</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</s:if>
<s:else>
	<div class="ingresar">
		
		<s:if test="recoveryPass">
			<form action="enviarDatos" method="post">
				<table>
					<tr>
						<td colspan="3">
							<p style="font-size: x-small; font-family: sans-serif;">
								<s:text name="recoveryPassInfo" />
							</p>
						</td>
					</tr>
					<tr>
						<td><s:text name="correo" /></td>
						<td><input type="text" name="correo" /></td>
						<td><img src="res/img/ayuda.gif" class="m_tip" alt="ayuda"
							name="t1" id="t1" onmouseover="tip(this);">
							<div class="t1" style="visibility: hidden; display: none;">
								<s:text name="mailHelp" />
							</div></td>
					</tr>
					<tr>
						<td><s:text name="captcha" /></td>
						<td><input type="text" name="captcha" maxlength="6" /></td>
						<td></td>
					</tr>
					<tr>					
						<td></td>
						<td id="captcha_pos" style="height: 35px;"><img src="getCaptcha" id="captcha"></td>
						<td><a href="SRSI" id="a_refresh"><img src="res/images/refresh.png" alt="Refrescar" id="refrescar"></a></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="submit"
							value="<s:text name="enviar" />" /></td>
						<td></td>
					</tr>
				</table>
				<s:hidden name="password" value="-1"></s:hidden>
			</form>
			<br>
		</s:if>
		<s:elseif test="recoveryPassForm">
			<form action="cambiarClave" method="post" id="formModificarClave"
				name="modificarClave">
				<table style="margin-left: 20px;">
					<tr>
						<td colspan="4"><s:fielderror>
								<s:param>password</s:param>
							</s:fielderror></td>
					<tr>
					<tr>
						<td colspan="4">
							<h5 class="requerido">
								<s:text name="usuario.modificar.requerido" />
							</h5>
						</td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.clave.nueva" /></td>
						<td><input type="password" name="clave_nueva" id="pass" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td><span id="passstrength"></span></td>
					<tr>
					<tr>
						<td><s:text name="usuario.modificar.clave.confirma" /></td>
						<td><input type="password" name="clave_nueva_confirme"
							id="pass2" /></td>
						<td><h5 class="requerido">*</h5></td>
						<td><span id="passequal"></span> <br></td>
					<tr>
					<tr>
						<td colspan="4"><s:token name="token" /> <s:hidden
								name="cuenta"></s:hidden> <input type="submit"
							id="cambiar_clave"
							value="<s:text name="usuario.modificar.clave"></s:text>">
						</td>
					<tr>
				</table>
			</form>
		</s:elseif>
		<s:else>
		
		<div class="mod-wrapper clearfix">		
			<h3 class="header"><s:text name="login" /></h3>
			<div class="mod-content clearfix">	
			<div class="mod-inner clearfix">
				<form action="index" method="post">
					<fieldset class="userdata">
					<table style="width: 200px;">
						<tr>
							<td>
							<p id="form-login-username">
								<label for="modlgn-username"><s:text name="user" /></label>
								<input id="modlgn-username" type="text" name="correo" size="18">						
							</p>
							</td>
							<td>
								<img src="res/img/ayuda.gif" alt="ayuda" class="m_tip" name="t1" id="t1" onmouseover="tip(this);"/>
								<div class="t1" style="visibility: hidden; display: none;">
									<s:text name="mailHelp" />
								</div>		
							</td>
						</tr>
						<tr>
							<td>
								<p id="form-login-password">
									<label for="modlgn-passwd"><s:text name="pass" /></label>
									<input type="password" name="password" maxlength="6" />
								</p>	
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td>
								<p id="form-login-password">
									<label for="modlgn-passwd"><s:text name="captcha" /></label>
									<input id="modlgn-passwd" type="text" name="captcha" maxlength="6" />
								</p>	
							</td>
							<td>								
							</td>
						</tr>
						<tr>
							<td style="padding-top: 16px;">								
								<img src="getCaptcha" id="captcha" alt="Captcha">									
							</td>
							<td style="padding-top: 16px;">
								<a href="SRSI" id="a_refresh"><img src="res/images/refresh.png" alt="Refrescar" id="refrescar"></a>								
							</td>
						</tr>
						<tr>
							<td style="padding-top: 16px;" colspan="2">								
								<input type="submit" name="Submit" value="<s:text name="entrar" />">																			
							</td>
						</tr>
					</table>	
					
					</fieldset>
				</form>
				<ul class="userlinks">					
					<li><a href="recuperar_clave"><s:text name="accessSystem" /></a></li>
				</ul>				
				</div>
			</div>
		</div>	
		</s:else>
	</div>
</s:else>
