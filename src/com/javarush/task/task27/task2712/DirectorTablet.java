package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<Date, Double> map = statisticManager.calcTotalAdvertisementAmountPerDay();
        Double totalAmount = 0d;
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date,Double> entry: map.entrySet()) {
            totalAmount += entry.getValue();
            String message = String.format("%s - %.2f", df.format(entry.getKey()), entry.getValue());
            ConsoleHelper.writeMessage(message);
        }
        String total = String.format("Total - %.2f", totalAmount);
        ConsoleHelper.writeMessage(total);
    }

    public void printCookWorkloading() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map <Date, Map<String, Integer>> map = statisticManager.calcCookingTimePerDay();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Map<String, Integer>> entry: map.entrySet()) {
            ConsoleHelper.writeMessage(df.format(entry.getKey()));
            for (Map.Entry<String, Integer> subEntry: entry.getValue().entrySet()) {
                String message = String.format("%s - %d min", subEntry.getKey(), subEntry.getValue());
                ConsoleHelper.writeMessage(message);
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}
