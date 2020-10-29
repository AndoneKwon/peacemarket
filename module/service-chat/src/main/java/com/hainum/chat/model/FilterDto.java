package com.hainum.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
    private final String tokenizer = "nori_my_dict_tokenizer";
    private String text;

}
