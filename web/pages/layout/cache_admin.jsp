<%
	response.setHeader("Pragma", "no-cache");
	response.addHeader("Cache-Control", "must-revalidate");
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);	
	String admin;
	try {
		admin = (String) request.getSession().getAttribute("admin").toString();
		if (admin == null) {
			%><meta http-equiv="refresh" content="0; URL=admin.action"><%
		}
	} catch (Exception e) {
		%><meta http-equiv="refresh" content="0; URL=admin.action"><%
	}	
%>
