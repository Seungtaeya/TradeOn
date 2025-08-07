package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.WishList.WishListDto;
import com.ecommerce.tradeon.Dto.WishList.WishListItemDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Wishlist.WishList;
import com.ecommerce.tradeon.Repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final MemberService memberService;

    @Transactional
    public WishList saveWishList(WishListDto wishListDto) {
        WishList wishList = new WishList(wishListDto.getTitle());
        Member byMemberId = memberService.findByMemberId(wishListDto.getMemberId());
        wishList.addMember(byMemberId);

        return wishListRepository.save(wishList);
    }

    public WishList findWishListEntity(Long wishListId) {
        return wishListRepository.findById(wishListId)
                .orElseThrow(() -> new IllegalArgumentException("목록이 비어있습니다."));
    }

    public List<WishListDto> findWishListByMemberId(Long memberId) {
        return wishListRepository.findByMember_Id(memberId)
                .stream()
                .map(WishListDto::setForm)
                .toList();
    }
}
