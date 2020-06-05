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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    @PostMapping("/uploadMultipleFiles")
    public  String uploadMultipleFiles(@RequestParam("description") String[] descriptions,@RequestParam("file") MultipartFile[] files) {
        if (files.length != descriptions.length)
            return "Mismatching no of files are equal to description";
        String status = "";
        File dir = new File("/Users/gwonjin-u/IdeaProjects/boardEx/src/main/resources");

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String description = descriptions[i];
            try {
                byte[] bytes = file.getBytes();

                if (!dir.exists())
                    dir.mkdirs();

                File uploadFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
                outputStream.write(bytes);
                outputStream.close();

                status = status + "You successfully uploaded file=" + file.getOriginalFilename();
            } catch (Exception e) {
                status = status + "Failed to upload " + file.getOriginalFilename()+ " " + e.getMessage();
            }
        }
        return status;
    }
}
