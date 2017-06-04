package com.vakhnenko.dao;

import com.vakhnenko.entity.Changes;

import java.util.List;

abstract class dao<T extends Changes> {
    abstract public void add(T item);

    abstract public List<T> list();
}
