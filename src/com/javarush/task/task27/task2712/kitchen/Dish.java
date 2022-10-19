package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH,
    STEAK,
    SOUP,
    JUICE,
    WATER;
    public static String allDishesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Dish dish: Dish.values()) {
            stringBuilder.append(dish.toString() +", ");
        }
        return stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length() - 1).toString();
    }
}
