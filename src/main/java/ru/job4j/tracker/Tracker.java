package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализующий добавление заявки в хранилище.
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + (long) (Math.random() * 100));
    }

    /**
     * Метод возвращает заявку по id.
     * @param id - id заявки.
     * @return Нужная заявка.
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }


    /**
     * Возвращает Лист Объектов элементов.
     * @return Лист элементов.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Возвращает лист Итемов с одинаковыми именами.
     * @key Ключ для нахождения сходства в массиве items
     * @return Лист итемов с одинаковыми именами.
     */
    public List<Item> findByName(String key) {
        List<Item> array = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (key.equals(items.get(i).getName())) {
                array.add(items.get(i));
            }
        }
        return array;
    }

    /**
     * Метод возвращает индекс ячейки массива по его id.
     * @param id - id заявки.
     * @return индекс ячейки под которым содержится нужный объект.
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод заменяет заявку по id.
     * @param id - id который необходимо заменить.
     * @param item новая заявка.
     * @return успешность результата выполнения.
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            items.set(index, item);
        }
        return rsl;
    }

    /**
     * Метод удаляет заявку по её id.
     * @param id - id заявки.
     * @return успешность результата выполнения.
     */

    public boolean delete(String id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return rsl;
    }
}