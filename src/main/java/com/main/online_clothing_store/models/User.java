package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    @Size(max = 256, message = "First name with up to 256 characters")
    private String first_name;

    @Column(name = "last_name")
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 256, message = "Last name with up to 256 characters")
    private String last_name;

    @Column(nullable = true, name = "avatar")
    private String avatar;

    @Column(unique = true)
    @Size(max = 256, message = "Email with up to 256 characters")
    @Email(regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}", message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$", message = "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    
    @Column(nullable = true, name = "birthdate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;

    @Column(nullable = true, name = "gender")
    private Boolean gender;

    @Column(unique = true, name = "telephone")
    @Pattern(regexp = "^\\d{10}$", message = "Telephone must be contain 10 digits")
    private String telephone;

    @Column(name = "apartment_number")
    @NotBlank(message = "Apartment number is mandatory")
    @Size(max = 256, message = "Apartment number with up to 256 characters")
    private String apartment_number;

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

    @Column(nullable = false, name = "receive_newsletter")
    private Boolean receive_newsletter;

    @Column(nullable = false, name = "receive_offers")
    private Boolean receive_offers;

    @Column(nullable = false, name = "last_login")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date last_login;

    @Column(nullable = false, name = "is_locked")
    private Boolean is_locked;

    @Column(nullable = true, name = "created_at	")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at	;

    @Column(nullable = true, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;
}
