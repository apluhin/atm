package com.emaple.yan.atm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class CalculateValueTest {

    Atm atm;
    Map<Integer, Integer> map;
    int amount;

    @Before
    public void setUp() throws Exception {

        map = new TreeMap<>();
        map.put(3, 2);
        map.put(1,1);
        map.put(5,1);
        atm = new AtmImpl();
        atm = new AtmImpl(map);
    }

    @Test
    public void testCalculate() throws Exception {
        int state = atm.state();
        atm.get(7);
        int state1 = atm.state();
        Assert.assertEquals(state, state1 + 7);
        atm.putMoney(1, 1000);
        atm.putMoney(1, 1000);
        atm.putMoney(1, 1000);
        Assert.assertEquals(state1 + 3000, atm.state());
        atm.get(3000);
        atm.get(1000);
        atm.get(125);
    }

    @Test
    public void testHungry() throws Exception {
        map = new TreeMap<>();
        map.put(2, 3);
        map.put(3, 3);
        map.put(1, 3);
        map.put(500, 3);
        map.put(5000, 3);
        map.put(1000, 3);
        atm = new AtmImpl(map);
        Assert.assertEquals(19518, atm.state());
        atm.get(5509);
        int expected = 19518 - 5509;
        Assert.assertEquals(expected, atm.state());
        atm.get(atm.state());
        Assert.assertEquals(0, atm.state());
    }
}