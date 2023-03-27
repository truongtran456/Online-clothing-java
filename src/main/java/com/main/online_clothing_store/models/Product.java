package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 512, message = "Name with up to 512 characters")
    private String name;

    @Column(name = "image")
    @NotBlank(message = "Image is mandatory")
    private String image;

    @Column(name = "imageHover")
    @NotBlank(message = "Image hover is mandatory")
    private String imageHover;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(nullable = false, name = "gender")
    private Boolean gender;

    @Column(name = "weight")
    @NotBlank(message = "Weight is mandatory")
    @Size(max = 32, message = "Weight with up to 32 characters")
    private String weight;

    @Column(name = "dimensions")
    @NotBlank(message = "Dimensions is mandatory")
    @Size(max = 128, message = "Dimensions with up to 128 characters")
    private String dimensions;

    @Column(name = "materials")
    @NotBlank(message = "Materials is mandatory")
    @Size(max = 512, message = "Materials with up to 512 characters")
    private String materials;

    @Column(name = "otherInfo")
    @NotBlank(message = "Other info is mandatory")
    @Size(max = 512, message = "Other info with up to 512 characters")
    private String otherInfo;

    @Column(nullable = false, name = "importPrice")
    @Min(value = 0, message = "The import price must be positive")
    private BigDecimal importPrice;

    @Column(nullable = false, name = "sellPrice")
    @Min(value = 0, message = "The sell price must be positive")
    private BigDecimal sellPrice;

    @Column(nullable = false, name = "discountPercent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discountPercent;

    @Formula(value = "coalesce(sellPrice * (1 - discountPercent / 100.0), 0)")
    private Double discountPrice;

    @Column(nullable = false, name = "shipPrice")
    @Min(value = 0, message = "The ship price must be positive")
    private BigDecimal shipPrice;

    @Column(nullable = false, name = "isActived")
    private Boolean isActived;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Wishlist> wishlist = new HashSet<Wishlist>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductInventory> productInventories = new HashSet<ProductInventory>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCategoryId", referencedColumnName = "id")
    private ProductCategory productCategory;
}
