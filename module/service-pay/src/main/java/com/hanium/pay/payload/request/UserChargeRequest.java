package com.hanium.pay.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChargeRequest {

    Long userId;
    Long amount;

}
