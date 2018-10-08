package model;

public class DocumentMetaData {
	private long bytes;
	private String fileName;
	private FileType fileType;

	public DocumentMetaData(){}
	
	public DocumentMetaData(String fileName) {
		this.fileName = fileName;
		setFileType(fileName);
	}

	public FileType getFileType(){
		return fileType;
	}
	
	private void setFileType(String fileName){		
		fileType = FileType.getFileType(fileName.substring(fileName.indexOf('.')+1, fileName.length())); 
	}
	
	public long getBytes(){ 
		return bytes; 
	}
	
	public void setBytes(long bytes){ 
		this.bytes = bytes; 
	}
			
	public String getFileName(){ 
		return fileName; 
	}
	
	public static enum FileType {
		PDF("pdf", true),
		XLSX("xlsx", true),
		JPG("jpg", true),
		DOCX("docx", true),
		ZIP("zip", false),
		UNKNOWN(null, false);
		
		private String extension;
		private boolean printable;
		
		private FileType(String extension, boolean printable){
			this.extension = extension;
			this.printable = printable;
		}
				
		public static FileType getFileType(String extension){
			switch(extension){
				case "pdf":
					return PDF;
				case "xlsx":
					return XLSX;
				case "docx":
					return DOCX;
				case "zip":
					return ZIP;
				default:
					return UNKNOWN;
			}
		}
		
		public boolean isPrintable() {return printable;}
		public String getExtension() {return extension;}
	}
}