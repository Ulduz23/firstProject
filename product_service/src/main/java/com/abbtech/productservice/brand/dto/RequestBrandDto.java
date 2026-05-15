package com.abbtech.productservice.brand.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "id": 1,
          "name": "Apple",
          "description": "Apple products and accessories",
          "image": "https://example.com/images/apple.png",
          "isActive": true,
          "isDeleted": false
        }
        """)
public record RequestBrandDto(
        @Schema(example = "1")
        Long id,
        @NotBlank(message = "Brand adi bos ola bilmez")
        @Size(min = 2, max = 50, message = "Brand adi 2 ile 50 simvol arasinda olmalidir")
        @Schema(example = "Apple")
        String name,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        @Schema(example = "Apple products and accessories")
        String description,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        @Schema(example = "https://example.com/images/apple.png")
        String image,
        @Schema(example = "true")
        Boolean isActive,
        @Schema(example = "false")
        Boolean isDeleted
) {
}
