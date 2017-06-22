package com.example.demoMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by Tanmoy Mandal on 12/27/2016.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("body","index");
        return "layouts/theme";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model){
        model.addAttribute("userEntity", new UserEntity());
//        model.addAttribute("body","add_editUser");
//        return "layouts/theme";
        return "add_editUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute UserEntity userEntity, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("Has Errors");
//            model.addAttribute("userEntity", new UserEntity());
//            model.addAttribute("body","add_editUser");
//            return "layouts/theme";
            bindingResult.rejectValue("",null,"should be unique");
            return "add_editUser";
        }else{
            System.out.println("Has No Errors");
            System.out.println(userService.save(userEntity));
            return "redirect:/";
        }
    }
}
