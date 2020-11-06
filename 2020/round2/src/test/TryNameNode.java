package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;

public class TryNameNode {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.addResource(new Path("C:\\Users\\eiahb\\Documents\\MyFiles\\SchoolThing\\PHBS_big_data_analysis\\setups\\hadoop-3.1.4\\hadoop-3.1.4\\etc\\hadoop\\core-site.xml")); //写自己的path
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("Enter the file path...");
        String filePath = br.readLine();
        Path path = new Path(filePath);
        FileSystem fs = path.getFileSystem(conf);
        FSDataInputStream inputStream = fs.open(path);
        System.out.println(inputStream.available());
        fs.close();
    }
}
