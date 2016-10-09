package com.emaple.yan.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Atm atm = new AtmImpl();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = reader.readLine();
            String[] arr = s.split(" ");
            if(s.startsWith("quit")) break;
            if(s.startsWith("state")) atm.state();
            if(s.startsWith("dump")) atm.dump();
            try {
                if(s.startsWith("get")) atm.get(Integer.parseInt(arr[1]));
                if(s.startsWith("put")) atm.putMoney(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            } catch (ArrayIndexOutOfBoundsException e) {
              //wrong print
            }
        }
    }


}
