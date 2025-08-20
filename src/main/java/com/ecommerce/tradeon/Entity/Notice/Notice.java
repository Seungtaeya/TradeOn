package com.ecommerce.tradeon.Entity.Notice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate createdAt;
    private boolean state;


    public Notice(String title, LocalDate createdAt, boolean state) {
        this.title = title;
        this.createdAt = createdAt;
        this.state = state;
    }
}
