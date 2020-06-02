package com.jpaex.board.web;

import com.jpaex.board.service.post.PostService;
import com.jpaex.board.web.dto.PostListResponseDto;
import com.jpaex.board.web.dto.PostResponseDto;
import com.jpaex.board.web.dto.PostSaveRequestDto;
import com.jpaex.board.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public String save(@RequestBody PostSaveRequestDto requestDto){
        try{
            postService.save(requestDto);
            return "Success";
        }finally {
            return "Fail";
        }
    }
    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        System.out.println(id);
        return postService.update(id,requestDto);
    }


    @GetMapping("/")
    public PostResponseDto findById(@RequestParam("id")  Long id){
        log.info(id.toString());
        return postService.findById(id);

    }

    @GetMapping("/author")
    public List<PostListResponseDto> findByAuthor(@RequestParam(value = "author") String author){
        return postService.findByAuthor(author);
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
