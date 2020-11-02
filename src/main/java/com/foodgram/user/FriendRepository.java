package com.foodgram.user;

import com.foodgram.domain.Friend;
import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUser(User user);

    Optional<Friend> findById(Long id);

    boolean existsByEmail(String email);

}
