package com.abbtech.productservice.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestCategoryDto(
        Long id,
        @NotBlank(message = "Category adi bos ola bilmez")
        @Size(min = 2, max = 50, message = "Category adi 2 ile 50 simvol arasinda olmalidir")
        String name,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        String description,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        String image,
        @PositiveOrZero(message = "Parent id menfi ola bilmez")
        Integer parentId,
        @PositiveOrZero(message = "Category order menfi ola bilmez")
        Integer categoryOrder,
        Boolean isActive,
        Boolean isDeleted
) {
}
