package com.main.online_clothing_store.controllers.user;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.online_clothing_store.models.Cart;
import com.main.online_clothing_store.models.Coupon;
import com.main.online_clothing_store.models.CartItem;
import com.main.online_clothing_store.models.OrderDetail;
import com.main.online_clothing_store.models.Payment;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.models.Wish;
import com.main.online_clothing_store.services.CartItemService;
import com.main.online_clothing_store.services.OrderDetailService;
import com.main.online_clothing_store.services.OrderItemService;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.json.JSONObject;

@Controller
@RequestMapping("/user/cart")
@SessionAttributes("orderDetail")
public class CartController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/add-to-cart")
    public String addToCart(Principal principal, @RequestParam("id") Integer id, @RequestParam("size") String size,
            @RequestParam("color") String color, @RequestParam("quantity") Integer quantity,
            final RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.findByEmail(principal.getName());
        try {
            if (cartItemService.addToCart(user.get(), id, size, color, quantity)) {
                redirectAttributes.addFlashAttribute("message", "Added to cart");
            } else {
                redirectAttributes.addFlashAttribute("message", "Quantity must be greater than 1");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products/single-product/" + id;
    }

    @GetMapping(value = "/view-cart", produces = "application/json")
    @ResponseBody
    public List<Cart> viewCart(Principal principal) {
        Optional<User> user = userService.findByEmail(principal.getName());
        return cartItemService.viewCart(user.get().getId());
    }

    @PostMapping(value = "/remove-cart-item", produces = "application/json")
    @ResponseBody
    public String removeCartItem(Principal principal, Integer id) {
        Optional<User> user = userService.findByEmail(principal.getName());
        JSONObject result = new JSONObject();
        if (cartItemService.removeCartItem(user.get().getId(), id)) {
            result.put("message", "success");
            result.put("status", 200);
        } else {
            result.put("message", "failed");
            result.put("status", 400);
        }
        return result.toString();
    }

    @GetMapping(value = "/view-wishlist", produces = "application/json")
    @ResponseBody
    public List<Wish> viewWishlist(Principal principal) {
        Optional<User> user = userService.findByEmail(principal.getName());
        return cartItemService.viewWishlist(user.get().getId());
    }

    @PostMapping(value = "/remove-wishlist-item", produces = "application/json")
    @ResponseBody
    public String removeWishlistItem(Principal principal, Integer id) {
        Optional<User> user = userService.findByEmail(principal.getName());
        JSONObject result = new JSONObject();
        if (cartItemService.removeWishlistItem(user.get().getId(), id)) {
            result.put("message", "success");
            result.put("status", 200);
        } else {
            result.put("message", "failed");
            result.put("status", 400);
        }
        return result.toString();
    }

    @PostMapping(value = "/add-to-wishlist", produces = "application/json")
    @ResponseBody
    public String addToWishlist(Principal principal, Integer id) {
        Optional<User> user = userService.findByEmail(principal.getName());
        JSONObject result = new JSONObject();
        if (cartItemService.addToWishlist(user.get(), id)) {
            result.put("message", "success");
            result.put("status", 200);
        } else {
            result.put("message", "failed");
            result.put("status", 400);
        }
        return result.toString();
    }

    @GetMapping("/checkout")
    public String checkout(Principal principal, Model model, @ModelAttribute("coupon") Coupon coupon, @ModelAttribute("error") String error) {
        if(principal == null) {
            return "redirect:/login";
        }
        Optional<User> user = userService.findByEmail(principal.getName());
        List<Cart> cartItems = cartItemService.viewCart(user.get().getId());
        Double subtotal = cartItemService.calSubtotal(cartItems);
        List<Payment> payments = cartItemService.getPayments();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setTelephone(user.get().getTelephone());
        orderDetail.setApartmentNumber(user.get().getApartmentNumber());
        orderDetail.setStreet(user.get().getStreet());
        orderDetail.setWard(user.get().getWard());
        orderDetail.setDistrict(user.get().getDistrict());
        orderDetail.setCity(user.get().getCity());
        orderDetail.setUser(user.get());
        orderDetail.setPayment(payments.get(0));
        orderDetail.setSubtotal(new BigDecimal(subtotal));
        orderDetail.setStatus(1);
        orderDetail.setCreatedAt(new Date());
        orderDetail.setModifiedAt(new Date());
        if(coupon == null){
            orderDetail.setTotal(new BigDecimal(subtotal));
            model.addAttribute("couponMessage", "Coupon is invalid");
            model.addAttribute("coupon", null);
        }
        else{
            if(coupon.getName() != null) {
                orderDetail.setCoupon(coupon);
                orderDetail.setTotal(new BigDecimal(subtotal * (1 - coupon.getDiscountPercent()/100.0)));
                model.addAttribute("coupon", coupon.getName());
            }
            else{
                orderDetail.setTotal(new BigDecimal(subtotal));
                model.addAttribute("coupon", null);
            }
            model.addAttribute("couponMessage", null);
        }
        model.addAttribute("user", user.get());
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("subtotal", orderDetail.getSubtotal());
        model.addAttribute("total", orderDetail.getTotal());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("payments", payments);
        model.addAttribute("error", error);
        return "user/checkout";
    }

    @PostMapping("/apply-coupon")
    public String applyCoupon(Principal principal, @RequestParam("couponName") String couponName, final RedirectAttributes redirectAttributes) {
        try{
            Optional<Coupon> coupon = cartItemService.findCouponByName(couponName);
            redirectAttributes.addFlashAttribute("coupon", coupon.get());
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("coupon", null);
        }
        return "redirect:/user/cart/checkout";
    }

    @PostMapping("/handle-checkout")
    public String handleCheckout(Principal principal, @ModelAttribute("orderDetail") @Valid OrderDetail orderDetail, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors, final RedirectAttributes redirectAttributes) {
        if(principal == null) {
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderDetail", orderDetail);
            redirectAttributes.addFlashAttribute("error", "Can not checkout. Please recheck your information.");
            redirectAttributes.addFlashAttribute("coupon", new Coupon());
            return "redirect:/user/cart/checkout";
        }
        if(orderDetail.getPaymentId() != orderDetail.getPayment().getId()){
            Optional<Payment> payment = cartItemService.findPaymentById(orderDetail.getPaymentId());
            if(payment.isPresent()){
                orderDetail.setPayment(payment.get());
            }
            else{
                redirectAttributes.addFlashAttribute("orderDetail", orderDetail);
                redirectAttributes.addFlashAttribute("error", "Can not checkout. Please recheck your information.");
                redirectAttributes.addFlashAttribute("coupon", new Coupon());
                return "redirect:/user/cart/checkout";
            }
        }
        if(cartItemService.isValidCart(orderDetail.getUser().getId()) && orderDetailService.checkout(orderDetail)){
            return "user/order_completed";
        }
        else{
            redirectAttributes.addFlashAttribute("orderDetail", orderDetail);
            redirectAttributes.addFlashAttribute("error", "Can not checkout. Please recheck your information or quantity of product in cart.");
            redirectAttributes.addFlashAttribute("coupon", new Coupon());
            return "redirect:/user/cart/checkout";
        }
    }
}
