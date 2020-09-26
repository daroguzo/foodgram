package com.foodgram.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MapController {

    @GetMapping("/map")
    public String getMap() {

        return "map";
    }

}
