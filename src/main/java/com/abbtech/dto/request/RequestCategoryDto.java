package com.abbtech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Integer parentId;
    private Integer categoryOrder;
    private Boolean isActive;
    private Boolean isDeleted;
}
