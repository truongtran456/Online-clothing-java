package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.main.online_clothing_store.models.composite_primary_keys.OrderItemId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "OrderItems")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemId id;

    @Column(nullable = false, name = "price")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @Column(nullable = false, name = "discountPercent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discountPercent;

    @Column(nullable = false, name = "quantity")
    @Min(value = 0, message = "The quantity must be positive")
    private Integer quantity;

    @Column(nullable = true, name = "evaluation")
    @Min(value = 1, message = "The evaluation must be positive")
    @Max(value = 5, message = "Invalid evaluation")
    private Integer evaluation;

    @Column(nullable = true, name = "comment")
    @Size(max = 512, message = "Comment with up to 512 characters")
    private String comment;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productInventoryId")
    private ProductInventory productInventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderDetailId")
    private OrderDetail orderDetail;
}
