package com.ecommerce.tradeon.Repository;

import com.ecommerce.tradeon.Entity.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
