package com.github.bingoohuang.ceping.zl.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Person {
    @JSONField(name = "PersonId")
    private String personId;
    @JSONField(name = "Papers")
    private List<Paper> papers;
    @JSONField(name = "Extend")
    private String extend;
}
