package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] { "2", "0" }
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "Menu.%n"
                                + "0. ==== Exit Program ====%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. ==== Exit Program ====%n"
                )
        ));
    }

    @Test
    public void whenShowAllItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0","1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ShowAllAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. ==== Show all items ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. ==== Show all items ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindItemById() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0","123456","1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new FindItemByIdAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. ==== Find item by Id ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator() +
                        "Item not found" + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. ==== Find item by Id ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test
    public void whenFindItemsByName() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0","Names","1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new FindItemsByNameAction(out),
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. ==== Find item by Name ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator() +
                        "Items not found" + System.lineSeparator() +
                        "Menu." + System.lineSeparator() +
                        "0. ==== Find item by Name ====" + System.lineSeparator() +
                        "1. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new ExitProgramAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Replaced item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", replacedName, item.getId(),  "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitProgramAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Deleted item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        Input in = new StubInput(
                new String[] {"0", item.getId(), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitProgramAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}