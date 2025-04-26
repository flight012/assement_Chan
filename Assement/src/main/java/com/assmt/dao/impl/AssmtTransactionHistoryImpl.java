package com.assmt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assmt.dao.AssmtTransactionHistoryImplInterface;
import com.assmt.dao.AssmtTransactionHistoryInterface;
import com.assmt.entity.AssmtTransactionHistoryEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Transactional
public class AssmtTransactionHistoryImpl implements AssmtTransactionHistoryImplInterface{
   
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<AssmtTransactionHistoryEntity> findListByCriteria(AssmtTransactionHistoryEntity entity,
			List<String> acctNo, int page, int size) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder singleQuery = new StringBuilder("");
		StringBuilder paramsString = new StringBuilder("");
		StringBuilder orderString = new StringBuilder("");

		singleQuery.append("SELECT a FROM assmtTransactionHistoryEntity a");
		this.constructConditionFilterParameter(singleQuery, entity, acctNo, params);
		orderString.append(" order by a.id desc");

		String sql = singleQuery.append(paramsString).append(orderString).toString();
		Query query = createQuery(sql, page, size, AssmtTransactionHistoryEntity.class, params);
		return query.getResultList();
	}
	 
	public void updateDescriptionById(String descpt, List<String> ids) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE assmt.tbl_assmt_trx_his SET description = :descpt WHERE ID in (:dis)");

		Query query = entityManager.createNativeQuery(buffer.toString());
		query.setParameter("descpt", descpt);
		query.setParameter("ids", ids);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<AssmtTransactionHistoryEntity> findListByCriteriaWithoutPagination(
			AssmtTransactionHistoryEntity entity) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder singleQuery = new StringBuilder("");
		StringBuilder paramsString = new StringBuilder("");
		StringBuilder orderString = new StringBuilder("");

		singleQuery.append("SELECT COUNT(a) FROM assmtTransactionHistoryEntity a");
		this.constructConditionFilterParameter(singleQuery, entity, null, params);
		orderString.append(" order by a.id desc");

		String sql = singleQuery.append(paramsString).append(orderString).toString();
		Query query = createQuery(sql, null, null, AssmtTransactionHistoryEntity.class, params);

		return query.getResultList();
	}
	
	private void constructConditionFilterParameter(StringBuilder paramsString, AssmtTransactionHistoryEntity entity, List<String> acctNo, List<Object> params) {
		int index = 1;
        
        if (null != entity.getCustomerNo())
        {
        	appendAndOr(paramsString);
            paramsString.append(" a.customerNo =?" + index);
            params.add(entity.getCustomerNo());
            index++;
        }
        if (null != entity.getAcctNo())
        {
        	appendAndOr(paramsString);
            paramsString.append(" a.acctNo =?" + index);
            params.add(entity.getAcctNo());
            index++;
        }
        
        if (null != entity.getDescription())
        {
        	appendAndOr(paramsString);
            paramsString.append(" a.description LIKE ?" + index);
            params.add(entity.getDescription().toUpperCase().concat("%"));
            index++;
        }
        if (null != acctNo && acctNo.size()>0)
        {
        	appendAndOr(paramsString);
            paramsString.append(" a.acctNo IN (?").append(index).append(")");
            params.add(acctNo);
            index++;
        }
	}
	
	private void appendAndOr(StringBuilder paramsString) {
		if (paramsString.length() > 0) {
			paramsString.append(" AND ");
		} else {
			paramsString.append(" WHERE ");
		}
	}
	
	private Query createQuery(String queryString, Integer firstResult, Integer maxResults, Class<?> c, Object... values) {
		Query query = this.entityManager.createQuery(queryString, c);
		if (values != null) {
			for (int i = 0; i < values.length; ++i) {
				System.out.println("index = [" + (i + 1) + "] values = [" + values[i] + "]");
				query.setParameter(i + 1, values[i]);
			}
		}
		if (firstResult != null && maxResults != null) {
			System.out.println("First Index = [" + firstResult + "], Max Results = [" + maxResults + "]");
			query.setFirstResult(firstResult.intValue());
			query.setMaxResults(maxResults.intValue());
		}
		return query;
	}

}
