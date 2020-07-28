package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "==== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        String id = input.askStr("Enter id: ");
        if (tracker.replace(id, item)) {
            out.println("Successfully");
        } else {
            out.println("Unsuccessfully");
        }
        return true;
    }
}
