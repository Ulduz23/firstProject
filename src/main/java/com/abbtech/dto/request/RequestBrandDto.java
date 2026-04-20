package com.abbtech.dto.request;

public class RequestBrandDto {
    private String name;
    private String description;
    private String image;
    private Boolean isActive;
    private Boolean isDeleted;

    public RequestBrandDto() {
    }

    public RequestBrandDto(String name, String description, String image, Boolean isActive, Boolean isDeleted) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

