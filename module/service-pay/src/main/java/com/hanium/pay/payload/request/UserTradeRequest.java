package com.hanium.pay.payload.request;


import com.hanium.pay.model.TradeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTradeRequest {


    private Long sellerId;

    private Long purchaserId;

    private Long productId;

    private Long price;

    private TradeType tradeType;

}
