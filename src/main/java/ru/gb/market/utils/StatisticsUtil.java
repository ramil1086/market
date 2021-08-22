package ru.gb.market.utils;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Statistics;
import ru.gb.market.repositories.StatisticsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Aspect
@Service
@RequiredArgsConstructor
public class StatisticsUtil {

    private final StatisticsRepository statisticsRepository;

    public List<Statistics> findAll() {
        return statisticsRepository.findAll();
    }

    @Around("execution(public * ru.gb.market.services.*.*(..))")
    public Object getAllServicesDurationTime(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        Statistics statistics = new Statistics();
        String serviceName = pjp.getSignature().getDeclaringType().getSimpleName();

        if (findByService(serviceName).isPresent()) {
            statistics = statisticsRepository.findByService(serviceName).get();
            statistics.setDuration(statistics.getDuration() + duration);
        } else {
            statistics.setService(serviceName);
            statistics.setDuration(duration);
        }
        statisticsRepository.save(statistics);

        return out;

    }

    public Optional<Statistics> findByService(String service) {
        return statisticsRepository.findByService(service);
    }

}
