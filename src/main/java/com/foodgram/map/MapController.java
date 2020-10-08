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
        Map map = Map.builder()
                .title("서울")
                .content("경복궁")
                .date("2019-03-20")
                .lat(37.579763)
                .lng(126.977000)
                .build();

        Map map2 = Map.builder()
                .title("서울")
                .content("창경궁")
                .date("2020-11-24")
                .lat(37.578932)
                .lng(126.994795)
                .build();

        List<Map> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);
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
        redirectAttributes.addFlashAttribute("title", mapForm.getTitle());
        redirectAttributes.addFlashAttribute("content", mapForm.getContent());
        redirectAttributes.addFlashAttribute("lat", mapForm.getLat());
        redirectAttributes.addFlashAttribute("lng", mapForm.getLng());

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

}
