package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement("INSERT INTO items(name) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = st.getGeneratedKeys();
            st.setString(1, item.getName());
            st.executeUpdate();
            if (rs != null && rs.next()) {
                item.setId(rs.getString(1));
            };
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("UPDATE items SET name = ? WHERE items.id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            if (st.executeUpdate() > 0) {
                rsl = true;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement st = cn.prepareStatement("DELETE FROM items WHERE items.id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            if (st.executeUpdate() > 0) {
                rsl = true;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items WHERE items.name = ?")) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"));
                item.setId(rs.getString("id"));
                result.add(item);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement st = cn.prepareStatement("SELECT * FROM items WHERE items.id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result = new Item(rs.getString("name"));
                result.setId(rs.getString("id"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return result;
    }
}
