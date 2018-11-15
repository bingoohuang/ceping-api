package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class SerialNumber {
    private String sn;
    @JSONField(name = "create_time")
    private String createTime;
    private String state;
}
