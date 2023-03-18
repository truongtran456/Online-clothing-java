package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 512, message = "Name with up to 512 characters")
    private String name;

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

    @Column(name = "other_info")
    @NotBlank(message = "Other info is mandatory")
    @Size(max = 512, message = "Other info with up to 512 characters")
    private String other_info;

    @Column(nullable = false, name = "import_price")
    @Min(value = 0, message = "The import price must be positive")
    private BigDecimal import_price;

    @Column(nullable = false, name = "sell_price")
    @Min(value = 0, message = "The sell price must be positive")
    private BigDecimal sell_price;

    @Column(nullable = false, name = "discount_percent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discount_percent;

    @Column(nullable = false, name = "ship_price")
    @Min(value = 0, message = "The ship price must be positive")
    private BigDecimal ship_price;

    @Column(nullable = false, name = "is_actived")
    private Boolean is_actived;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;

    @Column(nullable = false, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Wishlist> wishlist = new HashSet<Wishlist>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<ProductInventory> product_inventories = new HashSet<ProductInventory>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory product_category;
}
