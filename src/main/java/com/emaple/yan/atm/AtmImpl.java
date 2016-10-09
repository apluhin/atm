package com.emaple.yan.atm;

import java.util.*;
import java.util.stream.Collector;

public class AtmImpl implements Atm {

    private final Map<Integer, Integer> money;


    public AtmImpl() {
        money = new HashMap<>();
    }

    public AtmImpl(Map<Integer, Integer> money) {
        this.money = money;
    }

    @Override
    public void putMoney(int denomination, int count) {
        System.out.println("put <" + denomination + "> " + "<" + count + ">");
        money.put(denomination, money.getOrDefault(denomination, 0) + count);
        state();
    }

    @Override
    public void get(int amount) {
        System.out.println("get <" + amount + ">");
        CalculateValue calculateValue = new CalculateValue(money, amount);
        Result result = calculateValue.calculate();
        Map<Integer, Integer> moneyMap =  result.getMoneyMap();
        withdrawMoney(moneyMap);
        printResult(result, moneyMap);

    }

    private void printResult(Result result, Map<Integer, Integer> moneyMap) {
        String resultValueString = moneyMap.entrySet().stream().collect(getCollector());
        System.out.println(resultValueString + " всего " + (result.getAmount() - result.getDiff()));
        removeReduce();
        if(result.getDiff() == 0) return;
        System.out.println("без " + result.getDiff());
    }

    private void removeReduce() {
        money.entrySet().removeIf(s -> s.getValue() == 0);
    }

    private void withdrawMoney(Map<Integer, Integer> moneyMap) {
        for (Map.Entry<Integer, Integer> entry : moneyMap.entrySet()) {
            money.put(entry.getKey(), money.get(entry.getKey()) - entry.getValue());
        }
    }

    private Collector<Map.Entry<Integer, Integer>, StringJoiner, String> getCollector() {
       return Collector.of(
                () -> new StringJoiner(","),
                (a,p) -> a.add(p.getKey() + "=" + p.getValue()),
                StringJoiner::merge,
                StringJoiner::toString);
    }

    @Override
    public void dump() {
        Set<Map.Entry<Integer, Integer>> set = new TreeSet<>((s1, s2) -> -1 * s1.getKey().compareTo(s2.getKey()));
        set.addAll(money.entrySet());
        set.forEach(s -> System.out.println(s.getKey() + " " + s.getValue()));
    }

    @Override
    public int state() {
        final int[] amount = {0};
        money.entrySet().forEach(s -> amount[0] += s.getValue() * s.getKey());
        System.out.println(amount[0]);
        return amount[0];
    }

}
