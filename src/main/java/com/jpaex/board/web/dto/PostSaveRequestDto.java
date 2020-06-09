package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //기본생성자 자동 생성 어노테이
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;
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

    @Builder
    public PostSaveRequestDto(String title, String content, String author,String photo1,String photo2,String photo3,String photo4,String photo5,String photo6,String photo7,String photo8,String photo9,String photo10){
        this.title = title;
        this.content = content;
        this.author = author;
        //this.author = author; -> 불필요한 중복 제거
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.photo6 = photo6;
        this.photo7 = photo7;
        this.photo8 = photo8;
        this.photo9 = photo9;
        this.photo10 = photo10;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .photo1(photo1)
                .photo2(photo2)
                .photo3(photo3)
                .photo4(photo4)
                .photo5(photo5)
                .photo6(photo6)
                .photo7(photo7)
                .photo8(photo8)
                .photo9(photo9)
                .photo10(photo10)
                .build();
    }
}
