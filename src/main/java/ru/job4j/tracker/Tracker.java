package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализующий добавление заявки в хранилище.
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items[position++] = item;
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
        return index != -1 ? items[index] : null;
    }


    /**
     * Возвращает массив Объектов без null элементов.
     * @return Массив без null элементов.
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Возвращает массив Итемов с одинаковыми именами.
     * @key Ключ для нахождения сходства в массиве items
     * @return Массив итемов с одинаковыми именами.
     */
    public Item[] findByName(String key) {
        Item[] array = new Item[position];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (key.equals(this.items[i].getName())) {
                array[size] = this.items[i];
                size++;
            }
        }
        array = Arrays.copyOf(array, size);
        return array;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        item.setId(id);
        items[index] = item;
        return true;
    }

    public boolean delete(String id) {
        int index = indexOf(id);
        items[index] = null;
        System.arraycopy(items, index + 1, items, index, position - index);
        items[position - 1] = null;
        position--;
        return true;
    }
}