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
		}
		
		/*$("#formato").append('');
		 *Muestra la capa longitud cuando el tipo de dato es string 
		 */
		if($("#tipoDato").val()==2){
			$('#capa_longitud').attr('style', 'visibility: visible; position:relative;');
			$('#capa_formato').attr('style', 'visibility: hidden; position:fixed;');
		}
		
		/*
		 *Muestra la capa longitud y formato cuando el tipo de dato es integer 
		 */
		if($("#tipoDato").val()==3){
			$('#capa_longitud').attr('style', 'visibility: visible; position:relative;');
			$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
			$("#formato").append('<option id="opt_element_1" value="1">Con Signo</option>');
			$("#formato").append('<option id="opt_element_2" value="2">Sin Signo</option>');
		}
		
		/*
		 *Muestra la capa longitud y formato cuando el tipo de dato es decimal
		 */
		if($("#tipoDato").val()==4){
			$('#capa_longitud').attr('style', 'visibility: visible; position:relative;');
			$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
			$("#formato").append('<option id="opt_element_3" value="3">Con Signo</option>');
			$("#formato").append('<option id="opt_element_4" value="4">Sin Signo</option>');
		}
		
		/*
		 *Muestra la capa formato cuando el tipo de dato es boolean
		 */
		if($("#tipoDato").val()==5){
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
			$("#formato").append('<option id="opt_element_5" value="5">True - False</option>');
			$("#formato").append('<option id="opt_element_6" value="6">Verdadero - Falso</option>');
			$("#formato").append('<option id="opt_element_7" value="7">1 - 0</option>');
		}
		
		/*
		 *Muestra la capa formato cuando el tipo de dato es date
		 */
		if($("#tipoDato").val()==6){
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
			$("#formato").append('<option id="opt_element_8" value="8">YYYY-MM-DD</option>');
			$("#formato").append('<option id="opt_element_9" value="9">DD-MM-YYYY</option>');
			$("#formato").append('<option id="opt_element_10" value="10">YYYY-MM-DDThh:mm:ss</option>');
			$("#formato").append('<option id="opt_element_11" value="11">DD-MM-YYYYThh:mm:ss</option>');
		}		
		
		/*
		 *Muestra la capa formato cuando el tipo de dato es time			
		 */
		if($("#tipoDato").val()==7){
			$('#capa_longitud').attr('style', 'visibility: hidden; position:fixed;');
			$('#capa_formato').attr('style', 'visibility: visible; position:relative;');
			$("#formato").append('<option id="opt_element_12" value="12">hh:mm:ss</option>');
			$("#formato").append('<option id="opt_element_13" value="13">hh:mm</option>');
		}		
		
	}
});