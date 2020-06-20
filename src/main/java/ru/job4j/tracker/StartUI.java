package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                System.out.println("==== Create a new Item ====");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("==== Show all items ====");
                Item[] items = tracker.findAll();
                for (int i = 0; i < items.length; i++) {
                    System.out.println(items[i].toString());
                }
            } else if (select == 2) {
                System.out.println("==== Edit item ====");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                String id = input.askStr("Enter id: ");
                if (tracker.replace(id, item)) {
                    System.out.println("Successfully");
                } else {
                    System.out.println("Unsuccessfully");
                }
            } else if (select == 3) {
                System.out.println("==== Delete item ====");
                String id = input.askStr("Enter id: ");
                if (tracker.delete(id)) {
                    System.out.println("Successfully");
                } else {
                    System.out.println("Unsuccessfully");
                }
            } else if (select == 4) {
                System.out.println("==== Find item by Id ====");
                String id = input.askStr("Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println(item.toString());
                } else {
                    System.out.println("Item not found");
                }
            } else if (select == 5) {
                System.out.println("==== Find item by Name ====");
                String name = input.askStr("Enter name: ");
                Item[] items = tracker.findByName(name);
                if (items != null) {
                    for (int i = 0; i < items.length; i++) {
                        System.out.println(items[i].toString());
                    }
                } else {
                    System.out.println("Items not found");
                }
            } else if (select == 6) {
                System.out.println("==== Exit program ====");
                break;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}