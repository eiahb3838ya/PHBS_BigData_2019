package final_slice;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//public class GenderLoginWritable implements Writable {
//    private IntWritable male;
//    private IntWritable female;
//    private IntWritable maleLogins;
//    private IntWritable femaleLogins;
//
//    public GenderLoginWritable() {
//        male = new IntWritable(0);
//        female = new IntWritable(0);
//        maleLogins = new IntWritable(0);
//        femaleLogins = new IntWritable(0);
//    }
//
//    public GenderLoginWritable(IntWritable male, IntWritable maleLogins, IntWritable female, IntWritable femaleLogins) {
//        this.male = male;
//        this.female = female;
//        this.maleLogins = maleLogins;
//        this.femaleLogins = femaleLogins;
//    }
//
//    public IntWritable getMale() {
//        return male;
//    }
//
//    public IntWritable getFemale() {
//        return female;
//    }
//
//    public IntWritable getMaleLogins() {
//        return maleLogins;
//    }
//
//    public IntWritable getFemaleLogins() {
//        return femaleLogins;
//    }
//
//    public void setMale(IntWritable male) {
//        this.male = male;
//    }
//
//    public void setFemale(IntWritable female) {
//        this.female = female;
//    }
//
//    public void setMaleLogins(IntWritable maleLogins) {
//        this.maleLogins = maleLogins;
//    }
//
//    public void setFemaleLogins(IntWritable femalelogins) {
//        this.femaleLogins = femalelogins;
//    }
//
//    public void readFields(DataInput in) throws IOException {
//        male.readFields(in);
//        female.readFields(in);
//        maleLogins.readFields(in);
//        femaleLogins.readFields(in);
//    }
//
//    public void write(DataOutput out) throws IOException {
//        male.write(out);
//        female.write(out);
//        maleLogins.write(out);
//        femaleLogins.write(out);
//    }
//
//    @Override
//    public String toString() {
//        return male.toString() + "\t" + maleLogins.toString() + "\t" + female.toString() + "\t" + femaleLogins.toString();
//    }
//
//}
public class MapWritable implements Writable {
    private FloatWritable price;
    private FloatWritable volume;
    //    private IntWritable maleLogins;
    //    private IntWritable femaleLogins;

    public MapWritable() {
        price = new FloatWritable(0);
        volume = new FloatWritable(0);

    }

    public MapWritable(FloatWritable price, FloatWritable volume) {
        this.price = price;
        this.volume = volume;
    }

    public FloatWritable getPrice() {
        return price;
    }

    public FloatWritable getVolume() {
        return volume;
    }

    public void setPrice(FloatWritable price) {
        this.price = price;
    }

    public void setVolume(FloatWritable volume) {
        this.volume = volume;
    }

    public void readFields(DataInput in) throws IOException {
        price.readFields(in);
        volume.readFields(in);

    }

    public void write(DataOutput out) throws IOException {
        price.write(out);
        volume.write(out);

    }

    @Override
    public String toString() {
        return price.toString() + "\t" + volume.toString();
    }




}
