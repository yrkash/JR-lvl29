package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {

    private final int number;

    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            Order order = new Order(this);
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            if (!order.isEmpty()) {
                this.setChanged();
                this.notifyObservers(order);
                return order;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
