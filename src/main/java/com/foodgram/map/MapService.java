package com.foodgram.map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;
}
