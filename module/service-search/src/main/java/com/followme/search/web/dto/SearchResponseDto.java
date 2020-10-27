package com.followme.search.web.dto;

import com.followme.search.domain.Search;
import lombok.Builder;

@Builder
public class SearchResponseDto {

    private String title;
    private String photo;
    private int id;


    public SearchResponseDto(Search entity){
        this.title=entity.getTitle();
        this.photo=entity.getPhoto();
        this.id=entity.getId();
    }

}
