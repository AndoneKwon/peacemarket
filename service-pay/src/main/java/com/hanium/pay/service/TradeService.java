package com.hanium.pay.service;


import com.hanium.pay.model.Trade;
import com.hanium.pay.payload.request.UserPoint;
import com.hanium.pay.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    RestTemplate template;

    //요청처리
    @Transactional
    public void tradeHandling(Trade trade){

        int result = -1;

        UserPoint consumerTest = new UserPoint("abc", "200");
        UserPoint ProducerTest = new UserPoint("abc", "200");

        //consumer 포인트 차감

        //producer 포인트 차감


        //거래로그 생성
        tradeRepository.save(trade);
    }

}
