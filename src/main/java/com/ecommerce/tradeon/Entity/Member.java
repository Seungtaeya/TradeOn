package com.ecommerce.tradeon.Entity;

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

    @Id @GeneratedValue
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
}
