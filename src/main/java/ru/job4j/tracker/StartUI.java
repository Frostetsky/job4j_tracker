package ru.job4j.tracker;

import java.sql.Connection;

public class StartUI {

    private static Connection connection;

    public void init(Input input, Store memTracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.length) {
                System.out.println("Wrong input, you can select: 0 .. " + (actions.length - 1));
                continue;
            }
            UserAction action = actions[select];
            run = action.execute(input, memTracker);
        }
    }
    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        try (Store tracker = new HbmTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction(),
                    new DeleteAction(),
                    new ReplaceAction(),
                    new ShowAllAction(),
                    new FindItemByIdAction(),
                    new FindItemsByNameAction(),
                    new ExitProgramAction(),
            };
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}