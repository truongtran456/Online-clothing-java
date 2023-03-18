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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 256, message = "Name with up to 256 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 512, message = "Description with up to 512 characters")
    private String description;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;

    @Column(nullable = false, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Set<RoleAdminUser> role_admin_users = new HashSet<RoleAdminUser>();
}