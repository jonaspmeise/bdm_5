package org.wwu.dma.mapreduce.interfaces;

import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;

import java.util.List;

public interface LocalReducer<A, B, X, Y> {
    List<KeyValuePair<X, Y>> reduce(List<KeyValuePair<A, List<B>>> input);
}
