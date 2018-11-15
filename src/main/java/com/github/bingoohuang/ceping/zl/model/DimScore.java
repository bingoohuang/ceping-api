package com.github.bingoohuang.ceping.zl.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class DimScore {
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "Score")
    private double score;
}
