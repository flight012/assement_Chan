package com.assmt.dto;

public class AssmtCommonModel {
	private Integer startIndex;
	private Integer maxPerPage;
	private Integer totalRecords;
	private String statusCode;
	private String statusMessage;

	public Integer getStartIndex() {
		return  startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxPerPage() {
		return maxPerPage;
	}

	public void setMaxPerPage(Integer maxPerPage) {
		this.maxPerPage = maxPerPage;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
