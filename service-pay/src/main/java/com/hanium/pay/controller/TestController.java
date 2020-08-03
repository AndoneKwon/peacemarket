package com.hanium.pay.controller;


import com.hanium.pay.openBO.OpenAPI;
import com.hanium.pay.openBO.OpenAPIImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @Autowired
    OpenAPIImpl openAPI;

    //Fail
    @GetMapping(value = "/test1")
    public RedirectView test1() throws Exception{
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(openAPI.getAuthorizationCode());
        return redirectView;

    }

    @GetMapping(value = "/login")
    public void test2(@RequestParam("code") String code, HttpServletResponse response) throws Exception{

    }

}
