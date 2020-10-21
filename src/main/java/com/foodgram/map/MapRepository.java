package com.foodgram.map;

import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findByUser(User user);

    Optional<Map> findById(Long id);
}
