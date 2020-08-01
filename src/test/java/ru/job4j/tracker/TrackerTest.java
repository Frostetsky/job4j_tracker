package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void whenArrayWasCleaned() {
        Item item1 = new Item("test1");
        Tracker tracker = new Tracker();
        tracker.add(item1);
        List<Item> items = tracker.findAll();
        tracker.delete(item1.getId());
        ArrayList<Item> expected = new ArrayList<>();
        assertThat(items, is(expected));
    }

    @Test
    public void putArrayItemByKey() {
        Item item1 = new Item("test1");
        Item item2 = new Item("test3");
        Item item3 = new Item("test3");
        Tracker tracker = new Tracker();
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> result = tracker.findByName("test3");
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        assertThat(result, is(expected));
    }

    @Test
    public void addItemInArray() {
        Item item = new Item("test1");
        Tracker tracker = new Tracker();
        tracker.add(item);
        List<Item> items = tracker.findAll();
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

    @Test
    public void whenItemsAscSorted() {
        List<Item> list = new ArrayList<>();
        ItemAscByName itemAscByName = new ItemAscByName();
        Item item1 = new Item("Dmitri");
        Item item2 = new Item("Aleksandr");
        Item item3 = new Item("Bob");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Collections.sort(list, itemAscByName);
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        expected.add(item1);
        assertThat(list, is(expected));
    }

    @Test
    public void whenItemsDescSorted() {
        List<Item> list = new ArrayList<>();
        ItemDescByName itemDescByName = new ItemDescByName();
        Item item1 = new Item("Dmitri");
        Item item2 = new Item("Aleksandr");
        Item item3 = new Item("Bob");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        Collections.sort(list, itemDescByName);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        expected.add(item2);
        assertThat(list, is(expected));
    }
}