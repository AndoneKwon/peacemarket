package com.hanium.pay.payload.request;

import lombok.Getter;

@Getter
public class UserPoint {

    String userId;
    String point;

    public UserPoint(String userId, String point){
        this.userId = userId;
        this.point = point;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setPoint(String point){
        this.point = point;
    }

}
