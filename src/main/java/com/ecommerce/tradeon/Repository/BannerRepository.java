package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Banner.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
