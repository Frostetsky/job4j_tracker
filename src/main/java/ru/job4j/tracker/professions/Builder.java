package ru.job4j.tracker.professions;

public class Builder extends Engineer {

    @Override
    public void projectImplementation(Project project) {

    }

    @Override
    public String getName() {
        return "Builder";
    }

    @Override
    public int priceProject() {
        return 250_000;
    }
}
