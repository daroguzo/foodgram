package com.foodgram.map;

import com.foodgram.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {

}
