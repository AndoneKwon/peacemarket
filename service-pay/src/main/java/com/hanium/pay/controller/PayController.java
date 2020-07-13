package com.hanium.pay.controller;


import com.hanium.pay.openBO.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j
public class PayController {

    @Autowired
    private OpenAPI open;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String Test(){
        String auth_Code = open.getAuthCode();
        List<String> tokens = open.getAccessToken(auth_Code);

        return tokens.get(0);
    }

    @RequestMapping(value ="/auth", method=RequestMethod.GET)
    public void OpenAuth(HttpServletResponse response){
        String auth_Code = open.getAuthCode();
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public void OpenLogin(@RequestParam("code") String code, HttpServletResponse response ) {

        List<String> tokens = open.getAccessToken(code);
        log.info("code  : " + code +"\n"+ "Aceess : " + tokens.get(0)+"\n" + "Refresh : " + tokens.get(1));

        String acces_token = new String(tokens.get(0));
        String refresh_token = new String(tokens.get(1));



    }

}
