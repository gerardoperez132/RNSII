<%@taglib uri="/struts-tags" prefix="s"%>
	<div class="ingresar">	
		<div class="mod-wrapper clearfix">		
			<h3 class="header"><s:text name="login" /></h3>
			<div class="mod-content clearfix">	
			<div class="mod-inner clearfix">
				<form action="access_control" method="post">
					<fieldset class="userdata">
					<table style="width: 200px;">						
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
								<a href="RNSII" id="a_refresh"><img src="res/images/refresh.png" alt="Refrescar" id="refrescar"></a>								
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
				</div>
			</div>
		</div>		
	</div>