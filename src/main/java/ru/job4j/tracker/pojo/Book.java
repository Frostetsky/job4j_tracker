package ru.job4j.tracker.pojo;

public class Book {
    private String name;
    private int pages;

    public Book() {
        this.name = "Default Book";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
