package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class SnReport {
    private String sn;
    @JSONField(name = "test_report_list")
    private List<TestReport> testReports;
}
