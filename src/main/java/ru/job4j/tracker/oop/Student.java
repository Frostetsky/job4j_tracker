package ru.job4j.tracker.oop;

public class Student {
    public void song() {
        System.out.println("I believe I can fly");
    }

    public void music() {
        System.out.println("Tra tra tra");
    }

    public static void main(String[] args) {
        Student petya = new Student();
        for (int i = 0; i < 3; i++) {
            petya.song();
            petya.music();
        }
    }
}
