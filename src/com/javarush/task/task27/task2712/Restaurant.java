package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Comparator;
import java.util.List;

public class Restaurant {
    public static void main(String[] args) {
        Cook cook = new Cook("Irina");
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        cook.addObserver(new Waiter());
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();




    }

}
