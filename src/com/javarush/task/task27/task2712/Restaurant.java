package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;

public class Restaurant {
    public static void main(String[] args) {
        Cook cook = new Cook("Irina");
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
    }
}
