package com.devm8.stocks.services;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.AlarmStatus;
import com.devm8.stocks.repositories.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlarmServiceImpl implements AlarmService {

    private AlarmRepository alarmRepository;

    @Autowired
    public AlarmServiceImpl(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Page<Alarm> findByUserId(Long id, Pageable pageable) {
        return alarmRepository.findByUserId(id, pageable);
    }

    @Override
    public Optional<Alarm> findByIdAndUserId(Long id, Long userId) {
        return alarmRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Alarm updateAlarm(Alarm alarm){
        return alarmRepository.save(alarm);
    }

    @Override
    public List<Alarm> findByStock(String stock) {
        return alarmRepository.findByStock(stock);
    }

    @Override
    public List<Alarm> findAllActiveAlarms() {
        return alarmRepository.findByAlarmStatus(AlarmStatus.Active);
    }


}
