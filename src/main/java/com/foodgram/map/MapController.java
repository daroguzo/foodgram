package com.foodgram.map;

import com.foodgram.config.auth.LoginUser;
import com.foodgram.config.dto.SessionUser;
import com.foodgram.domain.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        return "map/findMap";
    }

}
