package com.followme.search.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private String title;
    private int id;
    private String photo;
    private String author;

    public Search(String title,int id,String photo,String author){
        this.title=title;
        this.id=id;
        this.photo=photo;
        this.author=author;
    }
}
