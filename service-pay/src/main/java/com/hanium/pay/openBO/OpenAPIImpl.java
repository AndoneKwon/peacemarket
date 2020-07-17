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
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class OpenAPIImpl implements OpenAPI{

    String API_KEY = "j9mhYBLjb2uQL9PFIhB1FKNUnu3t8AX0Mh7bO08M";
    String SECRET_KEY = "";

    @Override
    public List<String> getAccessToken(String authorize_code) {

        log.info(authorize_code);

        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token";

        try {
            URL url = new URL(reqURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            //POST config
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //Header config
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            //parameter Stream sending
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("code=" + authorize_code);
            sb.append("&client_id="+API_KEY);
            sb.append("&client_secret="+SECRET_KEY);
            sb.append("&redirect_uri=http://localhost:8080/login");
            sb.append("&grant_type=authorization_code");
            System.out.println(sb.toString());
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            log.info("responose : "+responseCode);


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        List<String> tokens = new ArrayList<String>();
        tokens.add(access_Token);
        tokens.add(refresh_Token);

        return tokens;

    }

}
