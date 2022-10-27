package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{

    private List<Tablet> tablets = new ArrayList<>();
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(interval);
                int tabletCount = tablets.size();
                tablets.get((int) (Math.random() * (tabletCount - 1))).createTestOrder();
            } catch (InterruptedException e) {
                return;
            }

        }

    }
}
