package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private List<Advertisement> bestChoice = new ArrayList<>();
    private long bestAmount;
    private int bestDuration;
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        StatisticManager statisticManager = StatisticManager.getInstance();

        VideoSelectedEventDataRow videoSelectedEventDataRow = new VideoSelectedEventDataRow(bestChoice, bestAmount, bestDuration);
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();

        List<Advertisement> advertisements = storage.list().stream()
                        .filter(advertisement -> advertisement.getHits() > 0)
                                . collect(Collectors.toList());
        recursionSearch(advertisements);

        statisticManager.register(videoSelectedEventDataRow);

        // Сортировка по условию 2.4 из задания 9
        bestChoice
                .sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
                        .thenComparingInt(Advertisement::getDuration)
                        .reversed());

        // Вывод на экран + Декремент поля hits у Advertisement
        bestChoice.forEach(advertisement -> {
            ConsoleHelper.writeMessage(advertisement.toString());
            advertisement.revalidate();
        });

        /*
        // Сортировка по условию. !!!!!!!!!!!!!! На всякий случай оставлю
        bestChoice.stream()
                .sorted(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying).reversed()
                        .thenComparing(a -> a.getAmountPerOneDisplaying() / a.getDuration()))
                .forEach(a-> ConsoleHelper.writeMessage(a.toString()));
        */
    }
    // Последующие 4 функции - Рекурсивный алгоритм портфеля
    private int calcFullDuration(List<Advertisement> list) {
        return list.stream().mapToInt(Advertisement::getDuration).sum();
    }
    private long calcFullAmountForDisplayingAdsList(List<Advertisement> list) {
        return list.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
    }
    private void checkAdsList(List <Advertisement> list) {
        int currentDuration = calcFullDuration(list);
        long currentAmount = calcFullAmountForDisplayingAdsList(list);

        if (bestChoice == null && calcFullDuration(list) <= timeSeconds) {
            bestChoice = list;
            bestAmount = currentAmount;
            bestDuration = currentDuration;
        } else {
            if (currentDuration <= timeSeconds) {

                if (currentAmount > bestAmount) {
                    bestChoice = list;
                    bestAmount = currentAmount;
                    bestDuration = currentDuration;
                }

                if (currentAmount == bestAmount) {
                    if (currentDuration > bestDuration) {
                        bestChoice = list;
                        bestAmount = currentAmount;
                        bestDuration = currentDuration;
                    }
                    if (currentDuration == bestDuration && list.size() < bestChoice.size()) {
                        bestChoice = list;
                        bestAmount = currentAmount;
                        bestDuration = currentDuration;
                    }
                }
            }
        }
    }
    public void recursionSearch(List<Advertisement> list) {
        if (list.size() > 0) checkAdsList(list);

        for (int i = 0; i < list.size(); i++) {
            List<Advertisement> newList = new ArrayList<>(list);
            newList.remove(i);
            recursionSearch(newList);
        }
    }

}
