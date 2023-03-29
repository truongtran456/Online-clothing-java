package com.main.online_clothing_store.controllers.admin;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.main.online_clothing_store.models.Coupon;
import com.main.online_clothing_store.models.Product;
import com.main.online_clothing_store.models.ProductInventory;
import com.main.online_clothing_store.services.CouponService;
import com.main.online_clothing_store.services.ProductInventoryService;
import com.main.online_clothing_store.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/coupon")
@SessionAttributes("coupon")
public class CouponController {
    CouponService couponService;
    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @GetMapping({ "", "/", "/list" })
    public String list(Model model) {
        List<Coupon> coupons = couponService.getAllCoupons();
        model.addAttribute("coupons", coupons);
        return "admin/coupon/list";
    }
    @GetMapping("/add")
    public String add(Principal principal, Model model) {
        if (principal == null) {
            return "authentication/login";
        }
        model.addAttribute("coupon", new Coupon());
        return "admin/coupon/add";
    }
    @PostMapping("/add")
    public String add(Principal principal, @ModelAttribute("coupon") @Valid Coupon coupon,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("coupon", coupon);
            model.addAttribute("message", "Can not create coupon");
        } else {
            try {
                couponService.create(coupon);
                return "redirect:/admin/coupon";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                model.addAttribute("coupon", coupon);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("coupon", coupon);
                model.addAttribute("message", "Can not create coupon");
            }
        }
        return "admin/coupon/add";
    }
    @GetMapping("/edit/{id}")
    public String edit(Principal principal, @PathVariable("id") Integer id, Model model) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            try {
                Optional<Coupon> coupon = couponService.findById(id);
                model.addAttribute("coupon", coupon.get());
                return "admin/coupon/edit";
            } catch (Exception e) {
                return "redirect:/admin/coupon";
            }
        }
    }
    @PostMapping("/edit")
    public String edit(Principal principal, @ModelAttribute("coupon") @Valid Coupon coupon,
            BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("coupon", coupon);
            model.addAttribute("message", "Can not update coupon:" + bindingResult.getAllErrors());
        } else {
            try {
                couponService.update(coupon);
                return "redirect:/admin/coupon";
            } catch (ObjectNotFoundException | IllegalArgumentException e) {
                model.addAttribute("coupon", coupon);
                model.addAttribute("message", e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("coupon", coupon);
                model.addAttribute("message", "Can not update coupon: " + e.getMessage());
            }
        }
        return "/admin/coupon/edit";
    }
}
