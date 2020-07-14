package com.hanium.pay.openBO;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpenAPIAuth {


    String API_KEY = "j9mhYBLjb2uQL9PFIhB1FKNUnu3t8AX0Mh7bO08M";


    public String getURL() {
        System.out.println(API_KEY);
        log.info(API_KEY);
        String OPEN_URL = "https://openapi.openbanking.or.kr/oauth/2.0/authorize?&response_type=code&client_id="+API_KEY+"&redirect_uri=login&scope=login"+
                "&auth_type=0&cellphone_cert_yn=Y&authorized_cert_yn=N";
        //String OPEN_URL = "https://openapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=j9mhYBLjb2uQL9PFIhB1FKNUnu3t8AX0Mh7bO08M";


        //https://openapi.openbanking.or.kr/oauth/2.0/authorize?response_type=code&client_id=j9mhYBLjb2uQL9PFIhB1FKNUnu3t8AX0Mh7bO08M&redirect_uri=callback.html&scope=inquiry transfer&client_info=asdfsadf312rf13213123&state=12345678901234567890123456789012&auth_type=0&lang=kor&bg_color=#000000&txt_color=#000000&btn1_color=#000000&btn2color=#000000&account_hold_auth_yn=N

        return OPEN_URL;
    }



}
