package com.main.online_clothing_store.models;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "order_details")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, name = "subtotal")
    @Min(value = 0, message = "The subtotal must be positive")
    private BigDecimal subtotal;

    @Column(nullable = false, name = "total")
    @Min(value = 0, message = "The total must be positive")
    private BigDecimal total;

    @Column(name = "note")
    @NotBlank(message = "Note is mandatory")
    @Size(max = 512, message = "Note with up to 512 characters")
    private String note;

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

    @Column(name = "telephone")
    @NotBlank(message = "Telephone number is mandatory")
    @Size(max = 10, message = "Telephone number with up to 10 characters")
    private String telephone;

    @Column(nullable = false, name = "status")
    private Integer status;

    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created_at;

    @Column(nullable = false, name = "modified_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date modified_at;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order_detail")
    private Set<OrderItem> order_items = new HashSet<OrderItem>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
}
