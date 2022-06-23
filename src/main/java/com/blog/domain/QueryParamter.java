package com.blog.domain;


import java.io.Serializable;

import lombok.Data;

/**
 * @author tiankun
 * Created on 2022-06-12
 */
@Data
public class QueryParamter{
    private int minSeq;
    private int maxSeq;
}
