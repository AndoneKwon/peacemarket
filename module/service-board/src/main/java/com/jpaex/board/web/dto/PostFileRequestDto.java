package com.jpaex.board.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Access;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostFileRequestDto {
    private String title;
    private String content;
    private int price;
    private List<MultipartFile> photo;
}
