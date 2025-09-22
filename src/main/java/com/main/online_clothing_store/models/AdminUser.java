package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "AdminUsers")
public class AdminUser implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    @NotBlank(message = "First name is mandatory")
    @Size(max = 256, message = "First name with up to 256 characters")
    private String firstName;

    @Column(name = "lastName")
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 256, message = "Last name with up to 256 characters")
    private String lastName;

    @Column(nullable = true, name = "avatar")
    private String avatar;

    @Transient
    private MultipartFile uploadAvatar;

    @Column(unique = true)
    @Size(max = 256, message = "Email with up to 256 characters")
    @Email(regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}", message = "Email is invalid")
    private String email;

    private String password;

    @Transient
    private String retypePassword;

    @Transient
    private String newPassword;

    @Column(unique = true, name = "telephone")
    @Pattern(regexp = "^\\d{10}$", message = "Telephone must be contain 10 digits")
    private String telephone;

    @Column(nullable = false, name = "lastLogin")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    @Column(nullable = false, name = "isLocked")
    private Boolean isLocked;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt	;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adminUser")
    private Set<RoleAdminUser> roleAdminUsers = new HashSet<RoleAdminUser>();

}
