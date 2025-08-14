package com.ecommerce.tradeon.Entity.Member;

import com.ecommerce.tradeon.Entity.vo.Address;
import com.ecommerce.tradeon.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(unique = true)
    private String email;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String password;
    private String username;
    private String phone;
    private LocalDateTime created_at;

    @PrePersist
    private void createDateTime() {
        this.created_at = LocalDateTime.now();
    }

    public Member(String email, Address address, String password, String username, String phone) {
        this.email = email;
        this.address = address;
        this.role = Role.USER;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }

    public void changeRole(String role) {
        if (Role.ADMIN.name().equals(role)){
            this.role = Role.ADMIN;
        } else if (Role.COMPANY.name().equals(role)) {
            this.role = Role.COMPANY;
        } else {
            this.role = Role.USER;
        }
    }
}
