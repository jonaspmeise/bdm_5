package org.wwu.dma.mapreduce.blocks;

import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;
import org.wwu.dma.mapreduce.interfaces.LocalMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper implements LocalMapper<Long, String, Long, Double> {
    // Missing measurements are indicated by 9999
    private static final int MISSING = 9999;

    /**
     * Produces an application-specific representation of the given data.
     * @param input: List of Key-Value-Pairs. Key: line index, Value: line value (line as String)
     * @return output: application-specific representation. Encompasses only the information needed for the given analytical purpose.
     */
    public List<KeyValuePair<Long, Double>> map(List<KeyValuePair<Long, String>> input) {
        List<KeyValuePair<Long, Double>> output = new ArrayList<KeyValuePair<Long, Double>>();
        //remove the 0th element
        input.remove(0);
        
        String year, temperature;
        
        for(KeyValuePair<Long, String> kvp : input) {
            year = kvp.getValue().split("-")[0];
            temperature = kvp.getValue().split(",")[1];
        	
            if (!(year.compareTo("") == 0 || temperature.compareTo("") == 0)) {
            	output.add(new KeyValuePair<Long, Double>(
	        			Long.valueOf(year), 
	        			Double.valueOf(temperature)));
            }
        }
        
        return output;
    }
}
