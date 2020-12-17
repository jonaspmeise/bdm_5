package org.wwu.dma.mapreduce;

import org.wwu.dma.mapreduce.blocks.Mapper;
import org.wwu.dma.mapreduce.blocks.Reducer;
import org.wwu.dma.mapreduce.blocks.Shuffler;
import org.wwu.dma.mapreduce.interfaces.LocalMapper;
import org.wwu.dma.mapreduce.interfaces.LocalReducer;
import org.wwu.dma.mapreduce.interfaces.LocalShuffle;
import org.wwu.dma.mapreduce.auxiliary.KeyValuePair;
import org.wwu.dma.mapreduce.auxiliary.WeatherDataReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class WeatherAnalyzer {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<KeyValuePair<Long, String>> data = WeatherDataReader.read();

        LocalMapper<Long, String, Long, Double> mapper = new Mapper();

        List<KeyValuePair<Long, Double>> mapOutput = mapper.map(data);

        LocalShuffle<Long, Double, Long, Double> shuffler = new Shuffler();

        List<KeyValuePair<Long, List<Double>>> shuffleOutput = shuffler.shuffle(mapOutput);

        LocalReducer<Long, Double, Long, Double> reducer = new Reducer();

        List<KeyValuePair<Long, Double>> reduceOutput = reducer.reduce(shuffleOutput);

        System.out.println("Highest global average temperature per year: ");
        for(KeyValuePair<Long, Double> kvp: reduceOutput) {
            System.out.println("\t " + kvp.getKey() + ", " + kvp.getValue() + " °C");
        }
    }
}
