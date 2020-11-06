package final_fang;

/**
* @author Edward Gao
* @version 1.1
* @date 2020-10-28
*/

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileAlreadyExistsException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyDriver {
	public static void main(String[] args)
			throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException,FileAlreadyExistsException {
		// 设置Configuration
		Configuration conf = new Configuration();

		// 新建一个Job实例控制Map和Reduce
		Job job = Job.getInstance(conf, "BDA_project");
		// 将project文件夹中的class打包成Jar用来并行运行
		job.setJarByClass(MyDriver.class);

		// 设置Mapper类以及Mapper类输出的Key-Value数据类型
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);

		// 设置Reducer类以及Reducer类输出的Key-Value数据类型
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// 设置输入和输出的文件路径
		FileInputFormat.addInputPath(job, new Path("./input/"));
		FileOutputFormat.setOutputPath(job, new Path("./output/"));

		System.out.println(job.waitForCompletion(true) ? 0 : 1);
	}
}
