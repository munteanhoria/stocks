package com.devm8.stocks.services;

import com.devm8.stocks.entities.Alarm;

public interface MailService {

    void sendNotification(String email, Alarm alarm);
}
