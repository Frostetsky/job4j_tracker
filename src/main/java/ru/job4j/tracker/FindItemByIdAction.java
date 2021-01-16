package ru.job4j.tracker;

public class FindItemByIdAction implements UserAction {

    @Override
    public String name() {
        return "==== Find item by Id ====";
    }

    @Override
    public boolean execute(Input input, Store store) {
        String id = input.askStr("Enter id: ");
        Item item = store.findById(Integer.parseInt(id));
        if (item != null) {
            System.out.println(item.toString());
        } else {
            System.out.println("Item not found");
        }
        return true;
    }
}
