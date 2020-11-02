package com.foodgram.user;

import com.foodgram.domain.Friend;
import com.foodgram.domain.Map;
import com.foodgram.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public void deleteFriend(Long id, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        Friend friend = friendRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.deleteFriend(friend);
        friendRepository.delete(friend);
    }

    @Transactional
    public Friend findFriend(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        Friend friend = Friend.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
        friendRepository.save(friend);
        return friend;
    }

    @Transactional
    public boolean isExistUser(String email) {
        return userRepository.existsByEmail(email);
    }
}
