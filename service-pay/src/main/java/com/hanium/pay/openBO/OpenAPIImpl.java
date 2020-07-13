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
    public String getAuthCode(){
        String auth_Code = "";
        String reqURL = "https://openapi.openbanking.or.kr/oauth/2.0/authorize";

        try {
            URL url = new URL(reqURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            //hader config
            //conn.setRequestProperty("Kftc-Bfop-UserSeqNo", "15161561");

            //GET config
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            //parameter Stream sending
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("response_type=code");
            sb.append("&client_id="+API_KEY);
            sb.append("&scope=login");
            sb.append("&state=12345645661234564566123456456633");
            sb.append("&auth_type=0");
            sb.append("&redirect_uri=http://localhost:8080/login");
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

            auth_Code = element.getAsJsonObject().get("code").getAsString();

           System.out.println("auth_code : " + auth_Code);

            br.close();
            bw.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        return auth_Code;

    }

    @Override
    public List<String> getAccessToken(String authorize_code) {


        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://openapi.openbanking.or.kr/oauth/2.0/token";

        try {
            URL url = new URL(reqURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            //POST config
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //parameter Stream sending
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("code =" + authorize_code);
            sb.append("&client_id="+API_KEY);
            sb.append("&client_secret"+SECRET_KEY);
            sb.append("&redirect_uri=http://localhost:8080/pay");
            sb.append("&grant_type=authorization_code");
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
