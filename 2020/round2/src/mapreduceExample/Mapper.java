package mapreduceExample;

// The following is a map process
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
public class Mapper extends MapReduceBase implements
        org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, IntWritable> {
//    private final static IntWritable one = new IntWritable(1000);
    public void map(LongWritable key, Text value, OutputCollector<Text,
            IntWritable> output, Reporter reporter){
        String valueString = value.toString();
        String[] oneRecord = valueString.split(",");
        try{
            int salesRevenue = Integer.parseInt(oneRecord[2]); // 3rd column is sales revenue
            output.collect(new Text(oneRecord[0]), new IntWritable(salesRevenue)); // 1st column is the product name
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}

