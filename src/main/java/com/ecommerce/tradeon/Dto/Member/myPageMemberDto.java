package com.ecommerce.tradeon.Dto.Member;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class myPageMemberDto {

    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    public myPageMemberDto(String name, String email, String phone, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }
}
