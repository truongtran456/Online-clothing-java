package com.main.online_clothing_store.controllers.user;

import java.security.Principal;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping({"/", "/my-account"})
    public String my_account(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        Optional<User> user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user.get());
        model.addAttribute("avatar", "data:image/png;base64, ".concat(user.get().getAvatar()));
        model.addAttribute("message", null);
        return "user/my_account";
    }
    @PostMapping("/update-account")
    public String update_account(Principal principal, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, HttpServletRequest request, Model model, Errors errors){
        if(principal == null){
            return "redirect:/login";
        }
        if(!user.getNewPassword().isBlank() && !userService.isValidPassword(user.getNewPassword())){
            bindingResult.rejectValue("newPassword", "error.user", "Password must be at least 8 and up to 10 characters, one uppercase letter, one lowercase letter, one number and one special character");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("message", "Can not update your account");
        }
        else{
            try {
                userService.update(user);
                return "redirect:/user/my-account";
            }
            catch(ObjectNotFoundException | IllegalArgumentException e){
                System.out.println(e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("message", e.getMessage());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("user", user);
                model.addAttribute("message", "Can not update your account");
            }
        }
        return "user/my_account";
    }
    @GetMapping("/update-account")
    public String update_account(Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        return "redirect:/user/my-account";
    }
}
