package com.example.simpletax.domain;

public abstract class TaxForm {

    private final String name;
    private final String id;

    public TaxForm(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
