package ve.gob.cnti.srsi.controlador;

import java.io.File;

public class Archivo {
	private String name;
	private File file;
	private String fileFileName;
	private String fileContentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Override
	public String toString() {
		return "Archivos [name=" + name + ", file=" + file + ", fileFileName="
				+ fileFileName + ", fileContentType=" + fileContentType + "]";
	}

}