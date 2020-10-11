package com.foodgram.map;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import com.foodgram.user.UserRepository;
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
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MapController {

    private final MapRepository mapRepository;
    private final MapService mapService;
    private final UserRepository userRepository;

    @GetMapping("/userMap")
    public String getUserMap(Model model, @LoginUser SessionUser user) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
            User nowUser = userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new);
            List<Map> userMap = mapRepository.findByUser(nowUser);
            List<Map> mapList = mapService.replaceMap(userMap);
            model.addAttribute("mapList", mapList);
        }

//        List<Map> mapList = mapService.getUserMapList(user.getEmail());

//        List<Map> mapList = mapRepository.findByUser(nowUser);
//        List<Map> mapList = new ArrayList<>();

//        Map map = Map.builder()
//                .content("김포공항")
//                .title("인천")
//                .lat(37.5589508457596)
//                .lng(126.802874569181)
////                .user(userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new))
//                .build();
//        Map map2 = Map.builder()
//                .content("한울마을")
//                .title("파주")
//                .lat(37.70854681503891)
//                .lng(126.74471475004465)
////                .user(userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new))
//                .build();
//
//        mapList.add(map);
//        mapList.add(map2);
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
            mapService.saveNewMap(mapForm, user.getEmail());
        }

        return "redirect:/userMap";
    }

}
