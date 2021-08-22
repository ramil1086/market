package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.dto.StatisticsDto;
import ru.gb.market.utils.StatisticsUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticsController {
    private final StatisticsUtil statisticsUtil;

    @GetMapping()
    public List<StatisticsDto> getServiceStatistics() {
        return statisticsUtil.findAll().stream().map(StatisticsDto::new).collect(Collectors.toList());
    }



}
