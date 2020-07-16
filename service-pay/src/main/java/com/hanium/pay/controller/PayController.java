package com.hanium.pay.controller;


import com.hanium.pay.openBO.OpenAPI;
import com.hanium.pay.openBO.OpenAPIAuth;
import com.hanium.pay.payload.UserTrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class PayController {

    @Autowired
    private OpenAPI open;

    @Autowired
    private OpenAPIAuth auth;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RedirectView Test(){

        RedirectView redirectView = new RedirectView();
        System.out.println("KEY  " + auth.getURL());
        redirectView.setUrl(auth.getURL());
        return redirectView;


    }

//    @RequestMapping(value ="/auth", method=RequestMethod.GET)
//    public void OpenAuth(HttpServletResponse response){
//        String auth_Code = open.getAuthCode();
//    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String OpenLogin(@RequestParam("code") String code, HttpServletResponse response ) {

        List<String> tokens = open.getAccessToken(code);
        log.info("code  : " + code +"\n"+ "Aceess : " + tokens.get(0)+"\n" + "Refresh : " + tokens.get(1));

        String acces_token = new String(tokens.get(0));
        String refresh_token = new String(tokens.get(1));

        return acces_token;

    }

    @PostMapping("/trade")
    public ResponseEntity<?> trade(@Valid @RequestBody UserTrade userTrade){


    }

}
