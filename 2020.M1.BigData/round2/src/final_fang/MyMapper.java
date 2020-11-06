package final_fang;

/**
* @author Edward Gao
* @version 1.1
* @date 2020-10-28
*/

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        String[] items = line.split(",");

        String stockTimeStamp = items[0]+"-"+items[1].substring(0,12);
        if (!items[2].equals(0)){

            Float closePrice = Float.parseFloat(items[2]);
            context.write(new Text(stockTimeStamp), new FloatWritable(closePrice));
        }
    }
}
