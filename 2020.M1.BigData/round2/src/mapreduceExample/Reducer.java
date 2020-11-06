package mapreduceExample;

// The following is the reducer
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;
public class Reducer extends MapReduceBase implements
        org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text t_key, Iterator<IntWritable> values,
                       OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException
    {
        int oneProductRevenue = 0;
        for (Iterator<IntWritable> it = values; it.hasNext(); ) {
            IntWritable value = it.next();
            oneProductRevenue += value.get();
        }
        output.collect(t_key, new IntWritable(oneProductRevenue));
    }
}