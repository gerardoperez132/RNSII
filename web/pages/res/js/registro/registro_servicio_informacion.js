/*
 * Variable con las claves de intercionalización del archivo json. 
 */
var data;

$(document).ready(function() {	
	
	/*
	 * Desabilitando el boton de enviar formulario
	 */
	$("#btn_submit").attr('disabled',true);
	
	/*
	 * habilitando el boton submit si alguno de los campos es presionado
	 * y todos los demas campos estan completos.
	 */
	//tab1
	$("#sector").change(function(){tab1_validate();});
	$("#servicio\\.nombre").keypress(function(){tab1_validate();});
	$("#servicio\\.descripcion").keypress(function(){tab1_validate();});
	$('[name="area"]').click(function(){tab1_validate();});
	$("#estado").change(function(){tab1_validate();});
	//tab3 
	$("#seguridad").change(function(){tab3_validate();});
	$('[name="arquitectura"]').click(function(){tab3_validate();});
	$("#servicio\\.version").keypress(function(){tab3_validate();});
	$("#intercambio").change(function(){tab3_validate();});	
	//tab4
	$("#servicio\\.responsable").keyup(function(){tab4_validate();});
	$("#telefono").keyup(function(){
		var no_digito = /\D/g; 
		this.value = this.value.replace(no_digito , ''); 
		tab4_validate();
	});
	$("#correo").keyup(function(){tab4_validate();});
	
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
	 * Creación de método para validar números,
	 * usada por el plugin jquery validator
	 */
	$.validator.addMethod('regexVersion', function (value) {		    	
		return $.isNumeric(value);
	});
	
	/*
	 * Validaciones para el formulario del tab1
	 */
	$("#formSI").validate({
		  errorPlacement: function (error, element) { 
		        error.insertBefore(element);    
		     },
		     rules: {
		    	'sector' : {min: 0 },
		    	'servicio.nombre': {required: true, regexTitle: true},
		    	'servicio.descripcion': {required: true, regexDescription: true},
		    	'area': { required: true, minlength: 1 },
		    	'estado':{min: 0 }
		    },
		    messages: {
		    	'sector': data['errores']['error.servicio.sector'],
		    	'servicio.nombre': {required:data['errores']['error.servicio.nombre'],regexTitle:data['errores']['error.regex.title']},
		    	'servicio.descripcion': {required:data['errores']['error.servicio.descripcion'],regexDescription:data['errores']['error.regex.description']},
		    	'area':data['errores']['error.servicio.area'],
		    	'estado':data['errores']['error.servicio.estado']
		    }
	});

	/*
	 * Validaciones para el formulario del tab2
	 */
	$("#formSI_Tab2").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	    	
	    	'name': {required: true, regexTitle: true},
	    	'file': {required: true, accept: "pdf"}	    	
	    },
	    messages: {	    	
	    	'name': {required:data['errores']['error.servicio.file.name'],regexTitle:data['errores']['error.regex.title']},
	    	'file': {reqired:data['errores']['error.servicio.file'],accept:data['errores']['error.servicio.format']}	    	
	    }
	});
	
	/*
	 * Validaciones para el formulario del tab3
	 */
	$("#formSI_Tab3").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	
	    	 'seguridad' : {min: 0 },
	    	 'arquitectura': { required: true, minlength: 1 },
	    	 'servicio.version':{required: true,regexVersion: true,min: 0 ,max: 1000 },
	    	 'intercambio': {min: 0 },
	    	 'wsdl':{url:true}
	    },
	    messages: {	    	
	    	'seguridad' : data['errores']['error.servicio.seguridad'],
	    	 'arquitectura': data['errores']['error.servicio.arquitectura'],
	    	 'servicio.version':{
	    		 required: data['errores']['error.servicio.version'],
	    		 regexVersion: data['errores']['error.servicio.version.format'],
	    		 min:data['errores']['error.servicio.nombre.version.range'],
	    		 max:data['errores']['error.servicio.version.range']},
	    	 'intercambio': {min: data['errores']['error.servicio.intercambio']},
	    	 'wsdl': data['errores']['error.regex.url']
	    }
	});
		
	/*
	 * Validaciones para el formulario del tab4
	 */
	$("#formSI_Tab4").validate({
	  errorPlacement: function (error, element) { 
	        error.insertBefore(element);    
	     },
	     rules: {	
	    	 'servicio.responsable' : {required: true, regexTitle: true},
	    	 'telefono': { required: true, digits: true },
	    	 'correo':{required: true,email: true }
	    },
	    messages: {	    	
	    	'servicio.responsable' : {
	    		required:data['errores']['error.servicio.responsable'],
	    		regexTitle:data['errores']['error.regex.title']},
	    	 'telefono': {
	    		required:data['errores']['error.servicio.telefono'],
	    		digits:data['errores']['error.servicio.telefono.regex']},	    	 
	    	 'correo': {
	    		required:data['errores']['error.servicio.correo'],
	    		email:data['errores']['error.regex.email']}
	    }
	});
	
	//Valida que todos los campos del tab1 esten completos antes de activar el boton submit.
	function tab1_validate(){
		var iscomplete = true;
		if($("#sector").val()==-1){
			iscomplete =  false;
		}		
		if($("#servicio\\.nombre").val()==""){
			iscomplete =  false;
		}
		if($("#servicio\\.descripcion").val()==""){
			iscomplete =  false;
		}
		if(!input_checkbox_validate($('[name="area"]'))){
			iscomplete =  false;
		}
		if($("#estado").val()==-1){
			iscomplete =  false;
		}		
		if(iscomplete){
			$("#btn_submit").attr('disabled',false);
		}else{
			$("#btn_submit").attr('disabled',true);
		}
	}
	
	//Valida que todos los campos del tab3 esten completos antes de activar el boton submit.
	function tab3_validate(){		
		var iscomplete = true;
		if($("#seguridad").val()==-1){
			iscomplete =  false;
		}		
		if($("#servicio\\.version").val()==""){
			iscomplete =  false;
		}		
		if(!input_checkbox_validate($('[name="arquitectura"]'))){
			iscomplete =  false;
		}
		if($("#intercambio").val()==-1){
			iscomplete =  false;
		}		
		if(iscomplete){
			$("#btn_submit").attr('disabled',false);
		}else{
			$("#btn_submit").attr('disabled',true);
		}
	}
	
	//Valida que todos los campos del tab4 esten completos antes de activar el boton submit.
	function tab4_validate(){		
		var iscomplete = true;			
		if($("#servicio\\.responsable").val()==""){
			iscomplete =  false;
		}
		if($("#telefono").val()==""){
			iscomplete =  false;
		}
		if($("#correo").val()==""){
			iscomplete =  false;
		}
		if(iscomplete){
			$("#btn_submit").attr('disabled',false);
		}else{
			$("#btn_submit").attr('disabled',true);
		}
	}
	
	//Función que valida de que almenos un elemento de un grupo de checkbox este
	//selecionado.
	function input_checkbox_validate(checkbox_s) {
		//Con esta variable se accede a cada elemento del grupo del checkbox
		//al ir recorriendo el grupo 
		var lcheck;
	    for (var i = 0; lcheck = checkbox_s[i]; i++) {
	    	//Se examina el elemento con el método checked y si esta este retorna
	    	//true, se termina la busqueda. 
	        if (lcheck.checked) {
	            return true;
	        }
	    }
	    return false;
	}
	
});
