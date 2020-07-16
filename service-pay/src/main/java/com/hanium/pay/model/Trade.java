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

    private LocalDateTime tradeTime;

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

    @PrePersist
    public void tradeTime(){
        this.tradeTime = LocalDateTime.now();
    }


}
