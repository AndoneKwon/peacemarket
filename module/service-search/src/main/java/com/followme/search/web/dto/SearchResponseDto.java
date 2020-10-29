package com.followme.search.web.dto;

import com.followme.search.domain.Search;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchResponseDto {
    private int id;
    private String title;
    private String photo;


    public SearchResponseDto(Search entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.photo=entity.getPhoto();
    }

}
