<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="formatos.size()">	
		<s:iterator value="formatos">			
				<option class="opt_element" id="opt_element_<s:property value="id_formato"/>"  
					value="<s:property value="id_formato"/>">
					<s:property value="formato"/>
				</option>												
		</s:iterator>	
</s:if>