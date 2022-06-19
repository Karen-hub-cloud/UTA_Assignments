package com.blog.domain;


import lombok.Data;

/**
 * @author tiankun
 * Created on 2022-06-12
 */
@Data
public class Earthquake {
    private String id;
    private String time;
    private Double latitude;
    private Double longitude;
    private Double depth;
    private Double mag;
    private String magType;
    private String net;
    private String place;
    private Double horizontalError;
    private Double magError;
    private Double magNst;
    private String locationSource;
}
