package com.jf.jf_iot.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于select2下拉框和数据字典
 */
@Data
public class SelectEntityIdString implements Serializable {

    private String id;
    private String text;

}
