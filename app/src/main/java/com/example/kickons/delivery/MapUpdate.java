package com.example.kickons.delivery;
;

public class MapUpdate implements Runnable {


    @Override
    public void run(){

        try {
            System.out.println("running");
            Thread.sleep(5000);
            System.out.println("running after 5 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
