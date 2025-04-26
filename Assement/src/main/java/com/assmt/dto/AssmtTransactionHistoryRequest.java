package com.assmt.dto;

import java.util.List;

public class AssmtTransactionHistoryRequest extends AssmtCommonModel{
	private AssmtTransactionHistoryModel model;
	private List<AssmtTransactionHistoryModel> models;
	
	public AssmtTransactionHistoryModel getModel() {
		return model;
	}

	public void setModel(AssmtTransactionHistoryModel model) {
		this.model = model;
	}

	public List<AssmtTransactionHistoryModel> getModels() {
		return models;
	}

	public void setModels(List<AssmtTransactionHistoryModel> models) {
		this.models = models;
	} 
	
}
