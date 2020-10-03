package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test@Ignore
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"2", "0"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
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

    @Test@Ignore
    public void whenShowAllItems() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ShowAllAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                    "Menu." + System.lineSeparator()
                        + "0. ==== Show all items ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. ==== Show all items ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()
        ));
    }

    @Test@Ignore
    public void whenFindItemById() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "123456", "1"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new FindItemByIdAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. ==== Find item by Id ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()
                        + "Item not found" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. ==== Find item by Id ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test@Ignore
    public void whenFindItemsByName() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Names", "1"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new FindItemsByNameAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. ==== Find item by Name ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()
                        + "Items not found" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. ==== Find item by Name ====" + System.lineSeparator()
                        + "1. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test@Ignore
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. ==== Exit Program ====" + System.lineSeparator()

        ));
    }

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new CreateAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", replacedName, item.getId(), "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", item.getId(), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(),
                new ExitProgramAction()
        };
        new StartUI().init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()), is(nullValue()));
    }
}