import org.junit.Test;

import java.io.*;

/*

 */
public class Io {
    @Test
    public static void main1(String[] args) throws Exception {
        //字节流转换为字符流，需要使用字节字符转换流
        //转换流可以设置编码，java文件的编码格式、文件编码格式
        //InputStreamReader：输入的字节字符转换流，或OutputStreamWriter：输出的字节字符转换流
        FileInputStream file=new FileInputStream(
                new File("H:\\bit\\javaweb\\20200405\\1.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(file,"utf-8"));
        //字符流按字符数组读取
        char[] chars=new char[1024];
        int i;
        //返回值为真实读取的长度，off：偏移量，len：每次读多长
//        while((i=br.read(chars,0,2)) != -1){
//            String str=new String(chars);
//            System.out.println(str);
//        }
        while((i=br.read(chars)) != -1){
            String str=new String(chars);
            System.out.println(str);
        }
        //字符流按行读取
//        String line;
//        while((line=br.readLine())!=null){
//            System.out.println(line);
//        }

    }
    public static void main2(String[] args) throws Exception {
        FileInputStream file = new FileInputStream(
                new File("H:\\bit\\javaweb\\20200405\\1.txt"));
        //按字节读取
        byte[] by=new byte[1024];
        int len;
        while ((len=file.read(by,0,1024))!=-1){
            System.out.println(new String(by,0,len));
        }
    }

    //写操作
    public static void main0(String[] args) throws Exception {
        FileOutputStream file = new FileOutputStream(
                new File("H:\\bit\\javaweb\\20200405\\1.txt"));
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(file,"utf-8"));
        //使用缓冲流：输出的时候，要进行flush刷新缓冲区，否则不会真实输出数据到目的设备
        br.write("sa1ssad市第八号");
        br.flush();//缓冲区刷新，发送数据到目的设备
    }

    //复制文本文件
    public static void main3(String[] args) throws Exception{
        FileOutputStream fi = new FileOutputStream(
                new File("H:\\bit\\javaweb\\20200405\\2.txt"));
        FileInputStream fr = new FileInputStream(
                new File("H:\\bit\\javaweb\\20200405\\1.txt"));
        BufferedReader br1=new BufferedReader(new InputStreamReader(fr,"utf-8"));
        BufferedWriter br2=new BufferedWriter(new OutputStreamWriter(fi,"utf-8"));
        String str;
        int len;
        while((str=br1.readLine())!=null){
            br2.write(str);
            br2.flush();
        }
    }
    public static void main(String[] args) throws Exception {
        FileOutputStream fi = new FileOutputStream(
                new File("H:\\bit\\javaweb\\20200405\\2.txt"));

        FileInputStream fr = new FileInputStream(
                new File("H:\\bit\\javaweb\\20200405\\1.txt"));
        BufferedInputStream br1=new BufferedInputStream(fr);
        byte[] by=new byte[1024];
        int len;
        while ((len=br1.read(by,0,1024))!=-1){
            fi.write(by,0,len);
            fi.flush();
        }
        //io流的操作完成之后，要释放资源
        //顺序根据依赖关系，反向释放
        //先释放后面的，br1依赖于fr
        br1.close();
        fr.close();
        fi.close();
    }
}
