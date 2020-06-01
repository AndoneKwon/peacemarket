package com.jpaex.board.web.dto;

import com.jpaex.board.domain.posts.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;

    public PostResponseDto(Post entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.contents=entity.getContent();
    }
}
