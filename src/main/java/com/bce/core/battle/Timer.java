package com.bce.core.battle;


import java.util.TimerTask;

class Timer {
    public void count(int wait) {
        Runnable r = () -> {
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
