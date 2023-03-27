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
public class RoleAdminUserId implements Serializable {
    @Column(name = "adminUserId")
    private Integer adminUserId;

    @Column(name = "roleId")
    private Integer roleId;
}
