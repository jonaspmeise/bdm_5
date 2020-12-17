package org.wwu.dma.mapreduce.interfaces;

import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;

import java.util.List;

public interface LocalShuffle<A, B, X, Y> {
    List<KeyValuePair<X, List<Y>>> shuffle(List<KeyValuePair<A, B>> input);
}
