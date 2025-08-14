package com.ecommerce.tradeon.Dto.Search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchCondition {

    private String Keyword;
    private String sido;
    private String sigun;
    private String dong;
}
