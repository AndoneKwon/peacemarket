package com.jpaex.board.web.dto;

import com.hanium.common.model.board.User;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    private Long id;
    private String email;
    private String password;
    private String salt;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .salt(salt)
                .build();
    }//Dto를 Entitiy화
}
