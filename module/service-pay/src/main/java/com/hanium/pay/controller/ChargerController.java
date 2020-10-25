package com.hanium.pay.controller;


import com.hanium.pay.payload.request.UserChargeRequest;
import com.hanium.pay.payload.response.APIResponse;
import com.hanium.pay.service.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargerController {

    @Autowired
    ChargerService chargerService;

    @PatchMapping(value = "/charge")
    public ResponseEntity<?> userCharge(UserChargeRequest userChargeRequest){
        chargerService.charger(userChargeRequest);
        return new ResponseEntity<>(new APIResponse(true, "success"), HttpStatus.OK);
    }


}
