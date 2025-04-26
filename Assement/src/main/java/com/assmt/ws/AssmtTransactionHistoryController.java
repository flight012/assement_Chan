package com.assmt.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assmt.dto.AssmtTransactionHistoryRequest;
import com.assmt.dto.AssmtTransactionHistoryResponse;
import com.assmt.service.AssmtTransactionHistoryService;

@RestController
@RequestMapping("/api/assmt")
public class AssmtTransactionHistoryController {
	
	@Autowired
	private AssmtTransactionHistoryService service;
	
    @GetMapping("/runBatch")
    public String runBatch() {
    	service.processFile("src/main/resources/");
        return "Batch job completed!";
    }
    @RequestMapping(value = "/inquiry", 
    		method = { RequestMethod.POST }, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE}, 
    		produces = { MediaType.APPLICATION_JSON_VALUE})
    public AssmtTransactionHistoryResponse inquiry(@RequestBody AssmtTransactionHistoryRequest request) {
    	AssmtTransactionHistoryResponse response = service.inquiry(request);
        return response;
    }
    
    @RequestMapping(value = "/update", 
    		method = { RequestMethod.POST }, 
    		consumes = { MediaType.APPLICATION_JSON_VALUE}, 
    		produces = { MediaType.APPLICATION_JSON_VALUE})
    public AssmtTransactionHistoryResponse updateDescription(@RequestBody AssmtTransactionHistoryRequest request) {
    	AssmtTransactionHistoryResponse response = service.updateDescription(request);
        return response;
    }
}
