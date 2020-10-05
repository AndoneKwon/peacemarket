package com.followme.search.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private int category;
    private float grade_avg;
    private String shopname;
    private int likenum;
    private String address;
    private String photo;

    public Search(int category,float grade_avg,String shopname,int likenum,String address,String photo){
        this.shopname = shopname;
        this.address=address;
        this.category=category;
        this.grade_avg=grade_avg;
        this.likenum=likenum;
        this.photo=photo;
    }

    public Search() {

    }
}
