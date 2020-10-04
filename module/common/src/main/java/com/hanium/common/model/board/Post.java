package com.hanium.common.model.board;

import com.hanium.common.model.audit.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Table(name ="Posts")
@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;

    @ColumnDefault("null")
    private String photo1;
    @ColumnDefault("null")
    private String photo2;
    @ColumnDefault("null")
    private String photo3;
    @ColumnDefault("null")
    private String photo4;
    @ColumnDefault("null")
    private String photo5;
    @ColumnDefault("null")
    private String photo6;
    @ColumnDefault("null")
    private String photo7;
    @ColumnDefault("null")
    private String photo8;
    @ColumnDefault("null")
    private String photo9;
    @ColumnDefault("null")
    private String photo10;

    @Builder
    public Post(String title,String content,String author,String photo1,String photo2,String photo3,String photo4,String photo5,String photo6,String photo7,String photo8,String photo9,String photo10){
        this.title = title;
        this.content = content;
        this.author = author;
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

    public void update(String title, String content,String photo1,String photo2,String photo3,String photo4,String photo5,String photo6,String photo7,String photo8,String photo9,String photo10){
        this.title=title;
        this.content=content;
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
}
