package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test(expected = NumberFormatException.class)
    public void whenInvalidFirstPoint() {
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ShowAllAction(),
                new ExitProgramAction(),
        };
        Input in = new StubInput(
                new String[] {"73", "First"}
        );
        new StartUI().init(in, memTracker, actions);
    }
}