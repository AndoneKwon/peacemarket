package com.hanium.pay.openBO;

import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public interface OpenAPI {

    public List<String> getAccessAndRefreshToken(String authorize_code) throws Exception;
    public List<String> refreshAccessToken(String accessToken) throws Exception;
    public String getAuthorizationCode() throws Exception;

}
