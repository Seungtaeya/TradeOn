package com.ecommerce.tradeon.Entity.Wishlist;

import com.ecommerce.tradeon.Entity.Member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class WishList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "wishList")
    private List<WishListItem> itemLIst = new ArrayList<>();

    private String title;

    public WishList(String title) {
        this.title = title;
    }

    public void addMember(Member member) {
        this.member = member;
    }
}
