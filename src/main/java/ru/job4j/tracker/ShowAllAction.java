package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {

    @Override
    public String name() {
        return "==== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        List<Item> items = store.findAll();
        for (Item itemsI : items) {
            System.out.println(itemsI);
        }
        return true;
    }
}
