package com.model;

import com.gc.Heap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyObject extends Object {
    private AtomicInteger cnt;

    public MyObject() {
        cnt = new AtomicInteger(0);
    }

    public int getCnt() {
        return this.cnt.get();
    }

    public void addReference() {
        this.cnt.getAndIncrement();
        Heap.addToHeap(this);
    }

    public void removeReference() {
        this.cnt.getAndDecrement();
        Heap.removeFromHeap(this);
    }
}
