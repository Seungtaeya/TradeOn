package com.ecommerce.tradeon.Entity.Notice;

import com.ecommerce.tradeon.Enums.NoticeStatus;
import jakarta.persistence.*;
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
    private String content;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private NoticeStatus state;


    public Notice(String title, LocalDate createdAt,String content, NoticeStatus state) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.state = state;
    }
}
