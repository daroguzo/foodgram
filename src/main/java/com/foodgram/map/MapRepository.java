package com.foodgram.map;

import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findByUser(User user);
}
