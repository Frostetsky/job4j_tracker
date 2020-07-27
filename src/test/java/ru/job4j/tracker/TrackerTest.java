package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void WhenArrayWasCleaned() {
        Item item1 = new Item("test1");
        Tracker tracker = new Tracker();
        tracker.add(item1);
        ArrayList<Item> items = tracker.findAll();
        tracker.delete(item1.getId());
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(items, is(expected));
    }

    @Test
    public void PutArrayItemByKey() {
        Item item1 = new Item("test1");
        Item item2 = new Item("test3");
        Item item3 = new Item("test3");
        Tracker tracker = new Tracker();
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        ArrayList<Item> result = tracker.findByName("test3");
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        assertThat(result, is(expected));
    }

    @Test
    public void AddItemInArray() {
        Item item = new Item("test1");
        Tracker tracker = new Tracker();
        tracker.add(item);
        ArrayList<Item> items = tracker.findAll();
        Item expected = item;
        assertEquals(items.get(0), expected);
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}