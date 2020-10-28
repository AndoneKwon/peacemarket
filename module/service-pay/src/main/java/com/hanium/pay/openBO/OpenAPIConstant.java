package com.hanium.pay.openBO;

public interface OpenAPIConstant {
    String BASE_URL = "https://testapi.openbanking.or.kr";
    String GET_OAUTH = "/oauth/2.0/authorize?";
    String GET_TOKEN = "oauth/2.0/token?";
    String CALL_BACK_URL = "http://localhost:8080/login";
    String LOGIN_POLICY = "inquiry";
}
