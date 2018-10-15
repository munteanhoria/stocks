package com.devm8.stocks.controllers;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.User;
import com.devm8.stocks.exceptions.ResourceNotFoundException;
import com.devm8.stocks.repositories.AlarmRepository;
import com.devm8.stocks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AlarmController {

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{userId}/alarms")
    public Page<Alarm> getAllAlarmsForUser(@PathVariable Long userId, Pageable pageable) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found!"));
        return alarmRepository.findByUserId(userId, pageable);
    }

    @GetMapping("/{userId}/alarms/{alarmId}")
    public Alarm getAlarmForUser(@PathVariable Long userId, @PathVariable Long alarmId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found!"));
        return alarmRepository.findByIdAndUserId(alarmId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Alarm " + alarmId + " not found!"));
    }

    @PostMapping("/{userId}/alarms")
    public Alarm createAlarm(@PathVariable Long userId, @Valid @RequestBody Alarm alarm) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found!"));
        alarm.setUser(user);
        return alarmRepository.save(alarm);
    }

    @PutMapping("/{userId}/alarms/{alarmId}")
    public Alarm updateAlarm(@PathVariable Long userId, @PathVariable Long alarmId, @Valid @RequestBody Alarm alarmRequest) {
        return alarmRepository.findByIdAndUserId(alarmId, userId).map(alarm -> {
            alarm.setNotificationDifference(alarmRequest.getNotificationDifference());
            alarm.setAlarmStatus(alarmRequest.getAlarmStatus());
            alarm.setStartingPrice(alarmRequest.getStartingPrice());
            alarm.setStock(alarmRequest.getStock());
            return alarmRepository.save(alarm);
        }).orElseThrow(() -> new ResourceNotFoundException("AlarmId " + alarmId + " not found"));
    }


    @DeleteMapping("/{userId}/alarms/{alarmId}")
    public ResponseEntity<?> deleteAlarm(@PathVariable Long userId, @PathVariable Long alarmId) {
        return alarmRepository.findByIdAndUserId(alarmId, userId).map(alarm -> {
            alarmRepository.delete(alarm);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AlarmId " + alarmId + " not found"));
    }
}
