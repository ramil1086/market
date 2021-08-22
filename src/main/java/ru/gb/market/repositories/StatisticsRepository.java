package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.models.Statistics;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository <Statistics, Long> {
    Optional<Statistics> findByService(String service);
}
