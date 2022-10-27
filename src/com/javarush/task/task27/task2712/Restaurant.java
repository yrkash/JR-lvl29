package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    public static void main(String[] args) {
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tablets.add(new Tablet(i));
        }
        //Tablet tablet = new Tablet(5);
        Cook cook1 = new Cook("Irina");
        Cook cook2 = new Cook("Amigo");
        StatisticManager statisticManager = StatisticManager.getInstance();
        statisticManager.register(cook1);
        statisticManager.register(cook2);
        OrderManager orderManager = new OrderManager();
        Waiter waiter = new Waiter();
        for (int i = 0; i < 5; i++) {
            tablets.get(i).addObserver(orderManager);
        }
        //tablet.addObserver(cook);
        //cook.addObserver(waiter);
        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(1000);
            thread.interrupt();
            thread.join();
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();


    }

}
