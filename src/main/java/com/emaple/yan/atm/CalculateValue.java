package com.emaple.yan.atm;

import java.util.*;

public class CalculateValue {

    private final List<Map.Entry<Integer, Integer>> moneyList;
    private final int amount;
    private final List<Result> listResult;

    private boolean isStop = false;


    public CalculateValue(Map<Integer, Integer> money, int amount) {
        this.moneyList = new ArrayList<>(money.entrySet());
        Collections.reverse(moneyList);
        this.amount = amount;
        this.listResult = new ArrayList<>();
    }


    public Result calculate() {
        Result result = new Result(amount);
        calculateRecur(moneyList, amount, -1, result);
        Collections.sort(listResult);
        return result = (listResult.size() == 0) ? new Result(amount) : listResult.get(0);
    }


    private void calculateRecur(List<Map.Entry<Integer, Integer>> list, int currentAmount, int curentIndex, Result result) {
        if (list.size() == ++curentIndex) return;
        Integer denomination = list.get(curentIndex).getKey();
        int count = Integer.min(currentAmount / denomination, list.get(curentIndex).getValue());
        for (int i = count; i >= 0; i--) {
            int newValue = currentAmount - i * denomination;
            result = getResult(result, denomination, i);
            if (newValue > 0) {
                calculateRecur(list, newValue, curentIndex, result);
            } else if(newValue == 0) {
                isStop = true;
            }
            if(isStop) return;
        }


    }

    private Result getResult(Result result, Integer denomination, int count) {
        Map<Integer, Integer> map = new HashMap<>(result.getMoneyMap());
        result = new Result(map, result.getAmount());
        result.putMoney(denomination, count);
        listResult.add(result);
        return result;
    }
}
