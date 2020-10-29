package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String photo6;
    private String photo7;
    private String photo8;
    private String photo9;
    private String photo10;
    private int price;

    public PostResponseDto(Post entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.contents=entity.getContent();
        this.photo1 = entity.getPhoto1();
        this.photo2 = entity.getPhoto2();
        this.photo3 = entity.getPhoto3();
        this.photo4 = entity.getPhoto4();
        this.photo5 = entity.getPhoto5();
        this.photo6 = entity.getPhoto6();
        this.photo7 = entity.getPhoto7();
        this.photo8 = entity.getPhoto8();
        this.photo9 = entity.getPhoto9();
        this.photo10 = entity.getPhoto10();
        this.price = entity.getPrice();
    }
}
