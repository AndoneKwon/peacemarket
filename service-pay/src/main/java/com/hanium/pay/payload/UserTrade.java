package com.hanium.pay.payload;


import lombok.Getter;

@Getter
public class UserTrade {


    private String consumer;

    private String prouder;

    private String goodsId;

    private String price;


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

}
