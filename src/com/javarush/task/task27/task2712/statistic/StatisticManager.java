package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType: EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put (EventDataRow data) {
            /*
            EventType type = data.getType();
            if (type == EventType.COOKED_ORDER) {
                List<EventDataRow> list = storage.get(EventType.COOKED_ORDER);
                list.add(data);
                storage.replace(EventType.COOKED_ORDER,list);
            }
             */
            if (storage.containsKey(data.getType())) { storage.get(data.getType()).add(data);}
        }

    }


    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

}
