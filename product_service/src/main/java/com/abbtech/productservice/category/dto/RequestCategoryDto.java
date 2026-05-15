package com.abbtech.productservice.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "id": 1,
          "name": "Electronics",
          "description": "Electronic devices and accessories",
          "image": "https://example.com/images/electronics.png",
          "parentId": 0,
          "categoryOrder": 1,
          "isActive": true,
          "isDeleted": false
        }
        """)
public record RequestCategoryDto(
        @Schema(example = "1")
        Long id,
        @NotBlank(message = "Category adi bos ola bilmez")
        @Size(min = 2, max = 50, message = "Category adi 2 ile 50 simvol arasinda olmalidir")
        @Schema(example = "Electronics")
        String name,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        @Schema(example = "Electronic devices and accessories")
        String description,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        @Schema(example = "https://example.com/images/electronics.png")
        String image,
        @PositiveOrZero(message = "Parent id menfi ola bilmez")
        @Schema(example = "0")
        Integer parentId,
        @PositiveOrZero(message = "Category order menfi ola bilmez")
        @Schema(example = "1")
        Integer categoryOrder,
        @Schema(example = "true")
        Boolean isActive,
        @Schema(example = "false")
        Boolean isDeleted
) {
}
