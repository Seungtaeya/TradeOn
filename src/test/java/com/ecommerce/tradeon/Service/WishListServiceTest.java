package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Category.CategoryDto;
import com.ecommerce.tradeon.Dto.Product.ProductDto;
import com.ecommerce.tradeon.Dto.WishList.WishListDto;
import com.ecommerce.tradeon.Dto.WishList.WishListItemDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Wishlist.WishList;
import com.ecommerce.tradeon.Entity.Wishlist.WishListItem;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Entity.vo.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListServiceTest {

    @Autowired
    WishListService wishListService;
    @Autowired
    WishListItemService wishListItemService;
    @Autowired
    private ProductService productService;

    @Autowired
    MemberService memberService;

    @Autowired
    CategoryService categoryService;

    @BeforeEach
    public void forEach() {
        Member member = new Member("aaa@aaa.com",new Address("1","2","3"),"qwe","aaa","010-222-333");
        memberService.join(member);

        CategoryDto category = new CategoryDto("test",null);
        categoryService.createCategory(category);

        ProductDto productDto = new ProductDto(1L,1L,null,"testproduct","test",10000,100,true);
        Product product = productService.createProduct(productDto, null);

        ProductDto productDto1 = new ProductDto(1L,1L,null,"test","test",10,100,true);
        Product product1 = productService.createProduct(productDto1, null);
    }

    @Test
    @Transactional
    public void createWishListAndWishListItem() throws Exception {
        //given
        WishListDto dto = new WishListDto();
        dto.setTitle("test");
        dto.setMemberId(1L);


        WishListItemDto itemdto = new WishListItemDto();
        ProductDto productOne = productService.getProductOne(1L);
        itemdto.setProductDto(productOne);
        //when
        WishList wishList = wishListService.saveWishList(dto);
        itemdto.setWishListId(wishList.getId());
        WishListItem wishListItem = wishListItemService.saveWishItem(itemdto);
        //then

        assertThat(wishList.getTitle()).isEqualTo("test");
        assertThat(wishList.getMember().getUsername()).isEqualTo("aaa");
        assertThat(wishListItem.getWishList().getTitle()).isEqualTo("test");
        assertThat(wishListItem.getProduct().getTitle()).isEqualTo("testproduct");
    }


}