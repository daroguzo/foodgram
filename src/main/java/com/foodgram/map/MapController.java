package com.foodgram.map;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import com.foodgram.domain.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MapController {

    private final MapRepository mapRepository;
    private final MapService mapService;

    @GetMapping("/userMap")
    public String getUserMap(Model model, @LoginUser SessionUser user) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute("message", "hello");

        List<Map> mapList = new ArrayList<>();
        model.addAttribute("mapList", mapList);
        return "map/userMap";
    }

    @GetMapping("/findMap")
    public String getFindMap(Model model, @LoginUser SessionUser user) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute(new MapForm());

        return "map/findMap";
    }

    @PostMapping("/findMap")
    public String postFindMap(Model model, @LoginUser SessionUser user,
                              RedirectAttributes redirectAttributes, MapForm mapForm) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        System.out.println(mapForm.getTitle());

//        redirectAttributes.addFlashAttribute(mapForm);
        redirectAttributes.addAttribute("title", mapForm.getTitle());
        redirectAttributes.addAttribute("content", mapForm.getContent());
        redirectAttributes.addAttribute("lat", mapForm.getLat());
        redirectAttributes.addAttribute("lng", mapForm.getLng());

        return "redirect:/mapForm";
    }

    @GetMapping("/mapForm")
    public String getMapForm(Model model, @LoginUser SessionUser user,
                             @RequestParam String title, @RequestParam String content,
                             @RequestParam Double lat, @RequestParam Double lng) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute(new MapForm());
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("lat", lat);
        model.addAttribute("lng", lng);

        return "map/mapForm";
    }

    @PostMapping("/mapForm")
    public String postMapForm(Model model, @LoginUser SessionUser user,
                              MapForm mapForm) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        mapService.processNewMap(mapForm);

        return "redirect:/userMap";
    }

}
