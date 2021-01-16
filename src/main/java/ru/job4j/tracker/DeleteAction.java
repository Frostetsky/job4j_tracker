package ru.job4j.tracker;

public class DeleteAction implements UserAction {

    @Override
    public String name() {
        return "==== Delete item ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String id = input.askStr("Enter id: ");
        if (store.delete(Integer.parseInt(id))) {
            System.out.println("Successfully");
        } else {
            System.out.println("Unsuccessfully");
        }
        return true;
    }
}
