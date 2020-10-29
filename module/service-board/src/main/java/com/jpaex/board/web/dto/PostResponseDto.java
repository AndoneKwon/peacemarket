package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.Post;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    /*
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
     */
    private List<String> photos=new ArrayList<>();
    private int price;
    private String author;
    private Long authorId;

    public PostResponseDto(Post entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.contents=entity.getContent();
        if(entity.getPhoto1()!=null){
            photos.add(entity.getPhoto1());
        }
        if(entity.getPhoto2()!=null){
            photos.add(entity.getPhoto2());
        }
        if(entity.getPhoto3()!=null){
            photos.add(entity.getPhoto3());
        }
        if(entity.getPhoto4()!=null){
            photos.add(entity.getPhoto4());
        }
        if(entity.getPhoto5()!=null){
            photos.add(entity.getPhoto5());
        }
        if(entity.getPhoto6()!=null){
            photos.add(entity.getPhoto6());
        }
        if(entity.getPhoto7()!=null){
            photos.add(entity.getPhoto7());
        }
        if(entity.getPhoto8()!=null){
            photos.add(entity.getPhoto8());
        }
        if(entity.getPhoto9()!=null){
            photos.add(entity.getPhoto9());
        }
        if(entity.getPhoto10()!=null){
            photos.add(entity.getPhoto10());
        }
        this.price = entity.getPrice();
        this.author=entity.getAuthor();
        this.authorId=entity.getAuthorId();
    }
}
