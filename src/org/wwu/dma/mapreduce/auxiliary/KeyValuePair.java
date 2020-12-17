package org.wwu.dma.mapreduce.auxiliary;

public class KeyValuePair<E, F> {
    private final E key;
    private final F value;

    public KeyValuePair(E key, F value) {
        this.key = key;
        this.value = value;
    }

    public E getKey() {
        return key;
    }

    public F getValue() {
        return value;
    }
}
