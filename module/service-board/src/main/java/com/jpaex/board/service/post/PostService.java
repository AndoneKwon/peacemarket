package com.jpaex.board.service.post;

import com.jpaex.board.domain.posts.Post;
import com.jpaex.board.domain.posts.PostRepository;
import com.jpaex.board.web.dto.PostListResponseDto;
import com.jpaex.board.web.dto.PostResponseDto;
import com.jpaex.board.web.dto.PostSaveRequestDto;
import com.jpaex.board.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        logger.info("Service : "+requestDto.getPhoto1());
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent(),requestDto.getPhoto1(),requestDto.getPhoto2(),requestDto.getPhoto3(),
                requestDto.getPhoto4(),requestDto.getPhoto5(),requestDto.getPhoto6(),requestDto.getPhoto7(),requestDto.getPhoto8(),requestDto.getPhoto9(),requestDto.getPhoto10());

        return id;
    }
    //게시물 삭제
    @Transactional
    public void delete (Long id) {
        Post posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        postRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findByAuthor(String author){
        return postRepository.findByAuthor(author).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findByTitle(String title){
        return postRepository.findByTitleContaining(title).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
