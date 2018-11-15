package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Test {
    private String name;
    private String cent;
    private String type;
    @JSONField(name = "dimension_list")
    private List<ResultDimension> dimensions;
}
