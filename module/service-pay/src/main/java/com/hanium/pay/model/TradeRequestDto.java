package com.hanium.pay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TradeRequestDto {
    private Long sellerId;
    private Long productId;
    private Long price;
}
