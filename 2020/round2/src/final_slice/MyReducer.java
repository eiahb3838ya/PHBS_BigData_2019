package final_slice;

/**
* @author Evan
* @version 1.1
* @date 2020-10-28
*/

import org.apache.hadoop.io.FloatWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text, MapWritable, Text, ReduceWritable> {

	public void reduce(Text key, Iterable<MapWritable> values, Context context)
			throws IOException, InterruptedException {

		float high = Float.MIN_VALUE;
		float low = Float.MAX_VALUE;
		float open = -1;
		float close = 0;
		float totalVolume = 0;
		for (MapWritable value : values) {
			Float price = value.getPrice().get();
			Float volume = value.getVolume().get();

			if (open<0){
				open = price;
			}
			high = Math.max(high, price);
			close = price;
			low = Math.min(low, price);
			totalVolume += volume;
		}
		//Write output
		context.write(key, new ReduceWritable(new FloatWritable(open), new FloatWritable(high), new FloatWritable(close), new FloatWritable(low), new FloatWritable(totalVolume)));
	}
}