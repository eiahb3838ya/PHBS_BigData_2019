import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import net.lingala.zip4j.ZipFile;
import java.io.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
public class hw1{
    public static void readfile(String filepath) throws IOException, URISyntaxException {
        Configuration conf=new Configuration();
        conf.addResource(new Path("F:\\setups\\setups\\hadoop-3.1.4\\hadoop-3.1.4\\etc\\hadoop\\core-site.xml"));

        Path path=new Path(filepath);
        FileSystem fs=path.getFileSystem(conf);
        RemoteIterator<LocatedFileStatus> listFiles=fs.listFiles(new Path("/"),true);
       FileStatus fileStatus=fs.getFileStatus(path);
        System.out.println("文件大小："+fileStatus.getLen());
        BlockLocation[] blockLocations=fs.getFileBlockLocations(fileStatus,0,fileStatus.getLen());
        int i=0;
        for (BlockLocation blockLocation:blockLocations){
            System.out.println("block"+i+"所在位置为：");
            String[] hosts=blockLocation.getHosts();
            for (String host:hosts){
                System.out.println(host);
            }
            i=i+1;
        }
        System.out.println("block数："+i);
        fs.close();
    }
    public static void append(){
        // 将9兆的文件内容头部添加
        String strHead = "Yao Surui\nLiang Yi\nWang Zilei\nLiu Xin\nZhang Ying\nCREDIT to Sun Bo\nZhou Jian\n" ; // 添加的头部内容
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String srcFilePath = "F:\\Group55_Filesize_8.75MB.txt" ; // 原文件路径
        String destFilePath = "F:\\Group5_Filesize_8.75MB.txt" ; // 添加头部后文件路径 （最终添加头部生成的文件路径）
        try {
            // 映射原文件到内存
            RandomAccessFile srcRandomAccessFile = new RandomAccessFile(srcFilePath, "r");
            FileChannel srcAccessFileChannel = srcRandomAccessFile.getChannel();
            long srcLength = srcAccessFileChannel.size();
            MappedByteBuffer srcMap = srcAccessFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, srcLength);
            // 映射目标文件到内存
            RandomAccessFile destRandomAccessFile = new RandomAccessFile(destFilePath, "rw");
            FileChannel destAccessFileChannel = destRandomAccessFile.getChannel();
            long destLength = srcLength + strHead.getBytes().length+ sdf.format(date).getBytes().length;
            MappedByteBuffer destMap = destAccessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, destLength);
            // 开始文件追加 : 先添加头部内容，再添加原来文件内容
            destMap.position(0);
            destMap.put(strHead.getBytes());
            destMap.put(sdf.format(date).getBytes());
            destMap.put(srcMap);
            destAccessFileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void upload() throws IOException {
            //获取配置文件信息
            Configuration conf = new Configuration(false);
            conf.addResource(new Path("F:\\setups\\setups\\hadoop-3.1.4\\hadoop-3.1.4\\etc\\hadoop\\core-site.xml"));
            conf.addResource(new Path("F:\\setups\\setups\\hadoop-3.1.4\\hadoop-3.1.4\\etc\\hadoop\\hdfs-site.xml"));
            conf.setQuietMode(false);
            conf.set("dfs.block.size","5M");
            conf.set("dfs.replication","3");
            //获取文件系统L
            FileSystem hdfs = FileSystem.get(conf);
            //文件名称
            Path src = new Path("F://Group5_Filesize_8.75MB.txt");
            Path dst = new Path("hdfs://hadoop-namenode:9082//");
            if(!hdfs.exists(dst)){

                hdfs.mkdirs(dst);
            }
            hdfs.copyFromLocalFile(src, dst);
            System.out.println("Upload to " + conf.get("fs.default.name"));
            FileStatus files[] = hdfs.listStatus(dst);
            for(FileStatus file:files){
                System.out.println(file.getPath());
            }
        }

    private static void unzip() {
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-namenode:9082"), conf);
            fs.copyToLocalFile(new Path("/airQuality.zip"),new Path("F:\\airQuality.zip"));
            fs.close();
            ZipFile zipFile = new ZipFile("F:\\airQuality.zip\\");
            zipFile.extractAll("F:\\airQuality\\");//需要新建一个airQuality空文件
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


        public static void Filestructure(String path) throws IOException {
            File file = new File(path);
            File[] list = file.listFiles();

            for (int i = 0; i < list.length; i=i+1) {
                if (list[i].isFile()) {
                    System.out.println("****************");
                    System.out.println("Filename: " + list[i].getName() + "\nFile path: " + list[i].getAbsolutePath());

                    System.out.println("Five rows：");
                    BufferedReader reader = new BufferedReader(new FileReader(list[i]));
                    int n=0;
                    String line = reader.readLine();
                    while (line != null & n < 5) {
                        System.out.println(line);
                        n++;
                    }
                    reader.close();
                }
                if (list[i].isDirectory()) {
                    System.out.println("Document：" + list[i].getName());
                    Filestructure(list[i].toString());
                }
            }
        }
        public static void  main(String[] args) throws IOException, InterruptedException,URISyntaxException {
           String filepath="hdfs://hadoop-namenode:9082/fileSize_8MB.txt";
            readfile(filepath);
            append();
            String filepath2="hdfs://hadoop-namenode:9082/Group5_Filesize_8.75MB.txt";
          upload();
           readfile(filepath2);
        unzip();

        String unzippath="F:\\airQuality\\";
           Filestructure(unzippath);

    }
}