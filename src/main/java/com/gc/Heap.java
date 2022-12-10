package com.gc;

import com.model.MyObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class Heap {

    private static final Map<MyObject, Integer> EDEN = new HashMap<>();
    //I didn't use survivors here, but the idea is for omiting memory fragmentation
    private static final Map<MyObject, Integer> SURV1 = new HashMap<>();
    private static final Map<MyObject, Integer> SURV2 = new HashMap<>();
    private static final Map<MyObject, Integer> G1 = new HashMap<>();

    @Value("${heap.eden.cycle}")
    private int cycle;

    public static void addToHeap(MyObject obj) {
        EDEN.put(obj, 1);
    }

    public static void removeFromHeap(MyObject obj) {
        EDEN.put(obj, EDEN.get(obj)-1);
    }

    void sweepEden() {
        System.out.println("EDEN size before sweep:" + EDEN.entrySet().size());

        Iterator<MyObject> iterator = EDEN.keySet().iterator();
        try {
            while (iterator.hasNext()) {
                MyObject obj = iterator.next();
                if (obj.getCnt() == 0) {
                    iterator.remove();
                } else {
                    EDEN.put(obj, EDEN.get(obj) + 1);
                    if (EDEN.get(obj) > cycle) {
                        iterator.remove();
                        G1.put(obj, 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("EDEN size after sweep:" + EDEN.entrySet().size());
    }

    void sweepG1() {
        System.out.println("G1 size before sweep:" + G1.entrySet().size());
        Iterator<MyObject> iterator = G1.keySet().iterator();
        while (iterator.hasNext()) {
            MyObject obj = iterator.next();
            if (obj.getCnt() == 0) {
                iterator.remove();
            }
        }
        System.out.println("G1 size after sweep:" + G1.entrySet().size());
    }
}
