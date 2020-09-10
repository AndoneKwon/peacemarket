package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
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
    public UserLoginResponseDto(User entity){
        this.id=entity.getId();
        this.email=entity.getEmail();
        this.password=entity.getPassword();
        this.salt=entity.getSalt();
    }
}
