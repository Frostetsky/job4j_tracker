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
        Item item = null;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].getId().equals(id)) {
                item = this.items[i];
                break;
            }
        }
        return item;
    }


    /**
     * Возвращает массив Объектов без null элементов.
     * @return Массив без null элементов.
     */
    public Item[] findAll() {
        Item[] array = new Item[this.items.length];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i] != null) {
                array[size] = this.items[i];
                size++;
            }
        }
        array = Arrays.copyOf(array, size);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
        return array;
    }

    /**
     * Возвращает массив Итемов с одинаковыми именами.
     * @key Ключ для нахождения сходства в массиве items
     * @return Массив итемов с одинаковыми именами.
     */
    public Item[] findByName(String key) {
        Item[] array = new Item[this.items.length];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (key.equals(this.items[i].getName())) {
                array[size] = this.items[i];
                size++;
            }
        }
        array = Arrays.copyOf(array, size);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
        return array;
    }

    /**
     * Геттер для Теста.
     * @return Массив items.
     */
    public Item[] getItems() {
        return items;
    }
}