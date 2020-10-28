package com.hanium.pay.openBO;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.hanium.pay.openBO.OpenAPIConstant.*;

@Component
@Slf4j
public class OpenAPICall {

    @Autowired
    RestTemplate restTemplate;



    public String call(String baseURL, String url, String body, HttpMethod httpMethod, String token) throws  Exception{

        //set Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("charset", "UTF-8");

        if(url.equals(GET_TOKEN) || url.equals(GET_OAUTH));
        else headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(baseURL+url+body, httpMethod, entity, String.class);

        String result = response.getBody();

        return result;
    }



}
