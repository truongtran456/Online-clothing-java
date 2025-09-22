package com.main.online_clothing_store.models.composite_primary_keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
    @Column(name = "productInventoryId")
    private Integer productInventoryId;

    @Column(name = "orderDetailId")
    private Integer orderDetailId;
}
