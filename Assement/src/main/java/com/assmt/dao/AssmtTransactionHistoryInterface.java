package com.assmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assmt.entity.AssmtTransactionHistoryEntity;

public interface AssmtTransactionHistoryInterface extends JpaRepository<AssmtTransactionHistoryEntity, String>, AssmtTransactionHistoryImplInterface{

	List<AssmtTransactionHistoryEntity> findListByCriteriaWithPagination(AssmtTransactionHistoryEntity entity,
			Object object, Integer startIndex, Integer maxPerPage);

	List<AssmtTransactionHistoryEntity> findListByCriteriaWithoutPagination(AssmtTransactionHistoryEntity entity);

	void updateDescriptionById(Object object, List<String> ids);

}
