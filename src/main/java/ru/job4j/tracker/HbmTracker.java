package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;


public class HbmTracker implements Store, AutoCloseable {
    private StandardServiceRegistry registry;
    private SessionFactory sf;

    public void init() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure().build();
        this.sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    public boolean replace(Integer id, Item item) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            item.setId(id);
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            result = true;
        }
        return result;
    }

    public boolean delete(Integer id) {
        boolean result = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = new Item(null);
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
            result = true;
        }
        return result;
    }

    public List<Item> findAll() {
        List result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.Item").list();
            session.getTransaction().commit();
        }
        return result;
    }

    public Item findById(Integer id) {
        Item result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, id);
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Item where name= :name");
            query.setParameter("name", key);
            result = query.getResultList();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
