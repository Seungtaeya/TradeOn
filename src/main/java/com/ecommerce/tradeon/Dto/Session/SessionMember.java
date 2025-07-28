package com.ecommerce.tradeon.Dto.Session;

import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SessionMember {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    public SessionMember(Member member) {
        this.id = member.getId();
        this.name = member.getUsername();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.createdAt = member.getCreated_at();
    }

}
