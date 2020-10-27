package com.followme.search.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private String title;
    private int id;
    private String photo;

    public Search(String title,int id,String photo){
        this.title=title;
        this.id=id;
        this.photo=photo;
    }
}
