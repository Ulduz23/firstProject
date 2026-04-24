package com.abbtech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(length = 250)
    private String image;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Column
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Item> items;

    @ManyToMany
    @JoinTable(name = "item"
            , joinColumns = @JoinColumn(name = "brand_id", table = "brand", foreignKey = @ForeignKey(name = "fk_brand_category"))
            , inverseJoinColumns = @JoinColumn(name = "category_id", table = "category", foreignKey = @ForeignKey(name = "fk_category_brand")))
    private List<Category> categories;
}
