package com.assmt.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assmt.dao.AssmtTransactionHistoryInterface;
import com.assmt.dto.AssmtTransactionHistoryModel;
import com.assmt.dto.AssmtTransactionHistoryRequest;
import com.assmt.dto.AssmtTransactionHistoryResponse;
import com.assmt.entity.AssmtTransactionHistoryEntity;

@Service
public class AssmtTransactionHistoryService {
	
	@Autowired
	private AssmtTransactionHistoryInterface assmtTransactionHistoryInterface;
	
	public void processFile(String path) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), "*.txt")) {
            for (Path filePath : stream) {
                System.out.println("Processing file: " + filePath.getFileName());
                processSingleFile(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public AssmtTransactionHistoryResponse inquiry(AssmtTransactionHistoryRequest request) {
		
		AssmtTransactionHistoryEntity entity = new AssmtTransactionHistoryEntity(request.getModel());
		
		List<AssmtTransactionHistoryEntity> entities = assmtTransactionHistoryInterface.findListByCriteriaWithPagination(entity, null, request.getStartIndex(), request.getMaxPerPage());
		
		AssmtTransactionHistoryResponse response = new AssmtTransactionHistoryResponse();
		
		List<AssmtTransactionHistoryModel> dtoList = entities.stream()
			    .map(AssmtTransactionHistoryModel::new) 
			    .collect(Collectors.toList());
		
		response.setModels(dtoList);
		response.setStartIndex(request.getStartIndex());
		response.setMaxPerPage(request.getMaxPerPage());
		response.setTotalRecords(entities.size());
		
		return response;
	}
	
	public AssmtTransactionHistoryResponse updateDescription(AssmtTransactionHistoryRequest request) {

		AssmtTransactionHistoryResponse respone = new AssmtTransactionHistoryResponse();
		AssmtTransactionHistoryEntity entity = new AssmtTransactionHistoryEntity();
		entity.setAcctNo(null != request.getModel().getAcctNo() ? request.getModel().getAcctNo() : null);
		entity.setCustomerNo(null != request.getModel().getCustomerNo() ? request.getModel().getCustomerNo() : null);
		
		if (null == entity.getAcctNo() || null == entity.getCustomerNo()) {
			respone.setStatusCode("FAILED");
			respone.setStatusMessage("FAILED TO UPDATE!");
			return respone;
		}
		
		List<AssmtTransactionHistoryEntity> entities = assmtTransactionHistoryInterface
				.findListByCriteriaWithoutPagination(entity);

		if (entities.size() > 0) {
			
			List<String> ids = entities.stream().map(AssmtTransactionHistoryEntity::getId).collect(Collectors.toList());
			
			assmtTransactionHistoryInterface.updateDescriptionById(null, ids);
			
			respone.setStatusCode("SUCCESS");
			respone.setStatusMessage("UPDATE Complete!");
			
		} else {
			respone.setStatusCode("FAILED");
			respone.setStatusMessage("No Records Found. FAILED TO UPDATE!");
		}

		return respone;
	}
	
    private void processSingleFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                	
                	String id = UUID.randomUUID().toString().toUpperCase();
                	String acctNo = parts[0];
                	BigDecimal trxAmt  = null!=parts[1]? new BigDecimal(parts[1]): new BigDecimal(0);
                	String desc = parts[2];
                	String trxDate= parts[3];
                	String trxTime= parts[4];
                	String cust = parts[5];
                			
                	AssmtTransactionHistoryModel model = new AssmtTransactionHistoryModel(id,acctNo,trxAmt,desc,trxDate,trxTime,cust);
                	AssmtTransactionHistoryEntity entity = new AssmtTransactionHistoryEntity(model);
                	assmtTransactionHistoryInterface.save(entity);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
