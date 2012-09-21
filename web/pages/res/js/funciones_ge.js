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
    
    $('.m_a').lksMenu();
    
    $(".m_a ul li ul").attr("style", "display: none");
    $(".m_a ul li ul li ul").attr("style", "display: none");
    
    
    /*
	 * LLenando variable que lee el número de tool tip
	 */
    var n = $(".m_tip");
	
	/*
	 * Creación del número de capas necesarias para la visualización de los sexys tool tips
	 */
	for(var i=1;i<=n.length;i++){
		$("#t"+i).tooltip (""+$('div.t'+i).html(), {
			width: 120,
	          style: 'alert',             
	          hook: 1
	    }); 
	}
    
    
    
    mensajeError();
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

(function($){
    // fn es un shortcut al prototipo (prototypo) de la libreria jquery
    // declarando un metodo dentro de esta librería la extendemos para ser usada
    // con cualquier selector
    $.fn.lksMenu=function(){
        // para mantener la posibilidad de concatenar metodos es que retornamos la función en
        // lugar de solo ejecutar algo y ya.
        // en este caso usamos un each, porque el selector sobre el que aplicamos la funcion
        // podria contener mas de un elemento , esto es , aplicaria $('.menu').menu() lo cual
        // ejecutaria el codigo para todos los elementos que tengan la clase menu, como puede
        // haber mas de uno es que ejecutamos el each para que corra sobre todos ellos
        return this.each(function(){
            // obtenemos el elemento que se esta analizando en esta vuelta del each
            var menu= $(this);
            // localizamos los links principales y le asignamos un evento click
            menu.find('ul li > a').bind('click',function(event){
                // identificamos el elemento sobre el que se hizo click
                var currentlink=$(event.currentTarget);
                // los ul que tengan la clase active seran los que estan abiertos
                // si el link sobre que presionamos ya estaba abierto , es decir tenia
                // la clase active, lo cerramos y nada mas
                if (currentlink.parent().find('ul.active').size()==1)
                {                	
                    //cerramos el link porque apretamos sobre el mismo abierto
                    currentlink.parent().find('ul.active').slideUp('medium',function(){
                        // le quitamos la clase
                        currentlink.parent().find('ul.active').removeClass('active');
                    });
                }
                // si ningun link estaba apretado
                else if (menu.find('ul li ul.active').size()==0)
                {
                    //no hace falta cerrar ninguno y solo abrimos el elemento al
                    // cual se le hizo click
                    show(currentlink);
                }
                else
                {
                    // si ya habia un item abierto , simplemente lo localizamos
                    // con find, y lo cerramos con slideup,
                    menu.find('ul li ul.active').slideUp('medium',function(){
                        // una vez que cerramos el que estaba abierto
                        // le quitamos la clase active
                        menu.find('ul li ul').removeClass('active');
                        // abrimos uno nuevo que corresponde al que se clickeo
                        show(currentlink);
                    });
                }
            });
            
            
            menu.find('ul li > a').bind('click',function(event){
                
                var currentlink=$(event.currentTarget);
                
                if (currentlink.parent().find('span.active').size()==1)
                {                    
                    currentlink.parent().find('span.active').html("+");
                    currentlink.parent().find('span.active').removeClass('active');                    
                }
                else if (menu.find('span.active').size()==0)
                {                                        
                    show2(currentlink);
                }
                else
                {
                        menu.find('span').html("+");
                        menu.find('span').removeClass('active');
                        show2(currentlink);
                }
            });
            
            

            // esta funcion es de soporte
            // todo lo que hace es abrir el elemento y asignarle la clase active
            function show(currentlink){
                currentlink.parent().find('ul').addClass('active');
                currentlink.parent().find('ul').slideDown('medium');
            }            
            
            function show2(currentlink){
                currentlink.parent().find('span').addClass('active');  
                currentlink.parent().find('span.active').html("-");              
            }
        });
    }
    // esto es lo que deciamos al principio , ejecutamos la funcion por eso los
    // parentesis y le pasamos por parametro la libreria jQuery.
})(jQuery);

function mensajeError(){
	$('.error_msj').attr("style", "visibility: hidden; margin-top: -100px;");	
	if($('.errorMessage').length>0){				
		Sexy.error("<br><span class=\"errorMessage\">"
					+$('.errorMessage').html()
					+"</span>");		
	}
	if($('.ok_pass').length>0){		
		Sexy.info("<br>"+$('.ok_pass').html());		
	}
}

/*
 * Ejecución de los sexys tool tips, en el que se lee la capa oculta en el html
 * donde se encuentrán los items incompletos del servicio de información.
 * 
 * elem = objeto html img para uso del tool tip
 * 
 * $('div.'+elem.name).html() = Lee una capa oculta con los detalles del servicio incompleto
 * 
 * $("#"+elem.name).tooltip = es el identificado de la imagen la cual mostrarará los detalles 
 * del servicio incompleto.
 */
function tip(elem){ 
    $("#"+elem.name).tooltip (""+$('.'+elem.name).html(), {
          width: 120,
          style: 'alert',             
          hook: 1
    });   
}