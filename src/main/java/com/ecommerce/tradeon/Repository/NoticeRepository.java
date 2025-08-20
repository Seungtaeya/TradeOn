package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
