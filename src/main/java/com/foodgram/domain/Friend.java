package com.foodgram.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Friend {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Friend(String name, String email, User user) {
        this.name = name;
        this.email = email;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
