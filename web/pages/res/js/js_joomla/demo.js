$(document).ready(function() {
	$("#mn1").mouseenter(function() {	    
	    $("#submenu_mn1").attr("style", "display: block;");
	  }).mouseleave(function() {
		  $("#submenu_mn1").attr("style", "display: none;");
	  });
});
