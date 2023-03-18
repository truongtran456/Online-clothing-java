package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.main.online_clothing_store.models.composite_primary_keys.OrderItemId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(OrderItemId.class)
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer product_inventory_id;

    @Id
    private Integer order_detail_id;

    @Column(nullable = false, name = "price")
    @Min(value = 0, message = "The price must be positive")
    private BigDecimal price;

    @Column(nullable = false, name = "discount_percent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discount_percent;

    @Column(nullable = false, name = "start_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date start_date;

    @Column(nullable = false, name = "end_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date end_date;

    @Column(nullable = false, name = "is_actived")
    private Boolean is_actived;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;

    @Column(nullable = false, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;
}
