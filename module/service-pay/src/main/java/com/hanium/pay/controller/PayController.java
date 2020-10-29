package com.hanium.pay.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanium.pay.model.Trade;
import com.hanium.pay.model.TradeRequestDto;
import com.hanium.pay.payload.response.ApiResponse;
import com.hanium.pay.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class PayController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/trade")
    public ResponseEntity<?> trade(@RequestHeader(value = "authorization") String token,@Valid @RequestBody TradeRequestDto trade){
        ObjectMapper mapper = new ObjectMapper();
        Algorithm a = Algorithm.HMAC256("nodebird");
        String jwt = token;
        JWTVerifier verifier = JWT.require(a)
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwt);
        Long consmumerId = decodedJWT.getClaim("user_id").asLong();

        boolean success = tradeService.tradeHandling(trade,consmumerId);
        if(success)
            return new ResponseEntity(new ApiResponse(true, "Success"), HttpStatus.OK);
        else
            return new ResponseEntity(new ApiResponse(false, "finished trade"), HttpStatus.OK);

    }

}
