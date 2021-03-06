package com.devm8.stocks.repositories;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.AlarmStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    Page<Alarm> findByUserId(Long id, Pageable pageable);

    Optional<Alarm> findByIdAndUserId(Long id, Long userId);

    List<Alarm> findByStock(String stock);

    List<Alarm> findByAlarmStatus(AlarmStatus status);
}
