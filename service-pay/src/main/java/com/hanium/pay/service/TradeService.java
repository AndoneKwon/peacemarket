package com.hanium.pay.service;


import com.hanium.pay.model.Trade;
import com.hanium.pay.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    //요청처리
    @Transactional
    public void tradeHandling(Trade trade){
        tradeRepository.save(trade);
    }

}
