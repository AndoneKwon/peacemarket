package com.hanium.pay.controller;


import com.hanium.pay.adapter.ParticipationRequest;
import com.hanium.pay.model.Trade;
import com.hanium.pay.openBO.OpenAPI;
import com.hanium.pay.openBO.OpenAPIAuth;
import com.hanium.pay.payload.request.UserPoint;
import com.hanium.pay.payload.request.UserTradeRequest;
import com.hanium.pay.payload.response.ApiResponse;
import com.hanium.pay.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class PayController {

    @Autowired
    private OpenAPI open;

    @Autowired
    private OpenAPIAuth auth;

    @Autowired
    private TradeService tradeService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RedirectView Test(){

        RedirectView redirectView = new RedirectView();
        System.out.println("KEY  " + auth.getURL());
        redirectView.setUrl(auth.getURL());
        return redirectView;


    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String Test2(){

        URI uri = URI.create("localhost:8080/test3");

        UserPoint userPoint = new UserPoint("dsfsf", "16165");

        System.out.println(userPoint);

        ParticipationRequest tempPart = new ParticipationRequest("TEST URL", userPoint);

        RequestEntity<Object> request =
                RequestEntity.post(uri)
                        .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(tempPart.getRequestBody());

        System.out.println(request.getBody());

        return "ABC";


    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String Test3(){

        return "ABC";


    }

//    @RequestMapping(value ="/auth", method=RequestMethod.GET)
//    public void OpenAuth(HttpServletResponse response){
//        String auth_Code = open.getAuthCode();
//    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String OpenLogin(@RequestParam("code") String code, HttpServletResponse response ) {

        List<String> tokens = open.getAccessToken(code);
        log.info("code  : " + code +"\n"+ "Aceess : " + tokens.get(0)+"\n" + "Refresh : " + tokens.get(1));

        String acces_token = new String(tokens.get(0));
        String refresh_token = new String(tokens.get(1));

        return acces_token;

    }

    @PostMapping("/trade")
    public ResponseEntity<?> trade(@Valid @RequestBody UserTradeRequest userTradeRequest){



        //여기서 관련된 API소스들 호출함(외부 DB 연결 API 호출)


        Trade trade = new Trade(userTradeRequest.getConsumer(), userTradeRequest.getProuder(), userTradeRequest.getGoodsId(), userTradeRequest.getPrice(),
                userTradeRequest.getTradeType());

        tradeService.tradeHandling(trade);


        return new ResponseEntity(new ApiResponse(true, "Success"), HttpStatus.OK);


    }

}
