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
@Table(name = "order_items")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemId id;

    @Column(nullable = false, name = "price")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @Column(nullable = false, name = "discount_percent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discount_percent;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;

    @Column(nullable = false, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("product_inventory_id")
    private ProductInventory product_inventory;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("order_detail_id")
    private OrderDetail order_detail;
}
