package com.foodgram.domain;

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

    private String Content;

    private String lat;

    private String lng;
}
