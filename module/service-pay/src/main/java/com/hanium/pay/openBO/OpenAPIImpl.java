package com.hanium.pay.openBO;


import java.util.ArrayList;
import java.util.List;


import com.hanium.pay.util.FastParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import static com.hanium.pay.openBO.OpenAPIConstant.*;

@Slf4j
@Service
@AllArgsConstructor
public class OpenAPIImpl implements OpenAPI{

    private final OpenAPICall openAPICall;

    @Value("{openAPI.api}")
    private final String API_KEY;

    @Value("{openAPI.sercret")
    private final String SECRET_KEY;

    @Value("{openAPI.state}")
    private final String STATE;



    @Override
    public String getAuthorizationCode(){

            String body = "response_type=code"+
                    "&client_id="+API_KEY+
                    "&redirect_uri="+CALL_BACK_URL+
                    "&scope="+LOGIN_POLICY+
                    "&state="+STATE+
                    "&auth_type=0" +
                    "&cellphone_cert_yn=Y" +
                    "&authorized_cert_yn=N";

            return BASE_URL+GET_OAUTH+body;
    }


    @Override
    public List<String> getAccessAndRefreshToken(String authorize_code) throws Exception{

        log.info(authorize_code);

        String access_Token;
        String refresh_Token;
        String body;
        String result;

        body = "code=" + authorize_code +
                "&client_id="+API_KEY +
                "&client_secret="+SECRET_KEY +
                "&redirect_uri="+CALL_BACK_URL+
                "&grant_type=authorization_code";

        result = openAPICall.call(BASE_URL, GET_TOKEN, body, HttpMethod.POST, "");

        FastParser fastParser = FastParser.create(result);
        access_Token = fastParser.getString("access_token");
        refresh_Token = fastParser.getString("refresh_token");

        List<String> tokens = new ArrayList<>();
        tokens.add(access_Token);
        tokens.add(refresh_Token);

        return tokens;

    }

    @Override
    public List<String> refreshAccessToken(String accessToken) throws Exception{


        String access_Token;
        String refresh_Token = "";
        String body;
        String result;
        List<String> tokens = new ArrayList<>();

        body = "refresh_token=" + refresh_Token +
                "&client_id="+API_KEY +
                "&client_secret="+SECRET_KEY +
                "&scope="+LOGIN_POLICY;

        result = openAPICall.call(BASE_URL, GET_TOKEN, body, HttpMethod.POST, "");

        FastParser fastParser = FastParser.create(result);
        access_Token = fastParser.getString("access_token");
        refresh_Token = fastParser.getString("refresh_token");

        tokens.add(access_Token);
        tokens.add(refresh_Token);

        return tokens;

    }

}
