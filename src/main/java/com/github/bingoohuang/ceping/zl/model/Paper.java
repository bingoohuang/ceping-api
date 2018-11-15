package com.github.bingoohuang.ceping.zl.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Paper {
    @JSONField(name = "PaperId")
    private String paperId;
    @JSONField(name = "ReportUrl")
    private String reportUrl;
    @JSONField(name = "ShortReportUrl")
    private String shortReportUrl;
    @JSONField(name = "PdfReportUrl")
    private String pdfReportUrl;
    @JSONField(name = "PdfShortReportUrl")
    private String pdfShortReportUrl;
    @JSONField(name = "TotalScore")
    private double totalScore;

    @JSONField(name = "DimScores")
    private List<DimScore> dimScores;

}
