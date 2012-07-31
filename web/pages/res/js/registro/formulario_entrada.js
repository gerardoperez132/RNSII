var isLongitud;
var isFormato;

/*
 * funciones para el correcto funcionamiento de links de la vista de gobierno en linea
 */
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

$.validator.addMethod('regexTitle', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras y espacios');

$.validator.addMethod('regexDescription', function (value) {
	var soloTexto = new RegExp("^[a-zA-Z0-9 _.()áéíóúAÉÍÓÚÑñ]+$");		    	
	return soloTexto.test(value);
}, 'Sólo puede introducir letras, números y puntos');

$.validator.addMethod('formatoValidate', function (value) {	
	if($('#capa_formato').data("formato")){		
		if($("#entrada\\.id_formato").val()==-1){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}, 'Debe seleccionar un tipo de formato que corresponda con el dato elegido');

$.validator.addMethod('longitudValidate', function (value) {
	if($('#capa_longitud').data("longitud")){
		if($("#entrada\\.longitud").val().length < 1) {			
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}, 'Debe indicar la cantidad de dígitos que acepta el dato');

$(document).ready(function(){
	
	 /********************************************************************
	 * Validaciones para el formulario creación/modificación de una 
	 * Entrada/Salida.    
	 */
	  $("#formES").validate({	
		errorPlacement: function (error, element) { 
		    error.insertBefore(element);     
		},  
	    rules: {	    	
	    	'entrada.nombre': {required: true, regexTitle: true},
	    	'entrada.descripcion': {required: true, regexDescription: true},
	    	'entrada.id_tipo_dato': {min: 0},
	    	'entrada.id_formato': {formatoValidate:true},
	    	'entrada.longitud': {longitudValidate:true}
	    },
	    messages: {	    	
	    	'entrada.nombre': {required:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
	    	'entrada.descripcion': {required:"Debe introducir una descripción",regexDescription:'Sólo puede introducir letras, números y puntos'},	    	
	    	'entrada.id_tipo_dato': "Debe seleccionar un tipo de dato",
	    	'entrada.id_formato': "Debe seleccionar un tipo de formato que corresponda con el dato elegido",
	    	'entrada.longitud': 'Debe indicar la cantidad de dígitos que acepta el dato'
	    }
	  });
	
	var select = $("#entrada.id_formato").val();
	var i;
	for(i=2;i<=7;i++){
		$('#opt_group_'+i).remove();
	}
			
	/*
	 * Oculta las capas formato y longitud al cargar el DOM para esperar la elección del tipo 
	 * de dato y mostrarle que introdusca el formato y/o la longitud dependiendo
	 * de la naturaleza del dato primitivo.
	 */	
	limpiar_options();
	fomato_longitud_visible();
	
	if(select>0){		
		$('#opt_element_'+select).attr("selected",true);
	}
	select=-1;
	
	
	$("#entrada\\.id_tipo_dato").change(function (e){		
		limpiar_longitud_formato();	
		fomato_longitud_visible();		
	});
	
	/*
	 * Valida que solo metan números.
	 */	
	$('#entrada\\.longitud').keyup(function(e) {
		if($("#entrada\\.id_tipo_dato").val()==4){//decimales
			if($.isNumeric($("#entrada\\.longitud").val()) == false){			
				this.value = this.value.substring(0,(this.value.length-1));
				$('#longitud_msj').html('Debe escribir solo números. '); 
				$('#longitud_msj').attr('class', 'error_pass');
			}else{
				$('#longitud_msj').html('');			
			}
		}else{	//Naturales	
			if (!/^([0-9])*$/.test($("#entrada\\.longitud").val())){
				this.value = this.value.substring(0,(this.value.length-1));
				$('#longitud_msj').html('Debe escribir solo números. '); 
				$('#longitud_msj').attr('class', 'error_pass');
			}else{				
				$('#longitud_msj').html('');
			}
		}
	});
	
	function limpiar_options(){
		i =1;
		for(i=1;i<=13;i++){
			$('#opt_element_'+i).remove();			
		}
	}
	
	function limpiar_longitud_formato(){
		$("#entrada\\.longitud").val('');
		$("#entrada\\.id_formato").val('-1');
		limpiar_options();
	}
	
	function fomato_longitud_visible(){			
		
		/*
		 * Oculta las capas formato y longitud si el usuario no selecciona ningún tipo de dato.
		 */
		if($("#entrada\\.id_tipo_dato").val()==-1){
			$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');			
		}else{
			//Muestra la capa longitud si el dato posse esta caracteristica			
			  $.ajax({
				    type: 'GET',
				    url: 'dato_haslength.action',
				    cache: false,
				    async: false,
				    data: { id_tipo_dato: $("#entrada\\.id_tipo_dato").val() },
				    success: function(result){ 
				    	var boleano = new String ('' + result);
						if(boleano.toLowerCase().indexOf('l=true') != -1){					
							$('#capa_longitud').attr('style', 'visibility: visible; position:relative;').data("longitud",true);							
						}else{					
							$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;').data("longitud",false);
						}
				    }
			 });
			//Muestra la capa formato si el dato posse esta caracteristica
			  $.ajax({type: 'GET',url: 'dato_hasformatted.action',cache: false,async: false,
				    data: { id_tipo_dato: $("#entrada\\.id_tipo_dato").val() },
				    success: function(result2){ 
				    	var boleano = new String ('' + result2);				
						if(boleano.toLowerCase().indexOf('f=true') != -1){					
							$('#capa_formato').attr('style', 'visibility: visible; position:relative;').data("formato",true);														
							//Crea las opciones	del select formato	
							$.ajax({type: 'GET',url: 'list_format.action',cache: false,async: false,
							    data: { id_tipo_dato: $("#entrada\\.id_tipo_dato").val() },
							    success: function(result3){ 
							    	$("#entrada\\.id_formato").append(result3);	
							    }
							});														
						}else{					
							$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;').data("formato",false);	
						}
				    }				    
			 });			
		}	
		
	}
	
	/*
	 * funciones para el correcto funcionamiento de links de la vista de gobierno en linea
	 */	
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

