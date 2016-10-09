package com.emaple.yan.atm;

public interface Atm {

    void putMoney(int denomination, int count);

    void get(int amount);

    void dump();

    int state();



}
