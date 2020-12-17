package org.wwu.dma.mapreduce.auxiliary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataReader {
	
	/**
     * Reads in the respective weather file and produces an application-agnostic representation as list of Key-Value-Pairs.
     * Every line of the file is represented by its line index (key) and the actual content as a String (value).
     * No further preprocessing is done here.
     * @param None. Only reads the predefined weather file.
     * @return output: List of Key-Value-Pairs. Key: line index, Value: line value (line as String)
     */
    public static List<KeyValuePair<Long, String>> read() throws FileNotFoundException, IOException {
        List<KeyValuePair<Long, String>> output = new ArrayList<KeyValuePair<Long, String>>();

        File source = new File("data/GlobalLandTemperaturesByCountry.csv");
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);

        String line;
        long idx = 0L;
        while((line = br.readLine()) != null) {
            output.add(new KeyValuePair<Long, String>(idx++, line));
        }

        return output;
    }
}
