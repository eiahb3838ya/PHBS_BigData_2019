package hw2_1;

// The following is the reducer
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;
public class AirQualityReducer extends MapReduceBase implements
        org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text t_key, Iterator<IntWritable> values,
                       OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException
    {
        int cityStatusCounter = 0;
        for (Iterator<IntWritable> it = values; it.hasNext(); ) {
            IntWritable value = it.next();
            cityStatusCounter += value.get();
        }
        output.collect(t_key, new IntWritable(cityStatusCounter));
    }
}