package com.blog.domain;


import java.io.Serializable;

import lombok.Data;

/**
 * @author tiankun
 * Created on 2022-06-12
 */
@Data
public class Earthquake  implements Serializable {
    private String number;
    private String volcanoName;
    private String country;
    private String region;
    private Double latitude;
    private Double longitude;
    private Double elev;
}
