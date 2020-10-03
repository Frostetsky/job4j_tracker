package ru.job4j.tracker;

public class ReplaceAction implements UserAction {

    @Override
    public String name() {
        return "==== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        String id = input.askStr("Enter id: ");
        if (store.replace(id, item)) {
            System.out.println("Successfully");
        } else {
            System.out.println("Unsuccessfully");
        }
        return true;
    }
}
