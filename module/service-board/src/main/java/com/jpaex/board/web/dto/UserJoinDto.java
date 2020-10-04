package com.jpaex.board.web.dto;

import com.hanium.common.model.board.User;
import lombok.Builder;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
public class UserJoinDto {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    private String email;
    private String password;
    private char gender;
    private String address;
    private String phoneNumber;
    private String nickname;
    private String salt=Integer.toString((int)(Math.random()*10000000));
    private Byte auth=5;

    @Builder
    public UserJoinDto(String email,String password,char gender, String address,String phoneNumber,String nickName,String salt,Byte auth){
        this.email=email;
        this.password=password;
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.nickname=nickName;
        //this.auth=auth;
        //this.salt=salt;
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .gender(gender)
                .address(address)
                .phoneNumber(phoneNumber)
                .nickName(nickname)
                .auth(auth)
                .salt(salt)
                .build();
    }
}
