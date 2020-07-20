package com.jpaex.board.web;

import com.jpaex.board.service.post.PostService;
import com.jpaex.board.web.dto.PostListResponseDto;
import com.jpaex.board.web.dto.PostResponseDto;
import com.jpaex.board.web.dto.PostSaveRequestDto;
import com.jpaex.board.web.dto.PostUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

//@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    //final PostSaveRequestDto requestDto;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
/*
    @PostMapping("/create")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }
    */


    @PostMapping("/create")
    public Long save(HttpServletRequest request,@RequestBody List<MultipartFile> files){
        PostSaveRequestDto requestDto=new PostSaveRequestDto();
        Random rnd = new Random();
        StringBuffer tempStr = new StringBuffer();
        String randomStr;
        String fileExtension;
        String[] fileNames = new String[10];
        requestDto.setTitle(request.getParameter("title"));
        requestDto.setAuthor(request.getParameter("author"));
        requestDto.setContent(request.getParameter("content"));
        logger.info("title:"+requestDto.getTitle());
        if(files!=null){
            try{
                for(int i=0;i<files.size();i++){
                    for(int j=0;j<30;j++){
                        randomStr = String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
                        tempStr.append(randomStr);
                    }
                    fileNames[i]=tempStr.toString()+"."+files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf(".")+1);
                    files.get(i).transferTo(new File("D:\\practice\\PostApiSpring\\src\\main\\resources\\static\\img\\"+tempStr.toString()+"."+files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf(".")+1)));
                    tempStr.delete(0,tempStr.length());
                }
            }catch (IllegalStateException | IOException e){
                e.printStackTrace();
            }
            requestDto.setPhoto1(fileNames[0]);
            requestDto.setPhoto2(fileNames[1]);
            requestDto.setPhoto3(fileNames[2]);
            requestDto.setPhoto4(fileNames[3]);
            requestDto.setPhoto5(fileNames[4]);
            requestDto.setPhoto6(fileNames[5]);
            requestDto.setPhoto7(fileNames[6]);
            requestDto.setPhoto8(fileNames[7]);
            requestDto.setPhoto9(fileNames[8]);
            requestDto.setPhoto10(fileNames[9]);
            requestDto.toEntity();
        }else{

        }

        logger.info("control"+requestDto.getPhoto1());
        return postService.save(requestDto);
    }

    @PostMapping(value = "/update/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto requestDto){
        System.out.println(id);
        return postService.update(id,requestDto);
    }

    @GetMapping("search/{title}")
    public List<PostListResponseDto> findByTitle(@PathVariable("title") String title){
        return postService.findByTitle(title);
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
    public String fileupload(HttpServletRequest request,PostSaveRequestDto requestDto,@RequestBody List<MultipartFile> files){
        Random rnd = new Random();
        StringBuffer tempStr = new StringBuffer();
        String randomStr;
        String fileExtension;
        String[] fileNames = new String[10];
        try{
            for(int i=0;i<files.size();i++){
                for(int j=0;j<30;j++){
                    randomStr = String.valueOf((char) ((int) (rnd.nextInt(52)) + 65));
                    tempStr.append(randomStr);
                }
                logger.info(tempStr.toString()+"."+files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf(".")+1));
                fileNames[i]=tempStr.toString()+"."+files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf(".")+1);
                files.get(i).transferTo(new File("/Users/gwonjin-u/IdeaProjects/boardEx/src/main/resources/static/img/"+tempStr.toString()+"."+files.get(i).getOriginalFilename().substring(files.get(i).getOriginalFilename().lastIndexOf(".")+1)));
            }
        }catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return "file upload";
    }
}
