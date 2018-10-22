package com.devm8.stocks.monitoring;

import com.devm8.stocks.entities.Alarm;
import com.devm8.stocks.entities.AlarmStatus;
import com.devm8.stocks.entities.Stock;
import com.devm8.stocks.services.AlarmService;
import com.devm8.stocks.services.MailService;
import com.devm8.stocks.services.StocksService;
import com.devm8.stocks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class StocksMonitor {

    private StocksService stocksService;

    private AlarmService alarmService;

    private MailService mailService;

    @Autowired
    public StocksMonitor(StocksService stocksService, AlarmService alarmService, MailService mailService) {
        this.stocksService = stocksService;
        this.alarmService = alarmService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 0/" + "${stocks.polling.interval.in.minutes}" + " * 1/1 * ?")
    public void checkStocks() {
        Map<String, List<Alarm>> allActiveAlarms = groupAlarmsByStock(alarmService.findAllActiveAlarms());

        if (allActiveAlarms.isEmpty())
            return;
        allActiveAlarms.forEach((stockName, alarms) -> {
            try {
                Optional<Stock> stockOptional = stocksService.getStockDetails(stockName);
                if (stockOptional.isPresent()) {
                    Stock stock = stockOptional.get();
                    alarms.forEach(alarm -> {
                        if (shouldSendMail(stock, alarm)) {
                            String email = alarm.getUser().getEmail();
                            mailService.sendNotification(email, alarm, stock);
                            alarm.setAlarmStatus(AlarmStatus.Inactive);
                            alarmService.updateAlarm(alarm);
                        } else {
                            System.out.println("No Mail");
                        }

                    });
                } else {
                    System.out.println("Stock details are not available now.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean shouldSendMail(Stock stock, Alarm alarm) {
        Double startingPrice = alarm.getStartingPrice();
        Double currentPrice = stock.getCurrentPrice();
        stock.setCurrentDifference(((currentPrice - startingPrice) / startingPrice) * 100.0);
        System.out.println("STOCK NAME " + stock.getStockName());
        System.out.println("CURRENT PRICE " + currentPrice);
        System.out.println("STARTING PRICE " + startingPrice);
        System.out.println("DIFFERENCE " + (stock.getCurrentDifference()));
        return ((alarm.getNotificationDifference() <= 0) == (stock.getCurrentDifference() <= alarm.getNotificationDifference()));
    }

    private Map<String, List<Alarm>> groupAlarmsByStock(List<Alarm> alarms) {
        Map<String, List<Alarm>> stocksMap = new HashMap<>();
        for (Alarm alarm : alarms) {
            if (stocksMap.get(alarm.getStock()) != null) {
                stocksMap.get(alarm.getStock()).add(alarm);
            } else {
                List<Alarm> alarmList = new ArrayList<>();
                alarmList.add(alarm);
                stocksMap.put(alarm.getStock(), alarmList);
            }
        }
        return stocksMap;

    }

}
