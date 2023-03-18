package com.main.online_clothing_store.models.composite_primary_keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private Integer product_inventory_id;
    private Integer order_detail_id;
}
