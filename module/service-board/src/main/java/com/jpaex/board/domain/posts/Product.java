package com.jpaex.board.domain.posts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long post_id;

    private String name;

    private String type;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private int price;


}