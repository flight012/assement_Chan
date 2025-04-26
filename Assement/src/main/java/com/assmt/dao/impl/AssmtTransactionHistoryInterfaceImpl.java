package com.assmt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assmt.dao.AssmtTransactionHistoryInterface;
import com.assmt.entity.AssmtTransactionHistoryEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Transactional
@Repository
public class AssmtTransactionHistoryInterfaceImpl implements AssmtTransactionHistoryInterface{
   
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
    public void saveRecord(AssmtTransactionHistoryEntity entity) {
        entityManager.persist(entity);
    }
    
	public List<AssmtTransactionHistoryEntity> retrieveListByCriteriaWithPagination(AssmtTransactionHistoryEntity entity,
			List<String> acctNo, int page, int size) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder singleQuery = new StringBuilder("SELECT a FROM AssmtTransactionHistoryEntity a ");
		StringBuilder paramsString = new StringBuilder("");
		StringBuilder orderString = new StringBuilder(" order by a.id desc");

		this.constructConditionFilterParameter(paramsString, entity, acctNo, params);

		String sql = singleQuery.append(paramsString).append(orderString).toString();
		System.out.println("Display Query[retrieveListByCriteriaWithPagination] : "+sql);
		Query query = createQuery(sql, page*size, size, AssmtTransactionHistoryEntity.class, params.toArray());
		
		List<AssmtTransactionHistoryEntity> list = (List<AssmtTransactionHistoryEntity>) query.getResultList();
		return list;
	}
	
	@Modifying
	@Transactional
	public void descriptionUpdateByIds(String descpt, List<String> ids) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE assmt.tbl_assmt_trx_his SET description = :descpt WHERE ID in (:ids)");

		Query query = entityManager.createNativeQuery(buffer.toString());
		query.setParameter("descpt", descpt);
		query.setParameter("ids", ids);
		query.executeUpdate();
	}
	
	public Integer selectCount(AssmtTransactionHistoryEntity entity, List<String>acctNo) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder singleQuery = new StringBuilder("");
		StringBuilder paramsString = new StringBuilder("");
		StringBuilder orderString = new StringBuilder("");

		singleQuery.append("SELECT count( DISTINCT a) FROM AssmtTransactionHistoryEntity a");
		this.constructConditionFilterParameter(paramsString, entity, acctNo, params);
		orderString.append(" order by a.id desc");

		String sql = singleQuery.append(paramsString).append(orderString).toString();
		System.out.println("Display Query[selectCount] : "+sql);
		Query query = createQuery(sql, params.toArray());
		Number var4 = (Number) query.getSingleResult();
		int count = var4 != null ? var4.intValue() : 0;

		return count;
	}
	
	public List<AssmtTransactionHistoryEntity> retrieveListByCriteriaWithoutPagination(AssmtTransactionHistoryEntity entity) {

		List<Object> params = new ArrayList<Object>();
		StringBuilder singleQuery = new StringBuilder("");
		StringBuilder paramsString = new StringBuilder("");
		StringBuilder orderString = new StringBuilder("");

		singleQuery.append("SELECT a FROM AssmtTransactionHistoryEntity a");
		this.constructConditionFilterParameter(paramsString, entity, null, params);
		orderString.append(" order by a.id desc");

		String sql = singleQuery.append(paramsString).append(orderString).toString();
		System.out.println("Display Query[retrieveListByCriteriaWithoutPagination] : "+sql);
		Query query = createQuery(sql, null, null, AssmtTransactionHistoryEntity.class, params.toArray());
		List<AssmtTransactionHistoryEntity> list = (List<AssmtTransactionHistoryEntity>) query.getResultList();
		return list;
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
	private Query createQuery(String queryString, Object... values) {
		Query query = this.entityManager.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; ++i) {
				System.out.println("index = [" + (i + 1) + "] values = [" + values[i] + "]");
				query.setParameter(i + 1, values[i]);
			}
		}
		return query;
	}
}
