package com.foodgram.map;

import com.foodgram.domain.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;

    public Map processNewMap(MapForm mapForm) {
        return saveNewMap(mapForm);
    }

    private Map saveNewMap(MapForm mapForm) {
        Map map = Map.builder()
                .title(mapForm.getTitle())
                .content(mapForm.getTitle())
                .lat(mapForm.getLat())
                .lng(mapForm.getLng())
                .build();

        return mapRepository.save(map);
    }
}