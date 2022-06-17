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
    private Double nst;
    private Double gap;
    private Double dmin;
    private Double rms;
    private String net;
    private String updated;
    private String place;
    private String type;
    private Double horizontalError;
    private Double depthError;
    private Double magError;
    private Double magNst;
    private String status;
    private String locationSource;
    private String magSource;
}
