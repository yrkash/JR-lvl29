package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<Date, Double> map = statisticManager.calcTotalAdvertisementAmountPerDay();
        Double totalAmount = 0d;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        for (Map.Entry<Date,Double> entry: map.entrySet()) {
            totalAmount += entry.getValue();
            String message = String.format("%s - %.2d", df.format(entry.getKey()), entry.getValue());
            ConsoleHelper.writeMessage(message);
        }
        String total = String.format("Total - %.2d", totalAmount);
        ConsoleHelper.writeMessage(total);
    }

    public void printCookWorkloading() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map <Date, Map<String, Integer>> map = statisticManager.calcCookingTimePerDay();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        for (Map.Entry<Date, Map<String, Integer>> entry: map.entrySet()) {
            ConsoleHelper.writeMessage(df.format(entry.getKey()));
            for (Map.Entry<String, Integer> subEntry: entry.getValue().entrySet()) {
                String message = String.format("%s - %d min", subEntry.getKey(), entry.getValue());
                ConsoleHelper.writeMessage(message);
            }
        }
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
