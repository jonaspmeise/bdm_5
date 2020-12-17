package org.wwu.dma.mapreduce.blocks;

import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;
import org.wwu.dma.mapreduce.interfaces.LocalReducer;

import java.util.ArrayList;
import java.util.List;

public class Reducer implements LocalReducer<Long, Double, Long, Double> {
	
	/**
     * Performs the actual aggregation for the given application scenario.
     * @param input: list of Key-List<Value>-Pairs.
     * @return output: List of Key-Value-Pairs representing the respective aggregation per key (if applicable).
     */
    public List<KeyValuePair<Long, Double>> reduce(List<KeyValuePair<Long, List<Double>>> input) {
        List<KeyValuePair<Long, Double>> output = new ArrayList<KeyValuePair<Long, Double>>();

        double average;
        
        for(KeyValuePair kvp : input) {
        	average = 0;
        	
        	for(Double singleValue : (List<Double>)(kvp.getValue())) {
        		average += singleValue;
        	}
        	
        	output.add(new KeyValuePair(kvp.getKey(), (average / ((List<Double>)kvp.getValue()).size())));
        }
        
        return output;
    }
}
