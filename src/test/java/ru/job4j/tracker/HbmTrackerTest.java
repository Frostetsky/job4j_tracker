package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HbmTrackerTest {

    @Test
    public void whenAddItem() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item item = new Item("Nikita");
        store.add(item);
        List<Item> result = store.findAll();

        assertThat(result.size(), is(1));
    }

    @Test
    public void whenReplaceItem() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item item = new Item("Nikita");
        store.add(item);
        boolean result = store.replace(1, new Item("Andrey"));
        Item updated = store.findById(1);

        assertThat(updated.getName(), is("Andrey"));
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteItem() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item item = new Item("Petr");
        store.add(item);
        boolean result = store.delete(1);
        List<Item> clearList = store.findAll();

        assertThat(clearList.size(), is(0));
        assertThat(result, is(true));
    }

    @Test
    public void whenFindAllItem() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item one = new Item("1");
        Item two = new Item("2");
        store.add(one);
        store.add(two);

        List<Item> result = store.findAll();

        assertThat(result.size(), is(2));
    }

    @Test
    public void whenFindByID() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item item = new Item("John");
        store.add(item);

        Item result = store.findById(1);

        assertThat(result.getName(), is("John"));
    }

    @Test
    public void whenFindByName() {
        final HbmTracker store = new HbmTracker();
        store.init();
        Item itemN = new Item("Nikita");
        Item itemOtherN = new Item("Nikita");
        store.add(itemN);
        store.add(itemOtherN);

        List<Item> result = store.findByName("Nikita");

        assertThat(result.size(), is(2));
    }
}