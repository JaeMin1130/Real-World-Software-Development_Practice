package practice.chapter3.service.exporter;

import practice.chapter3.domain.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}