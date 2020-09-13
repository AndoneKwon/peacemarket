package com.hanium.pay.openBO;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public interface OpenAPI {

    @Value("{openAPI.api}")
    String API_KEY;
    String SECRET_KEY = "3fi41ArBAHryIE0BuCkm1xJCojVsmFRHMr3DHwiB";
    String CALL_BACK_URL = "http://localhost:8080/login";

    String LOGIN_POLICY = "inquiry";
    String STATE = "12345678901234567890123456789012";

    String BASE_URL = "https://testapi.openbanking.or.kr";

    //Auth
    String GET_OAUTH = "/oauth/2.0/authorize?";
    String GET_TOKEN = "oauth/2.0/token?";


    public List<String> getAccessToken(String authorize_code) throws Exception;
    public List<String> refreshAccessToken(String accessToken) throws Exception;
    public String getAuthorizationCode() throws Exception;

}
