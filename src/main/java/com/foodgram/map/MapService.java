package com.foodgram.map;

import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import com.foodgram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;
    private final UserRepository userRepository;

    public void processNewMap(MapForm mapForm) {
        saveNewMap(mapForm);
    }

    private void saveNewMap(MapForm mapForm) {

//        Optional<User> user = userRepository.findByEmail(mapForm.getUserEmail());
        User user = userRepository.findByEmail2(mapForm.getUserEmail());

        Map map = Map.builder()
//                .user(user)
                .title(mapForm.getTitle())
                .content(mapForm.getContent())
                .lat(mapForm.getLat())
                .lng(mapForm.getLng())
                .build();

        mapRepository.save(map);
    }

    public List<Map> getUserMapList(String email) {

//        User user = userRepository.findByEmail(email).orElse(null);
        User user = userRepository.findByEmail2(email);

        return mapRepository.findByUser(user);
    }
}