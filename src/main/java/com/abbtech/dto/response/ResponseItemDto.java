package com.abbtech.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private String description;
    private RelatedEntityDto brand;
    private RelatedEntityDto category;
}
