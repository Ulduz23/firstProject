package com.abbtech.productservice.item.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestItemDto(
        Long id,
        @NotBlank(message = "Item adi bos ola bilmez")
        @Size(min = 2, max = 100, message = "Item adi 2 ile 100 simvol arasinda olmalidir")
        String name,
        @NotNull(message = "Price bos ola bilmez")
        @DecimalMin(value = "0.01", message = "Price 0-dan boyuk olmalidir")
        BigDecimal price,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        String image,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        String description,
        @NotNull(message = "Brand id bos ola bilmez")
        @Positive(message = "Brand id musbet olmalidir")
        Long brandId,
        @NotNull(message = "Category id bos ola bilmez")
        @Positive(message = "Category id musbet olmalidir")
        Long categoryId
) {
}
