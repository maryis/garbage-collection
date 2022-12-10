package com.other;

import com.model.MyObject;

import java.util.Random;

import static java.lang.Thread.sleep;

public class NormalClass implements Runnable{

    private static final Random random = new Random();

    @Override
    public void run() {
        System.out.println("Started creating and deleting objects");
        while (true) {
            MyObject object = new MyObject();
            object.addReference();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //keep some in heap as dead (without reference)
            if(random.nextBoolean())
                object.removeReference();
        }
    }
}
