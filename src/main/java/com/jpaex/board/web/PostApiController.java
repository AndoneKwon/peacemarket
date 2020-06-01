package com.jpaex.board.web;

import com.jpaex.board.service.post.PostService;
import com.jpaex.board.web.dto.PostListResponseDto;
import com.jpaex.board.web.dto.PostResponseDto;
import com.jpaex.board.web.dto.PostSaveRequestDto;
import com.jpaex.board.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostApiController {
    private PostService postService;

    @PostMapping("/posts/create")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }
/*
    @RequestMapping(value = "/getPost",method = RequestMethod.GET)
    public PostResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }
*/
    @GetMapping(value = "/update/")
    public Long update(@PathVariable Long id,@RequestBody PostUpdateRequestDto requestDto){
        return postService.update(id,requestDto);
        //return "String";
    }


    @GetMapping("/{id}")
    public PostResponseDto findById(@RequestParam Long id){
        return postService.findById(id);

    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }


    @GetMapping("/getList")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

}
