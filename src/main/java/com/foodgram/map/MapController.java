package com.foodgram.map;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import com.foodgram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        // now date
        LocalDateTime now = LocalDateTime.now();
        String yyyyMMdd = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        model.addAttribute(new MapForm());
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("date", yyyyMMdd);
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

    @GetMapping("/modifyForm/{mapId}")
    public String getModifyForm(Model model, @LoginUser SessionUser user,
                             @PathVariable Long mapId) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        Map map = mapRepository.findById(mapId).orElseThrow(IllegalArgumentException::new);

        model.addAttribute(new MapForm());
        model.addAttribute("mapId", mapId);
        model.addAttribute("title", map.getTitle());
        model.addAttribute("content", map.getContent());
        model.addAttribute("date", map.getDate());
        model.addAttribute("lat", map.getLat());
        model.addAttribute("lng", map.getLng());

        return "map/modifyForm";
    }

    @PostMapping("/modifyForm")
    public String postModifyForm(Model model, @LoginUser SessionUser user,
                              MapForm mapForm) {

        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
            mapService.modifyMap(mapForm, mapForm.getId());
        }

        return "redirect:/userMap";
    }

    @PostMapping("/modifyForm/delete")
    public String deleteMap(Model model, @LoginUser SessionUser user,
                            MapForm mapForm) {
        // login user
        if(user != null){
            model.addAttribute("userName", user.getName());
            mapService.deleteMap(mapForm.getId(), user.getEmail());
        }

        return "redirect:/userMap";
    }

}
