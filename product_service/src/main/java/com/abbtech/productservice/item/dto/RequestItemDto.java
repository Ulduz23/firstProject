package com.abbtech.productservice.item.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(example = """
        {
          "id": 1,
          "name": "iPhone 16",
          "price": 1299.99,
          "image": "https://example.com/images/iphone-16.png",
          "description": "Apple smartphone with 256GB storage",
          "brandId": 1,
          "categoryId": 1
        }
        """)
public record RequestItemDto(
        @Schema(example = "1")
        Long id,
        @NotBlank(message = "Item adi bos ola bilmez")
        @Size(min = 2, max = 100, message = "Item adi 2 ile 100 simvol arasinda olmalidir")
        @Schema(example = "iPhone 16")
        String name,
        @NotNull(message = "Price bos ola bilmez")
        @DecimalMin(value = "0.01", message = "Price 0-dan boyuk olmalidir")
        @Schema(example = "1299.99")
        BigDecimal price,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        @Schema(example = "https://example.com/images/iphone-16.png")
        String image,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        @Schema(example = "Apple smartphone with 256GB storage")
        String description,
        @NotNull(message = "Brand id bos ola bilmez")
        @Positive(message = "Brand id musbet olmalidir")
        @Schema(example = "1")
        Long brandId,
        @NotNull(message = "Category id bos ola bilmez")
        @Positive(message = "Category id musbet olmalidir")
        @Schema(example = "1")
        Long categoryId
) {
}
