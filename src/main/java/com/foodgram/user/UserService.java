package com.foodgram.user;

import com.foodgram.domain.Friend;
import com.foodgram.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public boolean isDuplicatedFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        List<Friend> friendList = friendRepository.findByUser(user);
        for (Friend f :
                friendList) {
            if (f.getEmail().equals(friendEmail)) {
                return true;
            }
        }
        return false;
    }

    public boolean cantAddMe(String userEmail, String friendEmail) {
        return userEmail.equals(friendEmail);
    }

    @Transactional
    public boolean isExistUser(String email) {
        return userRepository.existsByEmail(email);
    }
}
