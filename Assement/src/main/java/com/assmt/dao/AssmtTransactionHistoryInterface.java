package com.assmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.assmt.entity.AssmtTransactionHistoryEntity;

@Repository
public interface AssmtTransactionHistoryInterface {
	
	void saveRecord(AssmtTransactionHistoryEntity entity);
	
	List<AssmtTransactionHistoryEntity> retrieveListByCriteriaWithPagination(AssmtTransactionHistoryEntity entity,
			List<String> acctNo, int page, int size);

	void descriptionUpdateByIds(String descpt, List<String> ids);

	List<AssmtTransactionHistoryEntity> retrieveListByCriteriaWithoutPagination(AssmtTransactionHistoryEntity entity);
	
	Integer selectCount(AssmtTransactionHistoryEntity entity, List<String>acctNo);
}
