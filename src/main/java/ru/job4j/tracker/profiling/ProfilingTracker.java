package ru.job4j.tracker.profiling;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;

public class ProfilingTracker {
    public static void main(String[] args) throws InterruptedException {
        Store store = new MemTracker();
        for (int i = 0; i < 2000; i++) {
            store.add(new Item(i, "Item" + i));
        }
        System.out.println("Start");
        for (int i = 2001; i < 100000000; i++) {
            store.add(new Item(i, "Item" + i));
            Thread.sleep(10);
        }
    }
}
