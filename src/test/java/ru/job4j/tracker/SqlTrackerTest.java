package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
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
    public void createItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Nikita"));
            assertThat(tracker.findByName("Nikita"), is(List.of(new Item("Nikita"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Mira");
            tracker.add(item);
            String id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findAll(), is(List.of()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showAllItems() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("Sergey");
            Item item2 = new Item("Dima");
            assertThat(tracker.findAll(), is(List.of(item1, item2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("Sergey");
            Item item2 = new Item("Dima");
            assertThat(tracker.findById(item2.getId()), is(new Item("Dima")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}