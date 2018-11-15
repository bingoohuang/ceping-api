package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class TestReport {
    @JSONField(name = "create_time")
    private String createTime;
    private Test test;
    @JSONField(name = "report_list")
    private List<Report> reports;
}
