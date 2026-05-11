package com.abbtech.productservice.brand.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestBrandDto(
        Long id,
        @NotBlank(message = "Brand adi bos ola bilmez")
        @Size(min = 2, max = 50, message = "Brand adi 2 ile 50 simvol arasinda olmalidir")
        String name,
        @Size(max = 250, message = "Description maksimum 250 simvol ola biler")
        String description,
        @Size(max = 250, message = "Image maksimum 250 simvol ola biler")
        String image,
        Boolean isActive,
        Boolean isDeleted
) {
}
