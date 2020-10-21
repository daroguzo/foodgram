package com.foodgram.map;

import lombok.Data;

@Data
public class MapForm {

    private Long id;

    private String title;

    private String content;

    private String date;

    private Double lat;

    private Double lng;
}
