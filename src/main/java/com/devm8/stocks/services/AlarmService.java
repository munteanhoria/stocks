package com.devm8.stocks.services;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.AlarmStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlarmService {

    Page<Alarm> findByUserId(Long id, Pageable pageable);

    Optional<Alarm> findByIdAndUserId(Long id, Long userId);

    Alarm updateAlarm(Alarm alarm);

    List<Alarm> findByStock(String stock);

    List<Alarm> findAllActiveAlarms();
}
