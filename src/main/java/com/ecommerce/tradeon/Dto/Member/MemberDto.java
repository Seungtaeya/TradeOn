package com.ecommerce.tradeon.Dto.Member;

import com.ecommerce.tradeon.Dto.auth.AddressDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.vo.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String city;
    private String street;
    private String zipcode;
    private String password;
    private String newPassword;

    public static MemberDto setForm(Member member) {
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setUsername(member.getUsername());
        dto.setEmail(member.getEmail());
        dto.setPhone(member.getPhone());
        dto.setCity(member.getAddress().getCity());
        dto.setStreet(member.getAddress().getStreet());
        dto.setZipcode(member.getAddress().getZipcode());
        dto.setPassword(member.getPassword());

        return dto;
    }


}
