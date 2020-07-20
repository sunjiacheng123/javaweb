package lesson2;

import java.io.File;
import java.util.Arrays;

public class FileTest {

    public static void main(String[] args) {
//        File file1 = new File("D:\\Workspaces\\java13\\io-study\\res");
//        File file2 = new File("D:\\Workspaces\\java13\\io-study\\res1");
//        System.out.println(file1.isDirectory());
//        System.out.println(Arrays.toString(file1.listFiles()));
//        System.out.println(file1.getPath());
//        System.out.println(file2.exists());
//        file2.mkdirs();
        StringBuilder sb = new StringBuilder("计算机").append("软件");
        print(sb);
    }
    // 变量和值（对象）区分开，=号赋值引用指向
    // 传入参数、局部变量是在线程执行方法的时候创建，方法执行完就销毁
    // 这些信息是以栈帧保存在栈里边，和（线程）执行（方法）相关
    public static void print(StringBuilder sb){// StringBuilder sb="计算机软件";
        int i = 0;
        print(sb);
    }
}
