package com.foodgram.map;

import lombok.Data;

@Data
public class MapForm {

    private String title;

    private String content;

    private String date = "2020-10-08";

    private Double lat;

    private Double lng;
}
