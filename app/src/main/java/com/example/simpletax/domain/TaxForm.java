package com.example.simpletax.domain;

import java.util.Date;

public abstract class TaxForm {

    private final String name;
    private final String id;
    private final String uuid;
    private final Date createdAt;

    public TaxForm(String name, String id) {
        this.id = id;
        this.name = name;
        this.uuid = java.util.UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TaxForm taxForm = (TaxForm) obj;

        if (!name.equals(taxForm.name)) return false;
        if (!id.equals(taxForm.id)) return false;
        if (!uuid.equals(taxForm.uuid)) return false;
        return createdAt.equals(taxForm.createdAt);
    }
}
