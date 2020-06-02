package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.User;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserJoinDto {
    private String email;
    private String password;
    private char gender;
    private String address;
    private String phoneNumber;
    private String nickName;
    private String salt=Integer.toString((int)(Math.random()*10000));
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Builder
    public UserJoinDto(String email,String password,char gender, String address,String phoneNumber,String nickName){
        this.email=email;
        this.password=password;
        /*
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.nickName=nickName;

         */
    }

    public User toEntity(){
        logger.info("test:"+salt);
        return User.builder()
                .email(email)
                .password(password)
                .salt(salt)
                .build();
    }
}
