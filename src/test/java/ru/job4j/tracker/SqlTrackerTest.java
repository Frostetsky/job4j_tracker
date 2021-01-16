package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Nikita"));
            assertThat(tracker.findByName("Nikita").get(0).getName(), is("Nikita"));
        }
    }

    @Test
    public void deleteItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Josh"));
            Integer id = tracker.findByName("Josh").get(0).getId();
            tracker.delete(id);
            assertThat(tracker.findAll().size(), is(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showAllItems() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Nikita"));
            tracker.add(new Item("Denis"));
            tracker.add(new Item("Aleksandra"));
            System.out.println(tracker.findAll());
            assertThat(tracker.findAll().size(), is(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("Sergey");
            Item item2 = new Item("Dima");
            tracker.add(item1);
            tracker.add(item2);
            Integer id = tracker.findAll().get(1).getId();
            assertThat(tracker.findById(id).getName(), is("Dima"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}