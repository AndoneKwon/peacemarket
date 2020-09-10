package com.hanium.pay.payload.request;


import com.hanium.pay.model.TradeType;
import lombok.Getter;

@Getter
public class UserTradeRequest {


    private String consumer;

    private String prouder;

    private String goodsId;

    private String price;

    private TradeType tradeType;


    public void setConsumer(String consumer){
        this.consumer = consumer;
    }

    public void setProuder(String prouder){
        this.prouder = prouder;
    }

    public void setGoodsId (String goodsId){
        this.goodsId = goodsId;
    }

    public  void setPrice(String price){
        this.price = price;
    }

    public void setTradeType(TradeType tradeType){ this.tradeType = tradeType;}

}
