package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Tablet tablet = (Tablet) o;
        Order order = (Order) arg;
        CookedOrderEventDataRow cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet.toString(),name, order.getTotalCookingTime(),order.getDishes());
        statisticManager.register(cookedOrderEventDataRow);
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        this.setChanged();
        this.notifyObservers(arg);
    }
}
