package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class TesteeSn {
    private String email;
    @JSONField(name = "sn_list")
    private List<SerialNumber> sns;
}
