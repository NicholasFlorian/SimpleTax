package com.example.simpletax.domain;

import java.util.Date;

public abstract class TaxForm {

    private final String name;
    private final String id;
    private final Date createdAt;

    public TaxForm(String name, String id) {
        this.id = id;
        this.name = name;
        this.createdAt = new Date();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
