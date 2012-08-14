/*
 * Variable con las claves de intercionalización del archivo json. 
 */
var data;

var isLongitud;
var isFormato;

$(document).ready(function() {	
	
	/*
	 * Obteniendo los valores de intercionalización del archivo JSON
	 */
	$.ajax({
		url: "getJSONResult.action",		
		type: "GET",
		dataType: "json",
		async:false,		
		success: function(source){	
			data = source;			
		}	
	});

	/*
	 * Creación de método para validar una expresión regular del tipo title,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexTitle', function (value) {	
		var valor = value;
		var soloTexto = new RegExp(data['constants']['REGEX_TITLE']);		    	
		return soloTexto.test(valor.toUpperCase());
	});

	/*
	 * Creación de método para validar una expresión regular del tipo description,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexDescription', function (value) {
		var valor = value;
		var soloTexto = new RegExp(data['constants']['REGEX_DESCRIPTION']);		
		return soloTexto.test(valor.toUpperCase());
	});

	/*
	 * Creación de método para validar si el usuario ha escogido un formato,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('formatoValidate', function (value) {	
		if($('#capa_formato').data("formato")){		
			if($("#salida\\.id_formato").val()==-1){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	});

	/*
	 * Creación de método para validar si el usuario ha introduccido una longitud,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('longitudValidate', function (value) {
		if($('#capa_longitud').data("longitud")){
			if($("#salida\\.longitud").val().length < 1) {			
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	});

	
	 /*
	 * Validaciones para el formulario creación/modificación de una 
	 * Entrada/Salida.    
	 */
	  $("#formES").validate({	
		errorPlacement: function (error, element) { 
		    error.insertBefore(element);     
		},  
	    rules: {	    	
	    	'salida.nombre': {required: true, regexTitle: true},
	    	'salida.descripcion': {required: true, regexDescription: true},
	    	'salida.id_tipo_dato': {min: 0},
	    	'salida.id_formato': {formatoValidate:true},
	    	'salida.longitud': {longitudValidate:true}
	    },
	    messages: {	
	    	'salida.nombre': {required:data['errores']['error.salida.nombre'],regexTitle:data['errores']['error.regex.title']},
	    	'salida.descripcion': {required:data['errores']['error.salida.descripcion'],regexDescription:data['errores']['error.regex.description']},	    	
	    	'salida.id_tipo_dato': data['errores']['error.salida.tipodato'],
	    	'salida.id_formato': data['errores']['error.salida.format'],
	    	'salida.longitud': data['errores']['error.salida.longitud'],
	    }
	  });
	
	var select = $("#salida.id_formato").val();
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
	
	
	$("#salida\\.id_tipo_dato").change(function (e){		
		limpiar_longitud_formato();	
		fomato_longitud_visible();		
	});
	
	/*
	 * Valida que solo metan números.
	 */	
	$('#salida\\.longitud').keyup(function(e) {
		if($("#salida\\.id_tipo_dato").val()==4){//decimales
			if($.isNumeric($("#salida\\.longitud").val()) == false){			
				this.value = this.value.substring(0,(this.value.length-1));
				$('#longitud_msj').html(data['errores']['error.num']);  
				$('#longitud_msj').attr('class', 'error_pass');
			}else{
				$('#longitud_msj').html('');			
			}
		}else{	//Naturales	
			if (!/^([0-9])*$/.test($("#salida\\.longitud").val())){
				this.value = this.value.substring(0,(this.value.length-1));
				$('#longitud_msj').html(data['errores']['error.num']); 
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
		$("#salida\\.longitud").val('');
		$("#salida\\.id_formato").val('-1');
		limpiar_options();
	}
	
	function fomato_longitud_visible(){			
		
		/*
		 * Oculta las capas formato y longitud si el usuario no selecciona ningún tipo de dato.
		 */
		if($("#salida\\.id_tipo_dato").val()==-1){
			$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');			
		}else{
			//Muestra la capa longitud si el dato posse esta caracteristica			
			  $.ajax({
				    type: 'GET',
				    url: 'dato_haslength.action',
				    cache: false,
				    async: false,
				    data: { id_tipo_dato: $("#salida\\.id_tipo_dato").val() },
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
				    data: { id_tipo_dato: $("#salida\\.id_tipo_dato").val() },
				    success: function(result2){ 
				    	var boleano = new String ('' + result2);				
						if(boleano.toLowerCase().indexOf('f=true') != -1){					
							$('#capa_formato').attr('style', 'visibility: visible; position:relative;').data("formato",true);														
							//Crea las opciones	del select formato	
							$.ajax({type: 'GET',url: 'list_format.action',cache: false,async: false,
							    data: { id_tipo_dato: $("#salida\\.id_tipo_dato").val() },
							    success: function(result3){ 
							    	$("#salida\\.id_formato").append(result3);	
							    	$('#opt_element_'+$("#salida\\.id_formato").attr('class')+'').attr('selected', 'selected');
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