package com.devm8.stocks.services;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.Stock;

public interface MailService {

    void sendNotification(String email, Alarm alarm, Stock stock);
}
