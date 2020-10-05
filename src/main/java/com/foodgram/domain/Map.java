package com.foodgram.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Map {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private String date;

    private Double lat;

    private Double lng;

    @Builder
    public Map(String title, String content, String date, Double lat, Double lng) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }
}
