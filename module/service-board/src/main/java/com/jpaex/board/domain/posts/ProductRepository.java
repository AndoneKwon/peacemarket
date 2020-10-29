package com.jpaex.board.domain.posts;

import com.jpaex.board.web.dto.ProductRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}