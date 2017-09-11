package org.attachments.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Attachment {
	
	@Id
	private String id;
	
	private String name;
	
	private Date uploadDate;
	
	private byte[] file;
	
	private double size;
	
	private String meaningfulName;
	
	
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getMeaningfulName() {
		return meaningfulName;
	}

	public void setMeaningfulName(String meaningfulName) {
		this.meaningfulName = meaningfulName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

}
