package ru.job4j.tracker;

import java.util.List;

public class FindItemsByNameAction implements UserAction {

    @Override
    public String name() {
        return "==== Find item by Name ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String name = input.askStr("Enter name: ");
        List<Item> items = store.findByName(name);
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i));
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }
}
