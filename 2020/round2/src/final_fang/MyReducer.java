package final_fang;

/**
* @author Edward Gao
* @version 1.1
* @date 2020-10-28
*/

import org.apache.hadoop.io.FloatWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {

		float maxClosePrice = Float.MIN_VALUE;
		//Iterate all temperatures for a year and calculate maximum
		for (FloatWritable value : values) {
			maxClosePrice = Math.max(maxClosePrice, value.get());
		}
		//Write output
		context.write(key, new FloatWritable(maxClosePrice));
	}
}