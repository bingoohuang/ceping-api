package com.github.bingoohuang.ceping.zl.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Score {
    @JSONField(name = "JobId")
    private String jobId;
    @JSONField(name = "Persons")
    private List<Person> persons;
}
