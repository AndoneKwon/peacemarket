package com.jpaex.board.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();
    //@Query(value = "SELECT p FROM Post p WHERE p.author=?1")
    List<Post> findByAuthor(String author);
}