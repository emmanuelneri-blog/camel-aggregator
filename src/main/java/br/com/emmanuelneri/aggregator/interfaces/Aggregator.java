package br.com.emmanuelneri.aggregator.interfaces;

import java.util.ArrayList;
import java.util.List;

public class Aggregator<T> {

    private final String id;
    private List<T> values;

    public Aggregator(final String id) {
        this.id = id;
        this.values = new ArrayList<T>();
    }

    public void add(final T value) {
        this.values.add(value);
    }

    public String getId() {
        return id;
    }

    public List<T> getValues() {
        return values;
    }
}
