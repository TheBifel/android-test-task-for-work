package com.bifel.testtaskforwork.screens.tab1;

import android.support.annotation.NonNull;

public final class Contact {

    private final String name;
    private long sqlId;

    public Contact(String name, long sqlId) {
        this.name = name;
        this.sqlId = sqlId;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public long getSqlId() {
        return sqlId;
    }

    public void setSqlId(long sqlId) {
        this.sqlId = sqlId;
    }
}
