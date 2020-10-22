package com.foodgram.map;

import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import com.foodgram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveNewMap(MapForm mapForm, String email) {

        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        Map map = Map.builder()
                .user(user)
                .title(mapForm.getTitle())
                .content(mapForm.getContent())
                .date(mapForm.getDate())
                .lat(mapForm.getLat())
                .lng(mapForm.getLng())
                .build();

        user.addMap(map);
        mapRepository.save(map);
    }

    @Transactional
    public void modifyMap(MapForm mapForm, Long id) {
        Map map = mapRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (!map.getTitle().equals(mapForm.getTitle())) {
            map.setTitle(mapForm.getTitle());
            mapRepository.save(map);
        }
    }

    @Transactional
    public List<Map> replaceMap(List<Map> mapList){

        List<Map> newMap = new ArrayList<>();

        for (Map map : mapList) {
            newMap.add(Map.builder()
                    .id(map.getId())
                    .title(map.getTitle())
                    .content(map.getContent())
                    .date(map.getDate())
                    .lat(map.getLat())
                    .lng(map.getLng())
                    .build());
        }

        return newMap;
    }

    @Transactional
    public void deleteMap(Long mapId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        Map map = mapRepository.findById(mapId).orElseThrow(IllegalArgumentException::new);
        user.deleteMap(map);
        mapRepository.delete(map);
    }
}