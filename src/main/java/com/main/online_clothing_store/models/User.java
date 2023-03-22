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
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
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
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(name = "email", columnNames = { "email" }),
        @UniqueConstraint(name = "telephone", columnNames = { "telephone" }) })
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private MultipartFile upload;

    @Column(unique = true)
    @Size(max = 256, message = "Email with up to 256 characters")
    @Email(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}", message = "Email is invalid")
    private String email;

    private String password;

    @Transient
    private String retypePassword;
    
    @Transient
    private String newPassword;

    @Column(nullable = true, name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @Column(nullable = true, name = "gender")
    private Boolean gender;

    @Column(unique = true, name = "telephone")
    @Pattern(regexp = "^\\d{10}$", message = "Telephone must be contain 10 digits")
    private String telephone;

    @Column(name = "apartmentNumber")
    @NotBlank(message = "Apartment number is mandatory")
    @Size(max = 256, message = "Apartment number with up to 256 characters")
    private String apartmentNumber;

    @Column(name = "street")
    @NotBlank(message = "Street is mandatory")
    @Size(max = 256, message = "Street with up to 256 characters")
    private String street;

    @Column(name = "ward")
    @NotBlank(message = "Ward is mandatory")
    @Size(max = 256, message = "Ward with up to 256 characters")
    private String ward;

    @Column(name = "district")
    @NotBlank(message = "District is mandatory")
    @Size(max = 256, message = "District with up to 256 characters")
    private String district;

    @Column(name = "city")
    @NotBlank(message = "City is mandatory")
    @Size(max = 256, message = "City with up to 256 characters")
    private String city;

    @Column(nullable = false, name = "receiveNewsletter")
    private Boolean receiveNewsletter;

    @Column(nullable = false, name = "receiveOffers")
    private Boolean receiveOffers;

    @Column(nullable = false, name = "lastLogin")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    @Column(nullable = false, name = "isLocked")
    private Boolean isLocked;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Wishlist> wishlist = new HashSet<Wishlist>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<CartItem> cartItems = new HashSet<CartItem>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
}
