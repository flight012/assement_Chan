package com.assmt.service;

//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.assmt.dao.AssmtTransactionHistoryInterface;
//import com.assmt.dto.AssmtTransactionHistoryModel;
//import com.assmt.dto.AssmtTransactionHistoryRequest;
//import com.assmt.dto.AssmtTransactionHistoryResponse;
//
//
//@Transactional
//class AssmtTransactionHistoryServiceImplTest {
//
//	@Autowired
//	private AssmtTransactionHistoryService service;
//	
//	@Autowired
//	private AssmtTransactionHistoryInterface assmtTransactionHistoryInterface;
//	
//    @Test
//    void testRetrieveListByCriteriaWithPagination() {
//    	AssmtTransactionHistoryRequest request = new AssmtTransactionHistoryRequest();
//    	AssmtTransactionHistoryModel model = new AssmtTransactionHistoryModel();
//    	model.setCustomerNo("222");
//    	request.setModel(model);
//    	request.setStartIndex(0);
//    	request.setMaxPerPage(0);
//        when(service.inquiry(request))
//        .thenReturn(new AssmtTransactionHistoryResponse());
//        // Assert
//        assertFalse(response.getModels().isEmpty());
//        assertEquals(2, response.getModels().size());
//    }
//}
