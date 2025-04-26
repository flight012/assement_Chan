package com.assmt.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
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
	private static final AtomicInteger counter = new AtomicInteger(1); 
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
		if(null== request.getStartIndex()) {
			request.setStartIndex(0);
		}
		if(null== request.getMaxPerPage()) {
			request.setMaxPerPage(5);
		}
		Integer count = assmtTransactionHistoryInterface.selectCount(entity, request.getModel().getAcctNos());
		
		List<AssmtTransactionHistoryEntity> entities = assmtTransactionHistoryInterface.retrieveListByCriteriaWithPagination(entity, request.getModel().getAcctNos(), request.getStartIndex(), request.getMaxPerPage());
		
		AssmtTransactionHistoryResponse response = new AssmtTransactionHistoryResponse();
		
		List<AssmtTransactionHistoryModel> dtoList = entities.stream()
			    .map(AssmtTransactionHistoryModel::new) 
			    .collect(Collectors.toList());
		
		response.setModels(dtoList);
		response.setStartIndex(request.getStartIndex());
		response.setMaxPerPage(request.getMaxPerPage());
		response.setTotalRecords(count);
		
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
				.retrieveListByCriteriaWithoutPagination(entity);

		if (entities.size() > 0) {
			entity.setDescription(null != request.getModel().getDescription() ? request.getModel().getDescription() : null);
			List<String> ids = entities.stream().map(AssmtTransactionHistoryEntity::getId).collect(Collectors.toList());
			
			assmtTransactionHistoryInterface.descriptionUpdateByIds(entity.getDescription(), ids);
			
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
        	 List<String> allLines = lines.collect(Collectors.toList());
        	    if (allLines.size() <= 1) {
        	        System.out.println("Not enough data to process.");
        	    } else {
        	    	allLines.subList(1, allLines.size()).forEach(line -> {
                        System.out.println("Get Line from file: " + line);
                        String[] parts = line.split("\\|");
                    	String id = generateCustomId().toString().toUpperCase();
                    	String acctNo = parts[0];
                    	BigDecimal trxAmt  = null!=parts[1]? new BigDecimal(parts[1]): new BigDecimal(0);
                    	String desc = parts[2];
                    	String trxDate= parts[3];
                    	String trxTime= parts[4];
                    	String cust = parts[5];
                    			
                    	AssmtTransactionHistoryModel model = new AssmtTransactionHistoryModel(id,acctNo,trxAmt,desc,trxDate,trxTime,cust);
                    	AssmtTransactionHistoryEntity entity = new AssmtTransactionHistoryEntity(model);
                    	assmtTransactionHistoryInterface.saveRecord(entity);
                    });
        	    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String generateCustomId() {
        // 1. Get current date + time in YYYYMMDDhhmmss
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        
        // 2. Auto increment number
        int runningNumber = counter.getAndIncrement();

        // 3. If running number > 1_000_000_000, reset to 1
        if (runningNumber > 1_000_000_000) {
            counter.set(1);
            runningNumber = counter.getAndIncrement();
        }
        // 4. Format the running number with leading zeros (make it 10 digits)
        String numberPart = String.format("%010d", runningNumber);
        
        // 5. Combine
        return timestamp + numberPart;
    }
}
