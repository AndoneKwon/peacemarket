package com.hanium.pay.openBO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class OpenAPIImpl implements OpenAPI{

    @Autowired
    OpenAPICall openAPICall;


    @Override
    public String getAuthorizationCode() throws Exception{

            String body = "response_type=code"+
                    "&client_id="+API_KEY+
                    "&redirect_uri="+CALL_BACK_URL+
                    "&scope="+LOGIN_POLICY+
                    "&state="+STATE+
                    "&auth_type=0" +
                    "&cellphone_cert_yn=Y" +
                    "&authorized_cert_yn=N";

            return BASE_URL+GET_OAUTH+body;

            //return openAPICall.call(BASE_URL, GET_OAUTH, body, HttpMethod.GET, "");
    }


    @Override
    public List<String> getAccessToken(String authorize_code) throws Exception{

        log.info(authorize_code);

        String access_Token = "";
        String refresh_Token = "";
        String body = null;
        String result = null;

        body = "code=" + authorize_code +
                "&client_id="+API_KEY +
                "&client_secret="+SECRET_KEY +
                "&redirect_uri="+CALL_BACK_URL+
                "&grant_type=authorization_code";

        result = openAPICall.call(BASE_URL, GET_TOKEN, body, HttpMethod.POST, "");


        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        access_Token = element.getAsJsonObject().get("access_token").getAsString();
        refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

        List<String> tokens = new ArrayList<String>();
        tokens.add(access_Token);
        tokens.add(refresh_Token);

        return tokens;

    }

    @Override
    public List<String> refreshAccessToken(String accessToken) throws Exception{


        String access_Token = "";
        String refresh_Token = "";
        String body = null;
        String result = null;

        body = "refresh_token=" + refresh_Token +
                "&client_id="+API_KEY +
                "&client_secret="+SECRET_KEY +
                "&scope="+OpenAPI.LOGIN_POLICY;

        result = openAPICall.call(BASE_URL, GET_TOKEN, body, HttpMethod.POST, "");


        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        access_Token = element.getAsJsonObject().get("access_token").getAsString();
        refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

        List<String> tokens = new ArrayList<String>();
        tokens.add(access_Token);
        tokens.add(refresh_Token);

        return tokens;

    }

}
