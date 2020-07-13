package com.hanium.pay.openBO;

import java.util.*;

public interface OpenAPI {

    public String getAuthCode();
    public List<String> getAccessToken(String authorize_code);

}
