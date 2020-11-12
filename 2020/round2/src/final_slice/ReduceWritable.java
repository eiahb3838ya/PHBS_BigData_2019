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
public class ReduceWritable implements Writable {
    private FloatWritable open;
    private FloatWritable high;
    private FloatWritable close;
    private FloatWritable low;
    private FloatWritable volume;
    //    private IntWritable maleLogins;
    //    private IntWritable femaleLogins;

    public ReduceWritable() {
        open = new FloatWritable(0);
        high = new FloatWritable(0);
        close = new FloatWritable(0);
        low = new FloatWritable(0);

        volume = new FloatWritable(0);

    }

    public ReduceWritable(FloatWritable open,
                          FloatWritable high,
                          FloatWritable close,
                          FloatWritable low,
                          FloatWritable volume) {
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
    }


    public FloatWritable getHigh() {
        return high;
    }

    public void setHigh(FloatWritable high) {
        this.high = high;
    }

    public FloatWritable getClose() {
        return close;
    }

    public void setClose(FloatWritable close) {
        this.close = close;
    }

    public FloatWritable getLow() {
        return low;
    }

    public void setLow(FloatWritable low) {
        this.low = low;
    }

    public FloatWritable getOpen() {
        return open;
    }
    public FloatWritable getVolume() {
        return volume;
    }

    public void setOpen(FloatWritable open) {
        this.open = ReduceWritable.this.open;
    }

    public void setVolume(FloatWritable volume) {
        this.volume = volume;
    }

    public void readFields(DataInput in) throws IOException {
        open.readFields(in);
        high.readFields(in);
        close.readFields(in);
        low.readFields(in);

        volume.readFields(in);

    }

    public void write(DataOutput out) throws IOException {
        open.write(out);
        high.write(out);
        close.write(out);
        low.write(out);
        volume.write(out);

    }

    @Override
    public String toString() {
        return open.toString() + "\t" + high.toString()+ "\t" + close.toString()+ "\t" + low.toString()+ "\t" + volume.toString();
    }


}
