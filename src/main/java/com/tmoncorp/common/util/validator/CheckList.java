package com.tmoncorp.common.util.validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class CheckList<T extends Validatable> {
    private Map<Object, Predicate<T>> checkList = new HashMap<>();

    public CheckList() {}
    public CheckList(Map<Object, Predicate<T>> checkList) {
        this.checkList = checkList;
    }

    public CheckList<T> addAll(Map<Object, Predicate<T>> checkList) {
        this.checkList.putAll(checkList);
        return this;
    }

    public CheckList<T> add(Object id, Predicate<T> checkItem) {
        this.checkList.put(id, checkItem);
        return this;
    }

    public Predicate<T> get(Object id) {
        return this.checkList.get(id);
    }


    public void forEach(BiConsumer<Object, Predicate<T>> action) {
        this.checkList.forEach(action);
    }

    public Collection<Predicate<T>> values() {
        return this.checkList.values();
    }
}
