package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    private CommonService commonService;

    public ResourcesController(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllTrade() {

        commonService.deleteAllTrades();

        return new ResponseEntity<>(HttpStatus.OK);
    }


    
}
