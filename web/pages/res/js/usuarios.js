$(document).ready(function() {
	$('#longitud').keyup(function(e) {
		$('#longitud').validate({
			rules : {
				field : {
					required : true,
					number : true
				}
			}
		});
	});
});
