package hw2_1;

// The following is a map process

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;

public class AirQualityMapper extends MapReduceBase implements
        org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
//    private DateTimeFormatter asFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
    public void map(LongWritable key, Text value, OutputCollector<Text,
            IntWritable> output, Reporter reporter){
        String valueString = value.toString();
        String[] oneRecord = valueString.split(",");
        String PM25 = oneRecord[5];
        if (!PM25.equals("\"PM2.5\"")){
            try{
                if(!PM25.equals("NA")) {

//                    int aYear = Integer.parseInt(oneRecord[2]);
//                    int aMonth = Integer.parseInt(oneRecord[2]);
//                    int aDay = Integer.parseInt(oneRecord[3]);
//                    int aHour = Integer.parseInt(oneRecord[4]);
                    float pm25 = Float.parseFloat(PM25);
//
//                    String YearMonDateHour = LocalDateTime.of(aYear, aMonth, aDay, aHour, 0).format(asFormat);
                    String airStatus;
                    if (pm25 <= 75) {
                        airStatus = "_fine";
//                    } else if (35 < pm25 && pm25 <= 75) {
//                        airStatus = "_medium";
                    } else {
                        airStatus = "_bad";
                    }
//                    Text mappingKey = new Text(YearMonDateHour + airStatus);
                    Text mappingKey = new Text(oneRecord[17] + airStatus);
                    output.collect(mappingKey, one); // 1st column is the product name
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}

