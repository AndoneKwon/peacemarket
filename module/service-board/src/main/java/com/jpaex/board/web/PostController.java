package com.jpaex.board.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpaex.board.domain.posts.Product;
import com.jpaex.board.domain.posts.ProductRepository;
import com.jpaex.board.domain.posts.ProductType;
import com.jpaex.board.service.post.PostService;
import com.jpaex.board.web.dto.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ProductRepository productRepository;
    //final PostSaveRequestDto requestDto;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
/*
    @PostMapping("/create")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }
    */

    @PostMapping("/create")
    public int save(@RequestHeader(value = "authorization") String token, PostFileRequestDto files) throws UnsupportedEncodingException {
        PostSaveRequestDto requestDto = new PostSaveRequestDto();
        ObjectMapper mapper = new ObjectMapper();
        Algorithm a = Algorithm.HMAC256("nodebird");
        String jwt = token;
        JWTVerifier verifier = JWT.require(a)
                .build();

        DecodedJWT decodedJWT = verifier.verify(jwt);
        Long authorId = decodedJWT.getClaim("user_id").asLong();
        String author = decodedJWT.getClaim("nickname").asString();

        Random rnd = new Random();
        StringBuffer tempStr = new StringBuffer();
        String randomStr;
        String fileExtension;
        String[] fileNames = new String[10];
        requestDto.setTitle(files.getTitle());
        requestDto.setContent(files.getContent());
        requestDto.setPrice(files.getPrice());
        requestDto.setAuthorId(authorId);
        requestDto.setAuthor(author);
        logger.info(files.getTitle());
        if (files != null) {
            try {
                for (int i = 0; i < files.getPhoto().size(); i++) {
                    for (int j = 0; j < 30; j++) {
                        randomStr = String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
                        tempStr.append(randomStr);
                    }
                    fileNames[i] = tempStr.toString() + "." + files.getPhoto().get(i).getOriginalFilename().substring(files.getPhoto().get(i).getOriginalFilename().lastIndexOf(".") + 1);
                    files.getPhoto().get(i).transferTo(new File("D:\\practice\\img\\" + tempStr.toString() + "." + files.getPhoto().get(i).getOriginalFilename().substring(files.getPhoto().get(i).getOriginalFilename().lastIndexOf(".") + 1)));
                    tempStr.delete(0, tempStr.length());
                }
            } catch (IllegalStateException | IOException e) {
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


            //logger.info("control"+requestDto.getPhoto1());
        }
        Product product = new Product();
        product.setPost_id(postService.save(requestDto));
        product.setName(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setProductType(ProductType.SELLING);
        productRepository.save(product);
        return 1;
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
