package com.ecommerce.tradeon.Entity.Banner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Banner {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String linkUrl;
    private String imageName;

    public Banner(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
