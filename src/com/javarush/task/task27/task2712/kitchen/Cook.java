package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable {
    private final String name;

    public Cook(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        StatisticManager statisticManager = StatisticManager.getInstance();

        ConsoleHelper.writeMessage("Start cooking - " + order);
//        this.setChanged();
//        this.notifyObservers(order);
        CookedOrderEventDataRow cookedOrderEventDataRow = new CookedOrderEventDataRow(order.getTablet().toString(),name, order.getTotalCookingTime() * 60,order.getDishes());
        statisticManager.register(cookedOrderEventDataRow);

    }
}
