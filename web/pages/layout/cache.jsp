<%@page import="ve.gob.cnti.srsi.modelo.Usuario"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.addHeader("Cache-Control", "must-revalidate");
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	Usuario u = (Usuario) request.getSession().getAttribute("usuario");
	if (u == null) {
%>
<meta http-equiv="refresh" content="0; URL=autenticar.action">
<%
	}
%>