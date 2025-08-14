package com.ecommerce.tradeon.Dto.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegionDto {

    private String sido;
    private String sigun;
    private String dong;

    public RegionDto(String sido, String sigun, String dong) {
        this.sido = sido;
        this.sigun = sigun;
        this.dong = dong;
    }
}
