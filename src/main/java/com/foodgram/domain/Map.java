package com.foodgram.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Map {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String title;

    private String content;

    private String date;

    private Double lat;

    private Double lng;

    @Builder
    public Map(User user, Long id,  String title, String content, String date, Double lat, Double lng) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
