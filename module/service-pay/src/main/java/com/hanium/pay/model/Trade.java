package com.hanium.pay.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long id;

    private String consumer;

    private String producer;

    private String goodsId;

    private String price;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;



    private LocalDateTime tradeTime;

    public Trade(String consumer, String producer, String goodsId, String price, TradeType tradeType){
        this.consumer = consumer;
        this.producer = producer;
        this.goodsId = goodsId;
        this.price = price;
        this.tradeType = tradeType;
        tradeTime();
    }

    public void setConsumer(String consumer){
        this.consumer = consumer;
    }

    public void setProducer(String producer){
        this.producer = producer;
    }

    public void setGoodsId(String goodsId){
        this.goodsId = goodsId;
    }

    public  void setPrice(String price){
        this.price = price;
    }

    public  void setTradeType(TradeType tradeType){
        this.tradeType = tradeType;
    }

    @PrePersist
    public void tradeTime(){
        this.tradeTime = LocalDateTime.now();
    }


}
