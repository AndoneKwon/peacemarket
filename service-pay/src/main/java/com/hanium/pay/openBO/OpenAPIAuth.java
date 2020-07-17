package com.hanium.pay.openBO;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenAPIAuth {


    String API_KEY = "j9mhYBLjb2uQL9PFIhB1FKNUnu3t8AX0Mh7bO08M";


    public String getURL() {
        log.info(API_KEY);
        String OPEN_URL = "https://testapi.openbanking.or.kr/oauth/2.0/authorize?" +
                "response_type=code" +
                "&client_id="+API_KEY+
                "&redirect_uri=http://localhost:8080/login" +
                "&scope=inquiry"+
                "&state=12345678901234567890123456789012" +
                "&auth_type=0" +
                "&cellphone_cert_yn=Y" +
                "&authorized_cert_yn=N";

        return OPEN_URL;
    }



}
