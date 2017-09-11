package org.attachments.vos;

import java.util.Date;

public class AttachmentVO {
	
	
	private String name;
	
	private Date uploadDate;
	
	private double size;
	
	private String meaningfulName;

	
	public AttachmentVO(String name, Date uploadDate, double size, String meaningfulName) {
		this.name = name;
		this.uploadDate = uploadDate;
		this.size = size;
		this.meaningfulName = meaningfulName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getMeaningfulName() {
		return meaningfulName;
	}

	public void setMeaningfulName(String meaningfulName) {
		this.meaningfulName = meaningfulName;
	}

}
