package com.main.online_clothing_store.models.composite_primary_keys;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class WishlistId implements Serializable {
    private Integer product_id;
    private Integer user_id;
}
