package com.emaple.yan.atm;

import java.util.*;

public class Result implements Comparable {

    private final Map<Integer, Integer> moneyMap;
    private final int amount;

    public Result(int amount) {
        this.amount = amount;
        this.moneyMap = new TreeMap<>();
    }

    public Result(Map<Integer, Integer> needMoney, int remainMoney) {
        this.moneyMap = needMoney;
        this.amount = remainMoney;
    }

    public void putMoney(int amount, int count) {
        putVal(moneyMap, amount, count);
    }

    public Map<Integer, Integer> getMoneyMap() {
        moneyMap.entrySet().removeIf(s -> s.getValue() == 0);
        List<Map.Entry<Integer, Integer>> maps = new ArrayList<>(moneyMap.entrySet());
        maps.sort((s1,s2) -> -1 * Integer.compare(s1.getKey(), s2.getKey()));
        Map<Integer, Integer> map = new LinkedHashMap<>();
        maps.forEach(s -> putVal(map, s.getKey(), s.getValue()));

        return map;
    }

    private Integer putVal(Map<Integer, Integer> map, Integer key, Integer value) {
        return map.put(key, value);
    }

    public int getDiff() {
        return amount - getSum();
    }

    public int getSum() {
        final int[] count = {0};
        moneyMap.entrySet().forEach(s -> count[0] += s.getValue() * s.getKey());
        return count[0];
    }

    public int getCount() {
        final int[] count = {0};
        moneyMap.entrySet().forEach(s -> count[0] += s.getValue());
        return count[0];
    }

    @Override
    public int compareTo(Object o) {
        Result result = (Result) o;
        int i =   Integer.compare(this.getDiff(), result.getDiff());
        if(i != 0) return i;
        return -1 * Integer.compare(result.getCount(), this.getCount());
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "Result{" +
                "moneyMap=" + moneyMap +
                ", diff=" + getDiff() +
                "}\n";
    }
}
