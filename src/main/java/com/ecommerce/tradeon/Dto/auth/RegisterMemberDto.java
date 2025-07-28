package com.ecommerce.tradeon.Dto.auth;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterMemberDto {


    private String username;
    @Email
    private String email;
    private String password;
    private String phone;
    private AddressDto addressdto;

    public RegisterMemberDto(String username, String email, String password, String phone, AddressDto address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.addressdto = address;
    }
}
