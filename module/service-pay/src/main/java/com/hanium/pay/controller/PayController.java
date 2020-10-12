package com.hanium.pay.controller;


import com.hanium.pay.model.Trade;
import com.hanium.pay.openBO.OpenAPI;
import com.hanium.pay.openBO.OpenAPICall;
import com.hanium.pay.payload.request.UserTradeRequest;
import com.hanium.pay.payload.response.APIResponse;
import com.hanium.pay.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class PayController {

    @Autowired
    private OpenAPI open;

    @Autowired
    private OpenAPICall auth;

    @Autowired
    private TradeService tradeService;

    @PostMapping("/trade")
    public ResponseEntity<?> trade(@Valid @RequestBody Trade trade){

        tradeService.tradeHandling(trade);

        return new ResponseEntity(new APIResponse(true, "Success"), HttpStatus.OK);


    }

}
