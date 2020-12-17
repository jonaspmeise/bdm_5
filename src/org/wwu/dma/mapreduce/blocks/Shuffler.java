package org.wwu.dma.mapreduce.blocks;

import org.wwu.dma.mapreduce.interfaces.LocalShuffle;
import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;

import java.util.ArrayList;
import java.util.List;

public class Shuffler implements LocalShuffle<Long, Double, Long, Double> {
	
	/**
     * Groups values by key.
     * @param input: application-specific representation as list of Key-Value-Pairs
     * @return output: list of Key-List<Value>-Pairs.
     */
    public List<KeyValuePair<Long, List<Double>>> shuffle(List<KeyValuePair<Long, Double>> input) {
        List<KeyValuePair<Long, List<Double>>> output = new ArrayList<KeyValuePair<Long, List<Double>>>();

        boolean added;
        
    	for(KeyValuePair kvp : input) {
    		added = false;
    		Long currentKey = (Long) kvp.getKey();
    		Double currentValue = (Double) kvp.getValue();
    		
    		//ausnahmefall
    		if(output.size() == 0) {
    			output.add(new KeyValuePair<Long, List<Double>>(
    					currentKey,
    					new ArrayList<Double>()));
    		}

    		for(KeyValuePair<Long, List<Double>> neuKvp : output) {
    			if(neuKvp.getKey().equals(currentKey)) {
    				neuKvp.getValue().add(currentValue);
    				added = true;
    			}
    		}
    		
    		if(!added || output.size() == 0) {
    			List<Double> neueListe = new ArrayList<Double>();
    			neueListe.add(currentValue);
    			
    			output.add(new KeyValuePair<Long, List<Double>>(currentKey, neueListe));
    		}
    	}
    	
    	return output;
    }
}
