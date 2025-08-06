package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Dto.Qna.QnaDto;
import com.ecommerce.tradeon.Entity.Qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findByProductId(Long productId);
}
