package org.wwu.dma.mapreduce.interfaces;

import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;

import java.util.List;

public interface LocalMapper<A, B, X, Y> {
    List<KeyValuePair<X, Y>> map(List<KeyValuePair<A, B>> input);
}
