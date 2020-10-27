package com.foodgram.user;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/profile")
    public String getProfile(Model model, @LoginUser SessionUser user) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userPicture", user.getPicture());
        }

        return "user/profile";
    }
}
