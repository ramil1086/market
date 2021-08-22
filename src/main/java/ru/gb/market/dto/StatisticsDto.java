package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.models.Statistics;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class StatisticsDto {
    private String serviceName;
    private Long durationTime;

    public StatisticsDto(Statistics statistics) {
        this.serviceName = statistics.getService();
        this.durationTime = statistics.getDuration();
    }
}
