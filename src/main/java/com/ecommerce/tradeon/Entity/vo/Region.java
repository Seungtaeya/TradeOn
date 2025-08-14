package com.ecommerce.tradeon.Entity.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Region {

    private String sido;
    private String sigun;
    private String dong;

    public Region() {
    }

    public Region(String sido, String sigun, String dong) {
        this.sido = sido;
        this.sigun = sigun;
        this.dong = dong;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(sido, region.sido) && Objects.equals(sigun, region.sigun) && Objects.equals(dong, region.dong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sido, sigun, dong);
    }
}
