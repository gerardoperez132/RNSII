$(document).ready(function() {
		$("#tree").treeTable();
		
		$(".masPie").hide();
	    
	    $(".cerrarPie").toggle(
	         function () {
	            $(".masPie").slideDown();
	            $(".cerrarPie").text("-");
	     		return false;
	         },
	         function () {
	            $(".masPie").slideUp();
	            $(".cerrarPie").text("+");
	     		return false;                    
	         }
	       );	    
	});
	function changeValues(page, campoId1, valor1, campoId2, valor2, form){
	    var campo1 = document.getElementById(campoId1);
	    campo1.value = valor1;
	    var campo2 = document.getElementById(campoId2);
	    campo2.value = valor2;                      
	    submitForm(page, form);	   
	}
	function changeValue(page, campoId, valor, form){
		var campo = document.getElementById(campoId);
		campo.value = valor;			
		submitForm(page, form);
	}
	function submitForm(page, form){
	    var form = document.getElementById(form);
	    form.action= "http://gobiernoenlinea.gob.ve/home/" + page;	    
	    form.submit();
	}	
	function actualizarClima(cadena){
		var T = cadena.split(",");
	    $('#t_max').html(''+T[0]);
	    $('#t_min').html(''+T[1]);
	}