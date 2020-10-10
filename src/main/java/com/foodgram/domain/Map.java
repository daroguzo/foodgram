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
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private String date;

    private Double lat;

    private Double lng;

    @Builder
    public Map(User user, String title, String content, String date, Double lat, Double lng) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }
}
