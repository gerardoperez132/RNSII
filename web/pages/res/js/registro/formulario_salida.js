var isLongitud;
var isFormato;

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
		if($("#salida\\.id_formato").val()==-1){
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
		if($("#salida\\.longitud").val().length < 1) {			
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
	    	'salida.nombre': {required: true, regexTitle: true},
	    	'salida.descripcion': {required: true, regexDescription: true},
	    	'salida.id_tipo_dato': {min: 0},
	    	'salida.id_formato': {formatoValidate:true},
	    	'salida.longitud': {longitudValidate:true}
	    },
	    messages: {	    	
	    	'salida.nombre': {required:"Debe introducir un nombre",regexTitle:'Sólo puede introducir letras y espacios'},
	    	'salida.descripcion': {required:"Debe introducir una descripción",regexDescription:'Sólo puede introducir letras, números y puntos'},	    	
	    	'salida.id_tipo_dato': "Debe seleccionar un tipo de dato",
	    	'salida.id_formato': "Debe seleccionar un tipo de formato que corresponda con el dato elegido",
	    	'salida.longitud': 'Debe indicar la cantidad de dígitos que acepta el dato'
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
				$('#longitud_msj').html('Debe escribir solo números. '); 
				$('#longitud_msj').attr('class', 'error_pass');
			}else{
				$('#longitud_msj').html('');			
			}
		}else{	//Naturales	
			if (!/^([0-9])*$/.test($("#salida\\.longitud").val())){
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
							    }
							});														
						}else{					
							$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;').data("formato",false);	
						}
				    }				    
			 });			
		}	
		
	}
	
	
});