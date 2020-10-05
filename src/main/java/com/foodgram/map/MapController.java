package com.foodgram.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MapController {

    private final MapRepository mapRepository;
    private final MapService mapService;

    @GetMapping("/map")
    public String getMap(Model model) {

        model.addAttribute("message", "hello");
        return "map";
    }

    @GetMapping("/findMap")
    public String getFindMap() {

        return "findMap";
    }

}
