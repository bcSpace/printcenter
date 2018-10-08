package model;

public class PrintDocument {
	
	private DocumentMetaData document;
	private long priority;

	public PrintDocument() {}
	public PrintDocument(DocumentMetaData meta, long prio) {
		this.document = meta;
		this.priority = prio;
	}
	
	public long getPriority() {
		return priority;
	}
	public void setPriority(long priority) {
		this.priority = priority;
	}
	public DocumentMetaData getDocument() {
		return document;
	}
	public void setDocument(DocumentMetaData document) {
		this.document = document;
	}	
}
