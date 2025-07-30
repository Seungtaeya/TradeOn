package com.ecommerce.tradeon.Dto.Member;

import com.ecommerce.tradeon.Entity.Member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AdminMemberDto {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String role;
    private LocalDateTime createdAt;

    public static AdminMemberDto setForm(Member member) {
        AdminMemberDto dto = new AdminMemberDto();
        dto.setId(member.getId());
        dto.setUsername(member.getUsername());
        dto.setEmail(member.getEmail());
        dto.setPassword(member.getPassword());
        dto.setRole(String.valueOf(member.getRole()));
        dto.setCreatedAt(member.getCreated_at());
        dto.setPhone(member.getPhone());
        return dto;
    }

    public AdminMemberDto( String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
