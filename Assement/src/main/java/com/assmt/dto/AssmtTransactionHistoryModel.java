package com.assmt.dto;

import java.math.BigDecimal;
import java.util.List;

import com.assmt.entity.AssmtTransactionHistoryEntity;

public class AssmtTransactionHistoryModel{
	
	public String id;
	public String acctNo;
	public BigDecimal trxAmt;
	public String description;
	public String trxDate;
	public String trxTime;
	public String customerNo;
	public List<String> acctNos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public BigDecimal getTrxAmt() {
		return trxAmt;
	}
	public void setTrxAmt(BigDecimal trxAmt) {
		this.trxAmt = trxAmt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	public String getTrxTime() {
		return trxTime;
	}
	public void setTrxTime(String trxTime) {
		this.trxTime = trxTime;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public List<String> getAcctNos() {
		return acctNos;
	}
	public void setAcctNos(List<String> acctNos) {
		this.acctNos = acctNos;
	}
	
	public AssmtTransactionHistoryModel(AssmtTransactionHistoryEntity entity) {
		super();
		this.id = entity.getId();
		this.acctNo = entity.getAcctNo();
		this.trxAmt = entity.getTrxAmt();
		this.description = entity.getDescription();
		this.trxDate = entity.getTrxDate();
		this.trxTime = entity.getTrxTime();
		this.customerNo = entity.getCustomerNo();
	}
	public AssmtTransactionHistoryModel() {
	}
	public AssmtTransactionHistoryModel(String id, String acctNo, BigDecimal trxAmt, String description, String trxDate,
			String trxTime, String customerNo) {
		super();
		this.id = id;
		this.acctNo = acctNo;
		this.trxAmt = trxAmt;
		this.description = description;
		this.trxDate = trxDate;
		this.trxTime = trxTime;
		this.customerNo = customerNo;
		}

	
	
}
