package com.assmt.entity;

import java.math.BigDecimal;

import com.assmt.dto.AssmtTransactionHistoryModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_assmt_trx_his", schema = "assmt")
public class AssmtTransactionHistoryEntity {
	public String id;
	public String acctNo;
	public BigDecimal trxAmt;
	public String description;
	public String trxDate;
	public String trxTime;
	public String customerNo;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "acct_no")
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	@Column(name = "trx_amt")
	public BigDecimal getTrxAmt() {
		return trxAmt;
	}
	public void setTrxAmt(BigDecimal trxAmt) {
		this.trxAmt = trxAmt;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "trx_date")
	public String getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	
	@Column(name = "trx_time")
	public String getTrxTime() {
		return trxTime;
	}
	public void setTrxTime(String trxTime) {
		this.trxTime = trxTime;
	}
	
	@Column(name = "cus_no")
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public AssmtTransactionHistoryEntity(AssmtTransactionHistoryModel model) {
		super();
		this.id = model.getId();
		this.acctNo = model.getAcctNo();
		this.trxAmt = model.getTrxAmt();
		this.description = model.getDescription();
		this.trxDate = model.getTrxDate();
		this.trxTime = model.getTrxTime();
		this.customerNo = model.getCustomerNo();
	}
	
	public AssmtTransactionHistoryEntity() {
	}

}
