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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "Coupons")
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 32, message = "Name with up to 32 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 512, message = "Description with up to 512 characters")
    private String description;

    @Column(nullable = false, name = "discountPercent")
    @Min(value = 0, message = "The discount percent must be positive")
    @Max(value = 100, message = "Invalid discount percentage")
    private Integer discountPercent;

    @Column(nullable = false, name = "startDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false, name = "endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(nullable = false, name = "isActived")
    private Boolean isActived;

    @Column(nullable = false, name = "createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(nullable = false, name = "modifiedAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon")
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
}
