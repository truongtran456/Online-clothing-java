package com.main.online_clothing_store.models;

import java.io.Serializable;
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
import jakarta.persistence.UniqueConstraint;
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
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productInventories", uniqueConstraints = { @UniqueConstraint(name = "product",columnNames = { "productId", "Size", "Color" }) })
public class ProductInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "size")
    @NotBlank(message = "Size is mandatory")
    @Size(max = 32, message = "Size with up to 32 characters")
    private String size;

    @Column(name = "color")
    @NotBlank(message = "Color is mandatory")
    @Size(max = 32, message = "Color with up to 32 characters")
    private String color;

    @Column(nullable = false, name = "quantity")
    @Min(value = 0, message = "The quantity must be positive")
    private Integer quantity;
    
    @Column(nullable = false, name = "isActived")
    private Boolean isActived;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt	;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productInventory")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productInventory")
    private Set<CartItem> cartItems = new HashSet<CartItem>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
}
