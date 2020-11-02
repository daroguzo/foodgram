package com.foodgram.user;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import com.foodgram.domain.Friend;
import com.foodgram.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

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

    @GetMapping("/friendList")
    public String getFriendList(Model model, @LoginUser SessionUser user) {
        // login user
        if(user != null) {
            model.addAttribute("userName", user.getName());
            User nowUser = userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new);
            List<Friend> friendList = friendRepository.findByUser(nowUser);
            model.addAttribute("friendList", friendList);
            model.addAttribute(new FriendForm());
            if (friendList.size() == 0) {
                model.addAttribute("errorMessage", "등록 친구가 없습니다!");
            }
        }
        return "user/friendList";
    }

    @PostMapping("/deleteFriend")
    public String deleteFriend(Model model, @LoginUser SessionUser user, @RequestParam Long id) {
        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
            userService.deleteFriend(id, user.getEmail());
        }

        return "redirect:/friendList";
    }

    @GetMapping("/findFriend")
    public String getFindFriend(Model model, @LoginUser SessionUser user) {

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "user/findFriend";
    }

    @PostMapping("/findFriend")
    public String postFindFriend(Model model, @LoginUser SessionUser user, @RequestParam String email,
                                 RedirectAttributes redirectAttributes) {

        if(user != null){
            model.addAttribute("userName", user.getName());
            if (userService.isExistUser(email)) {
                Friend friend = userService.findFriend(email);
                User nowUser = userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new);
                nowUser.addFried(friend);
                redirectAttributes.addFlashAttribute("message", "친구 추가 완료!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "해당 이메일을 사용하는 사용자가 없습니다!");
            }
        }


        return "redirect:/findFriend";
    }
}
