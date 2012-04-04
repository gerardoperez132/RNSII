var File = function() {
	var nameLabel;
	var fileLabel;
	function createRow() {
		// return
		// '<s:fielderror><s:param>name</s:param></s:fielderror><s:textfield
		// name="name" labelposition="top" /><h5 class="formulario"><s:text
		// name="documento.file"></s:text></h5><s:fielderror><s:param>file</s:param></s:fielderror><s:file
		// name="file" />';
		// return '<input type="submit" value="Submit">';
		alert("Index => " + i);
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
