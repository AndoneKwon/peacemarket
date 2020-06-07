package com.jpaex.board.web;

import com.jpaex.board.service.post.PostService;
import com.jpaex.board.web.dto.PostListResponseDto;
import com.jpaex.board.web.dto.PostResponseDto;
import com.jpaex.board.web.dto.PostSaveRequestDto;
import com.jpaex.board.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @PostMapping("/create")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }

    @PostMapping(value = "/update/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto requestDto){
        System.out.println(id);
        return postService.update(id,requestDto);
    }


    @GetMapping("{id}")
    public PostResponseDto findById(@PathVariable("id")  Long id){
        System.out.println(id);
        return postService.findById(id);

    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @GetMapping("/author/{author}")
    public List<PostListResponseDto> findByAuthor(@PathVariable("author") String Author){
        logger.info(Author);
        return postService.findByAuthor(Author);
    }


    @GetMapping("/getList")
    public List<PostListResponseDto> findAll() {
        return postService.findAllDesc();
    }

    @RequestMapping("/uploadMultipleFiles")
    public String fileupload(HttpServletRequest request, @RequestParam("files") List<MultipartFile> files){
        logger.info("try upload 1");
        try{
            logger.info("try upload 2");
            logger.info(Integer.toString(files.size()));
            for(int i=0;i<files.size();i++){
                files.get(i).transferTo(new File("/Users/gwonjin-u/IdeaProjects/boardEx/src/main/resources/static/img/"+files.get(i).getOriginalFilename()));
            }
        }catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return "file upload";
    }
}
