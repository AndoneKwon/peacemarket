package com.jpaex.board.web.dto;

import com.hanium.common.model.board.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String photo1;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.photo1 = entity.getPhoto1();
    }
}
