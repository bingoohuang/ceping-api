package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Activity {
    private String id;
    private String name;
    private String description;
    private int state;
    @JSONField(name = "create_date")
    private String createDate;
}
