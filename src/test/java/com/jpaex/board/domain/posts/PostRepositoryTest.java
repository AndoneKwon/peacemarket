package com.jpaex.board.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void save_Call(){
        String title="first board";
        String content ="first content";

        LocalDateTime now = LocalDateTime.of(2020,5,1,0,0,0);

        postRepository.save(Post.builder()
            .title(title)
            .content(content)
            .author("wlsdn110@gmail.com")
            .build());

        List<Post> postList =postRepository.findAll();

        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getUpdatedDate()).isAfter(now);
    }


}
