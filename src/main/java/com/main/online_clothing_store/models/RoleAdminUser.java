package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.main.online_clothing_store.models.composite_primary_keys.RoleAdminUserId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
@Table(name = "RoleAdminUsers")
public class RoleAdminUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleAdminUserId id;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt	;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("adminUserId")
    private AdminUser adminUser;
}
