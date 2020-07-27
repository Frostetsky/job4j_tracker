package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test(expected = NumberFormatException.class)
    public void whenInvalidFirstPoint() {
        Tracker tracker = new Tracker();
        Output out = new StubOutput();
        UserAction[] actions = {
                new ShowAllAction(out),
                new ExitProgramAction(out),
        };
        Input in = new StubInput(
                new String[] {"73", "First"}
        );
        new StartUI(out).init(in,tracker,actions);
    }
}