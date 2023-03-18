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
@Table(name = "role_admin_users")
public class RoleAdminUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleAdminUserId id;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at	;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("admin_user_id")
    private AdminUser admin_user;
}
