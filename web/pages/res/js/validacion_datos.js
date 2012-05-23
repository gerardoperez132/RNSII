$(document).ready(function(){	
	
	var select = $("#formato").val();
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
	
	
	$("#tipoDato").change(function (e){
		
		limpiar_longitud_formato();	
		fomato_longitud_visible();
		
	});
	
	/*
	 * Valida que solo metan números.
	 */	
	$('#longitud').keyup(function(e) {
		if($("#tipoDato").val()==4){//decimales
			if($.isNumeric($("#longitud").val()) == false){			
				this.value = this.value.substring(0,(this.value.length-1));
				$('#longitud_msj').html('Debe escribir solo números. '); 
				$('#longitud_msj').attr('class', 'error_pass');
			}else{
				$('#longitud_msj').html('');			
			}
		}else{	//Naturales	
			if (!/^([0-9])*$/.test($("#longitud").val())){
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
		$("#longitud").val('');
		$("#formato").val('-1');
		limpiar_options();
	}
	
	function fomato_longitud_visible(){			
		
		/*
		 * Oculta las capas formato y longitud si el usuario no selecciona ningún tipo de dato.
		 */
		if($("#tipoDato").val()==-1){
			$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');			
		}else{
			//Muestra la capa longitud si el dato posse esta caracteristica
			  $.ajax({
				    type: 'GET',
				    url: 'dato_haslength.action',
				    cache: false,
				    async: false,
				    data: { id_tipo_dato: $("#tipoDato").val() },
				    success: function(result){ 
				    	var boleano = new String ('' + result);
						if(boleano.toLowerCase().indexOf('l=true') != -1){					
							$('#capa_longitud').attr('style', 'visibility: visible; position:relative;');
						}else{					
							$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');
						}
				    }
			 });
			//Muestra la capa formato si el dato posse esta caracteristica
			  $.ajax({type: 'GET',url: 'dato_hasformatted.action',cache: false,async: false,
				    data: { id_tipo_dato: $("#tipoDato").val() },
				    success: function(result2){ 
				    	var boleano = new String ('' + result2);				
						if(boleano.toLowerCase().indexOf('f=true') != -1){					
							$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
							//Crea las opciones	del select formato	
							$.ajax({type: 'GET',url: 'list_format.action',cache: false,async: false,
							    data: { id_tipo_dato: $("#tipoDato").val() },
							    success: function(result3){ 
							    	$("#formato").append(result3);	
							    }
							});														
						}else{					
							$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;');
						}
				    }				    
			 });			
		}	
		
	}
	
	
});