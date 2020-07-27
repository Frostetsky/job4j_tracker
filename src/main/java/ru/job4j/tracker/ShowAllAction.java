package ru.job4j.tracker;

import java.util.ArrayList;

public class ShowAllAction implements UserAction {
    private final Output output;

    public ShowAllAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "==== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        ArrayList<Item> items = tracker.findAll();
        for (Item itemsI : items) {
            System.out.println(itemsI);
        }
        return true;
    }
}
