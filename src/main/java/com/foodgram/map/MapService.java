package com.foodgram.map;

import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import com.foodgram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;
    private final UserRepository userRepository;

//    public void processNewMap(MapForm mapForm) {
//        saveNewMap(mapForm);
//    }

    public void saveNewMap(MapForm mapForm, String email) {

//        Optional<User> user = userRepository.findByEmail(mapForm.getUserEmail());
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        Map map = Map.builder()
                .user(user)
                .title(mapForm.getTitle())
                .content(mapForm.getContent())
                .lat(mapForm.getLat())
                .lng(mapForm.getLng())
                .build();

        mapRepository.save(map);
    }

    public List<Map> getUserMapList(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        return mapRepository.findByUser(user);
    }

    public List<Map> replaceMap(List<Map> mapList){

        List<Map> newMap = new ArrayList<>();

        for (Map map : mapList) {
            newMap.add(Map.builder()
            .title(map.getTitle())
            .content(map.getContent())
            .date(map.getDate())
            .lat(map.getLat())
            .lng(map.getLng())
            .build());
        }

        return newMap;
    }
}