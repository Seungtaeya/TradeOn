package com.ecommerce.tradeon.Dto.WishList;

import com.ecommerce.tradeon.Entity.Wishlist.WishList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WishListDto {

    private Long id;
    private Long memberId;
    private List<WishListItemDto> list;
    private String title;


    public static WishListDto setForm(WishList wishList) {
        WishListDto dto = new WishListDto();
        dto.setId(wishList.getId());
        dto.setMemberId(wishList.getMember().getId());
        dto.setList(wishList.getItemLIst().stream()
                .map(WishListItemDto::setForm).toList());
        dto.setTitle(wishList.getTitle());

        return dto;
    }
}
