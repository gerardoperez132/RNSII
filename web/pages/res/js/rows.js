var File = function() {
	var nameLabel;
	var fileLabel;
	function createRow() {
		form = '<h5 class="formulario">'
				+ nameLabel
				+ '</h5>'
				+ '<input type="text" name="archivos.name" id="name">'
				+ '<h5 class="formulario">'
				+ fileLabel
				+ '</h5><input type="file" name="archivos.file" value="" id="file"><hr>';
		return form;
	}
	return {
		prepare : function(name, file) {
			nameLabel = name;
			fileLabel = file;
		},
		addRow : function() {
			$("#addRow").before(createRow());
			return false;
		}
	};
}();
